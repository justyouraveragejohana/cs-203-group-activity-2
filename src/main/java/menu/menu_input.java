package menu;

import java.util.Scanner;

// reads numbers form the user and keeps asking if they type something wrong so a type won't close the whole program
public class menu_input {
    private menu_input() {
    }

    // keeps asking the question until the user types a whole number from declared min to max
    static int readNumber(Scanner scanner, String question, int min, int max) {
        while (true) {
            System.out.print(question);
            String answer = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(answer);

                // checking if the number is in specified range
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // asks again bc user typed something wrong again
            }
            System.out.println("Please enter a whole number from " + min + " to " + max + ".");
        }
    }

    // same as readNumber but for any number
    static int readAnyNumber(Scanner scanner, String question) {
        while (true) {
            System.out.print(question);
            String answer = scanner.nextLine().trim();

            try {
                return Integer.parseInt(answer);
            } catch (NumberFormatException e) {
                // user didn't type a whole number so asks again
                System.out.println("Please enter a whole number.");
            }
        }
    }
}
