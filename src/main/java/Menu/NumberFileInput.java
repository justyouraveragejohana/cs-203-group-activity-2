package Menu;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import data.NumberFileReader;
import data.RandomNumberFileGenerator;

/** Prompts for either a generated numbers file or an existing file. */
final class NumberFileInput {
    private NumberFileInput() {
    }

    /** Collects the selected file, then returns its validated contents as an array. */
    static int[] loadNumbers(Scanner scanner) throws IOException {
        System.out.println("Choose a numbers source:");
        System.out.println("1. Generate a random numbers file");
        System.out.println("2. Use an existing numbers file");
        System.out.print("Enter a choice (1-2): ");
        if (!scanner.hasNextInt()) {
            throw new IllegalArgumentException("Please enter 1 or 2.");
        }

        int sourceChoice = scanner.nextInt();
        scanner.nextLine();
        Path file;

        if (sourceChoice == 1) {
            file = generateNumbersFile(scanner);
            System.out.println("Generated numbers file: " + file);
        } else if (sourceChoice == 2) {
            System.out.print("Enter the path to the numbers file: ");
            file = Path.of(scanner.nextLine().trim());
        } else {
            throw new IllegalArgumentException("Choice must be 1 or 2.");
        }

        return NumberFileReader.readNumbers(file);
    }

    /** Prompts for the output file size and name, then creates the file. */
    private static Path generateNumbersFile(Scanner scanner) throws IOException {
        System.out.print("Enter the number of random integers to generate (0-1,000,000): ");
        if (!scanner.hasNextInt()) {
            throw new IllegalArgumentException("Please enter a whole number.");
        }
        int size = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter an output file name: ");
        return RandomNumberFileGenerator.generateFile(size, scanner.nextLine().trim());
    }
}
