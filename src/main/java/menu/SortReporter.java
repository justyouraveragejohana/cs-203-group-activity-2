package menu;

import java.util.Arrays;
import java.util.function.LongSupplier;
import java.util.function.ToDoubleFunction;
import data.excel.excel_data;

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

        // save this run to excel
        // position of k is N/A since this is a sort
        // we have to save the sorted output but it's N/A for now
        excel_data.saveRun(originalNumbers.length, "Sort", name, NumberFileInput.filePath, "N/A", 
        "N/A", basicOps, durationInMillis, "N/A");

    }
}
