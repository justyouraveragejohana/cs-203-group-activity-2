package data.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// save the sorted numbers to a file so we have the output of each sort
public final class SortedFileWriter {
    private SortedFileWriter() {

    }

    // saves the sorted numbers in the downloads folder and give back where the file lives
    // the name is based on input file name and alg. used. ex. test-selection-sort-sorted.txt
    public static Path writeSortedFile(int[] numbers, String inputFilePath, String algorithmName) throws IOException {
        // getting the input file name without the .txt at the end
        String inputName = Path.of(inputFilePath).getFileName().toString();
        if (inputName.endsWith(".txt")) {
            inputName = inputName.substring(0, inputName.length() - 4);
        }
        // placing/writing alg. name in file name
        String algName = algorithmName.toLowerCase().replace(" ", "-");
        String outputName = inputName + "-" + algName + "-sorted.txt";
        Path outputFile = Path.of(System.getProperty("user.home"), "Downloads", outputName);

        try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            for (int i = 0; i < numbers.length; i++) {
                writer.write(Integer.toString(numbers[i]));

                // putting a space between the numbers so they are formatted like the input file
                if (i < numbers.length - 1) {
                    writer.write(" ");
                }
            }
            writer.newLine(); // line break at end of file
        }
        return outputFile.toAbsolutePath();
    }  
}
