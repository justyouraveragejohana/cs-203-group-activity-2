package algorithms.sort;

public class selection_sort {

    // counts basic operation (comparing two numbers)
    // long rather than int because larger arrays can go past int limit
    public static long basicOpCount = 0;
    public double durationInMillis = 0;

    public void SelectionSort(int[] A) {
        long startTime = System.nanoTime();
        basicOpCount = 0;
        //temp variables
        int min; // holds index of min/smallest partition
        int temp; // buffer var while swap is performed

        // loop sorts 1 item at a time apart from last item
        for (int i = 0; i < A.length - 1; i++) {
            min = i; // starts by assigning and assuming the first spot as the smallest

            // scan the rest of the array for a smaller partition
            for (int j = i+1; j < A.length; j++) {
                basicOpCount++; //counting comparison on the next line
                
                // checking if current number is smaller than the partition
                if (A[j] < A[min]) {
                    min = j;
                }
            }
            // swap the smallest number/partition into current spot
            temp = A[i];
            A[i] = A[min];
            A[min] = temp;

        }
        long endTime = System.nanoTime();
        this.durationInMillis = (endTime - startTime) / 1_000_000.0;
    }
}
