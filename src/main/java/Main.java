import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

//THIS IS JUST TEST CODE, I WILL CHANGE SOON

public class Main {
    public static void main(String[] args) throws Exception {
        // making a new empty Excel workbook with one sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.createSheet("Test");

        // saving it as test.xlsx to check that POI works
        FileOutputStream out = new FileOutputStream("test.xlsx");
        workbook.write(out);
        out.close();
        workbook.close();

        System.out.println("test.xlsx was made");
    }
}