package menu;

import java.util.Arrays;
import java.util.function.LongSupplier;
import java.util.function.ToDoubleFunction;
import data.excel.excel_data;
import java.io.IOException;
import java.nio.file.Path;
import data.writer.SortedFileWriter;

/** Runs a sort on a copy of the input and reports its duration and operation count. */
final class SortReporter {
    private SortReporter() {
    }

    /** Copies the source data, runs the sort, and prints its duration and basic operations. */
    static void reportSort(
            String name,
            int[] originalNumbers,
            ToDoubleFunction<int[]> sortAlgorithm,
            LongSupplier basicOperationCounter) {
        int[] algorithmInput = Arrays.copyOf(originalNumbers, originalNumbers.length);
        double durationInMillis = sortAlgorithm.applyAsDouble(algorithmInput);
        long basicOps = basicOperationCounter.getAsLong();

        System.out.println(name + " completed in " + durationInMillis + " ms.");
        System.out.println("Basic operations: " + basicOperationCounter.getAsLong());

        // save sorted numbers to a file so we have the o/p
        // this goes after the timer stopped so saving the file isnt included in the runtime
        String sortedFile;
        try {
            Path sortedPath = SortedFileWriter.writeSortedFile(algorithmInput, NumberFileInput.filePath, name);
            sortedFile = sortedPath.toString();
            System.out.println("Sorted output saved to " + sortedFile);
        } catch (IOException e) {
            // if the file can't be saved, still save the run to excel but w N/A in the sorted file column
            System.out.println("Could not save the sorted output file: " + e.getMessage());
            sortedFile = "N/A";
        }

        // save this run to excel
        // position of k is N/A since this is a sort
        // we have to save the sorted output but it's N/A for now
        excel_data.saveRun(originalNumbers.length, "Sort", name, NumberFileInput.filePath, "N/A", 
        "N/A", basicOps, durationInMillis, sortedFile);

    }
}
