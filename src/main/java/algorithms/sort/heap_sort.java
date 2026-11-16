package algorithms.sort;

public class heap_sort {
     // Tracks the total number of key comparisons (basic operations)
    public static long count = 0;
    
     public static void sort(int[] arr) {
        int n = arr.length;
        basicOpCount = 0;
         // Reset the counter before starting a new sort
         
         // Step 1: Build a max heap from the unsorted array
        // Start from the last non-leaf node and move upwards to the root
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
         // Step 2: Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move the current root (largest element) to the end of the array
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // Restore the max heap property on the remaining reduced heap
            heapify(arr, i, 0);
        }
    }
     private static void heapify(int[] arr, int n, int i) {
        int largest = i;       // Assume the root is the largest element
        int left = 2 * i + 1;  // Calculate the index of the left child
        int right = 2 * i + 2; // Calculate the index of the right child

        // Check if the left child exists within the heap bounds
        if (left < n) {
            count++; // Increment counter for the key comparison below
            if (arr[left] > arr[largest]) {
                largest = left;
            }
        }
        // Check if the right child exists within the heap bounds
        if (right < n) {
            count++; // Increment counter for the key comparison below
            if (arr[right] > arr[largest]) {
                largest = right;
            }
        }
         // If the largest element is not the root, swap them and continue heapifying
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
         // Recursively heapify the affected child subtree
            heapify(arr, n, largest);
        }
    }
}
