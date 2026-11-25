package menu;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

import data.reader.NumberFileReader;
import data.generator.RandomNumberFileGenerator;

/* Prompts for either a generated input file or an existing file. */
final class NumberFileInput {
    // name of the input file so it can be saved in the excel sheet
    static String filePath = "";

    private NumberFileInput() {
    }

    /* Collects the selected file, then returns its validated contents as an array. */
    static int[] loadNumbers(Scanner scanner) throws IOException {
        System.out.println("Choose an input file:");
        System.out.println("1. Generate a random numbered input file");
        System.out.println("2. Use an existing input file");
        int sourceChoice = MenuInput.readNumber(scanner, "Enter a choice (1-2): ", 1, 2);

        Path file;
        if (sourceChoice == 1) {
            file = generateNumbersFile(scanner);
            System.out.println("Generated input file: " + file);
        } else {
            file = askForExistingFile(scanner);
        } 

        // saving just the file name for the excel sheet
        filePath = file.toAbsolutePath().toString();

        return NumberFileReader.readNumbers(file);
    }

    /* Prompts for the output file size and name, then creates the file. */
    private static Path generateNumbersFile(Scanner scanner) throws IOException {
        int size = MenuInput.readNumber(scanner, "Enter the number of random integers to generate (0-1,000,000): ", 0, 1_000_000);

        // keep asking until we get a file name that works
        while (true) {
            System.out.print("Enter an input file name (no need to add .txt to the end): ");

            try {
                return RandomNumberFileGenerator.generateFile(size, scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                // name was empty or bad character in it so tell them what happened and ask again
                System.out.println(e.getMessage());
            }
        }
    }

    // keeps asking for a file name until it finds a input file that works
    private static Path askForExistingFile(Scanner scanner) {
        while (true) {
            System.out.print("Enter the input file name (ex. test.txt): ");
            String name = scanner.nextLine().trim();

            // adding .txt if they left it off
            if (!name.endsWith(".txt")) {
                name = name + ".txt";
            }

            // looking in downloads folder for saved file
            Path file = Path.of(System.getProperty("user.home"), "Downloads", name);

            try {
                // reading it here just to check if it's a real input file with numbers
                NumberFileReader.readNumbers(file);
                return file;
            } catch (NoSuchFileException e) {
                System.out.println("Could not find that file, make sure it's in your downloads folder and try again.");
            } catch (IOException | IllegalArgumentException e) {
                // file is there but something is wrong with it
                System.out.println("Could not use that file: " + e.getMessage());
            }
        }
    }
}
