package Menu;

import java.util.Arrays;
import java.util.function.LongSupplier;
import java.util.function.ToDoubleFunction;

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

        System.out.println(name + " completed in " + durationInMillis + " ms.");
        System.out.println("Basic operations: " + basicOperationCounter.getAsLong());
    }
}
