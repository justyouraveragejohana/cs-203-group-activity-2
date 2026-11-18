package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Reads integer data files into arrays for the search and sorting algorithms. */
public final class NumberFileReader {
    private NumberFileReader() {
    }

    /** Validates and reads one 32-bit integer per line; an empty file returns an empty array. */
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

        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String value = line.trim();
                if (value.isEmpty()) {
                    throw new IOException("Blank line at line " + lineNumber + " in " + file);
                }

                try {
                    numbers.add(Integer.parseInt(value));
                } catch (NumberFormatException exception) {
                    throw new IOException(
                            "Invalid 32-bit integer at line " + lineNumber + " in " + file + ": " + value,
                            exception);
                }
            }
        }

        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }
        return result;
    }
}
