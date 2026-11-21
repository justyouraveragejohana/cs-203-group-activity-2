package data.reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/** Reads integer data files into arrays for the search and sorting algorithms. */
public final class NumberFileReader {
    private NumberFileReader() {
    }

    // reads the numbers from the file into the array
    // allowing numbers to be separated by numbers, spaces, commas, new lines for old files to work
    public static int[] readNumbers(Path file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File path cannot be null.");
        }
        if (!Files.exists(file)) {
            throw new NoSuchFileException(file.toString());
        }
        if (!Files.isRegularFile(file)) {
            throw new IOException("Path is not a regular file: " + file);
        }
        if (!Files.isReadable(file)) {
            throw new IOException("File is not readable: " + file);
        }

        // read the file as one text
        String text = Files.readString(file, StandardCharsets.UTF_8).trim();

        // checking if the file is empty
        if (text.isEmpty()) {
            return new int[0];
        }

        // splitting the text where breaks are (spaces, commas, etc.)
        String[] pieces = text.split("[\\s,]+");
        int[] result = new int[pieces.length];
        for (int i = 0; i < pieces.length; i++) {
            try {
                result[i] = Integer.parseInt(pieces[i]);
            } catch (NumberFormatException exception) {
                // telling user that something in the file is not a whole number
                throw new IOException("Number " + (i + 1) + "in" + file.getFileName() 
                + " is not a whole number: " + pieces[i], exception);
            }
        }
        return result;
    }
}
