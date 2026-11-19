package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

// saves the resutls of each run as a new row in an excel file
public class excel_data {

   // finds the persons downloads folder and save the excel there
   public static final String FOLDER = System.getProperty("user.home") + File.separator + "Downloads";
   public static final String FILE_NAME = "algorithm-results.xslx";

   // column names for the header row
   private static final String[] HEADERS = {
      "Run", "Input Size", "Sort or Search", "Algorithm", "Input File Name", 
      "Position of k (Search Only)", "# of Basic Operations Performed", 
      "Runtime (m/s)", "Sorted File Name" 
   };

   // column widths
   private static final double[] WIDTHS = {5, 10, 13, 18, 18, 25, 26, 13.50, 18};

   // adds run to excel file
   // positionOfK is "N/A" for sorting runs
   public static void saveRun (int inputSize, String sortOrSearch, String alg, 
                              String inputFile, String positionOfK, long basicOps, 
                              double runtime, String sortedFile) {
      try {
         XSSFWorkbook workbook;
         XSSFSheet sheet;
         File file = new File(FOLDER, FILE_NAME); // the excel lives inside users downloads folder

         // checking if the file already exists
         if (file.exists()) {
            // opens file to add to it if it exists
            FileInputStream in = new FileInputStream(file);
            workbook = new XSSFWorkbook(in);
            in.close();
            sheet = workbook.getSheetAt(0);
         }
         else {
            // makes a new file if it doesn't exist yet
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Results");
            makeHeader(workbook, sheet);
         }

         // put a new empty row after previous one is made
         int rowNum = sheet.getLastRowNum() + 1;
         XSSFRow row = sheet.createRow(rowNum);
         XSSFCellStyle style = makeRowStyle(workbook);

         // assigning columns/rows to their data
         setNumber(row, 0, rowNum, style);
         setNumber(row, 1, inputSize, style);
         setText(row, 2, sortOrSearch, style);
         setText(row, 3, alg, style);
         setText(row, 4, inputFile, style);
         setText(row, 5, positionOfK, style);
         setNumber(row, 6, basicOps, style);
         setNumber(row, 7, runtime, style);
         setText(row, 8, sortedFile, style);

         //save file, this writes over the old file with only the new row added
         FileOutputStream out = new FileOutputStream(file);
         workbook.write(out);
         out.close();
         workbook.close();

         System.out.println("Run " + rowNum + " saved to " + file.getAbsolutePath());
      } catch (IOException e) {
         // usually happens if the excel file is open while the program is running
         System.out.println("Could not save to " + FILE_NAME + ", close the Excel file and try again.");
      }
   }

   // makes the header row with our desired cell and manual formatting
   private static void makeHeader(XSSFWorkbook workbook, XSSFSheet sheet) {
      XSSFFont font = workbook.createFont();
      font.setBold(true);
      font.setFontName("Arial");
      font.setFontHeightInPoints((short) 11);
      font.setColor(makeColor(0x00, 0x00, 0x00));

      XSSFCellStyle style = workbook.createCellStyle();
      style.setFont(font);
      style.setFillForegroundColor(makeColor(0xDC, 0xEA, 0xF7));
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      style.setBorderTop(BorderStyle.MEDIUM);
      style.setBorderBottom(BorderStyle.THIN);
      style.setBorderLeft(BorderStyle.THIN);
      style.setBorderRight(BorderStyle.THIN);

      XSSFRow header = sheet.createRow(0);

      // go through each column and put the name in it
      for (int i = 0; i < HEADERS.length; i++) {
         XSSFCell cell = header.createCell(i);
         cell.setCellValue(HEADERS[i]);
         cell.setCellStyle(style);
         sheet.setColumnWidth(i, (int) (WIDTHS[i] * 256));
      }
   }

   // makes the desired cell and manual formating for each run
   private static XSSFCellStyle makeRowStyle(XSSFWorkbook workbook) {
      XSSFCellStyle style = workbook.createCellStyle();
      style.setFillForegroundColor(makeColor(0xE8, 0xE8, 0xE8));
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      style.setBorderTop(BorderStyle.THIN);
      style.setBorderBottom(BorderStyle.THIN);
      style.setBorderLeft(BorderStyle.THIN);
      style.setBorderRight(BorderStyle.THIN);
      return style;
   }

   // makes rgb/color hex understandable for excel
   private static XSSFColor makeColor (int r, int g, int b) {
      byte[] rgb = {(byte) r, (byte) g, (byte) b};
      return new XSSFColor(rgb, null);
   }

   // puts a number in every new cell/run
   private static void setNumber(XSSFRow row, int col, double value, XSSFCellStyle style) {
      XSSFCell cell = row.createCell(col);
      cell.setCellValue(value);
      cell.setCellStyle(style);
   }

   // puts words in a cell
   private static void setText (XSSFRow row, int col, String value, XSSFCellStyle style) {
      XSSFCell cell = row.createCell(col);
      cell.setCellValue(value);
      cell.setCellStyle(style);
   }
}