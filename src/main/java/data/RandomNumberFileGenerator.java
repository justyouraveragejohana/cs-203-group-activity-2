package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/** Creates user-named text files containing random integer data. */
public class RandomNumberFileGenerator {
    private static final int MAX_SIZE = 1_000_000;
    // nextInt uses an exclusive upper bound, so this generates values from 0 through 10.
    private static final int MAX_RANDOM_NUMBER = 10;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of random integers to generate (0-1,000,000): ");
            if (!scanner.hasNextInt()) {
                System.err.println("Please enter a whole number.");
                return;
            }

            int size = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter an output file name: ");
            String fileName = scanner.nextLine().trim();

            try {
                Path outputFile = generateFile(size, fileName);
                System.out.println("Saved " + size + " random integers to " + outputFile);
            } catch (IllegalArgumentException exception) {
                System.err.println(exception.getMessage());
            } catch (IOException exception) {
                System.err.println("Could not write the output file: " + exception.getMessage());
            }
        }
    }

    /** Writes the requested count to a working-directory file, adding .txt when needed. */
    public static Path generateFile(int size, String fileName) throws IOException {
        if (size < 0 || size > MAX_SIZE) {
            throw new IllegalArgumentException("Size must be between 0 and " + MAX_SIZE + ".");
        }
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name cannot be empty.");
        }

        String outputName = fileName.trim();
        if (outputName.contains("/") || outputName.contains("\\")
                || outputName.equals(".") || outputName.equals("..")) {
            throw new IllegalArgumentException("Enter a file name, not a path.");
        }
        if (!outputName.toLowerCase(Locale.ROOT).endsWith(".txt")) {
            outputName += ".txt";
        }
        Path outputFile = Path.of(outputName);

        try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            for (int i = 0; i < size; i++) {
                int number = ThreadLocalRandom.current().nextInt(MAX_RANDOM_NUMBER + 1);
                writer.write(Integer.toString(number));
                writer.newLine();
            }
        }

        return outputFile.toAbsolutePath();
    }
}
