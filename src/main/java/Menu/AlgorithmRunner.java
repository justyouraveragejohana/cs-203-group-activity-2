package menu;

import java.io.IOException;
import java.util.Scanner;

/** Coordinates number-file input and algorithm execution. */
public class AlgorithmRunner {
    /** Starts the interactive program. */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] numbers;
            try {
                numbers = NumberFileInput.loadNumbers(scanner);
            } catch (IOException | IllegalArgumentException exception) {
                System.err.println("Could not prepare numbers file: " + exception.getMessage());
                return;
            }

            AlgorithmSelector.run(numbers, scanner);
        }
    }
}
