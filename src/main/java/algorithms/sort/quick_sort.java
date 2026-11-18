package algorithms.sort;

public class quick_sort {
      // Tracks the total number of key comparisons (basic operations)
    public static long basicOpCount = 0;
    public double durationInMillis = 0;  



        /**
     * Places the pivot element at its correct position and moves all smaller
     * elements to its left and larger elements to its right (Lomuto partition).
     *
     * @param arr  The array being partitioned
     * @param low  The starting index
     * @param high The ending index (chosen as the pivot)
     * @return The final index of the pivot element
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose the rightmost element as the pivot
        int i = low - 1;       // Index of the smaller element boundary

        // Compare each element in the subarray against the pivot
        for (int j = low; j < high; j++) {
           basicOpCount++; // Increment counter for the key comparison below
            if (arr[j] <= pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place the pivot element in its correct sorted position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // Return the pivot index
    }




      
    /**
     * Sorts an array of integers in ascending order using Quick Sort.
     *
     * @param arr The array to be sorted
     */
    public void sort(int[] arr) {
      long startTime = System.nanoTime();
        basicOpCount = 0; // Reset the counter before starting a new sort
        if (arr != null && arr.length > 1) {
            quickSort(arr, 0, arr.length - 1);
        }
      long endTime = System.nanoTime();
      this.durationInMillis = (endTime - startTime) / 1_000_000.0;
    }

    /**
     * Recursively partitions the array and sorts the subarrays.
     *
     * @param arr  The array being sorted
     * @param low  The starting index of the current subarray
     * @param high The ending index of the current subarray
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot's final sorted index
            int pivotIndex = partition(arr, low, high);

            // Recursively sort elements before and after the pivot
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
       
    }

  
}
