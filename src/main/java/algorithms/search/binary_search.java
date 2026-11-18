package algorithms.search;

import java.util.Arrays;

public class binary_search {

    public double durationInMillis = 0;
    private static long basicOpCount = 0;

    public int binarySearch(int[] arr, int target) {
        basicOpCount = 0;
        if (!isSorted(arr)) {
            Arrays.sort(arr);
        }

        int low = 0;
        int high = arr.length - 1;
        long startTime = System.nanoTime();
        int result = -1;

        while (low <= high) {
            // Prevents potential integer overflow compared to (low + high) / 2
            int mid = low + (high - low) / 2;
            basicOpCount++;

            if (arr[mid] == target) {
                result = mid;
                break;
            } else if (arr[mid] < target) {
                low = mid + 1; // Search the right half
            } else {
                high = mid - 1; // Search the left half
            }
        }

        long endTime = System.nanoTime();
        this.durationInMillis = (endTime - startTime) / 1_000_000.0;
        return result;
    }

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /** Returns the number of binary-search comparisons from the most recent call. */
    public static long getBasicOpCount() {
        return basicOpCount;
    }
}
