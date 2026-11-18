package algorithms.search;

public class binary_search {

    public double durationInMillis = 0;
    private static long basicOpCount;

    public int binarySearch(int[] arr, int target) {
        basicOpCount = 0;
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
}
