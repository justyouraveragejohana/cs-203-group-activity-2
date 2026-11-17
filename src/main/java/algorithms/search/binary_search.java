package algorithms.search;

public class binary_search {
    public static int binarySearch(int[] arr, int target) {
    public double durationInMillis = 0;
    public static long basicOpCount = 0;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            // Prevents potential integer overflow compared to (low + high) / 2
            int mid = low + (high - low) / 2;
            basicOpCount++
            
            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                low = mid + 1; // Search the right half
            } else {
                high = mid - 1; // Search the left half
            }
        }
        return -1; // Target not found
        long endTime = System.nanoTime();
        this.durationInMillis = (endTime - startTime) / 1_000_000.0;
    }
}
