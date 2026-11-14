package algorithms.sort;

public class selection_sort {

    public static void SelectionSort( int[] A) {
        //temp variables
        int min; // holds index of min/smallest partition
        int temp; // buffer var while swap is perfomred

        // loop sorts 1 item at a time apart from last item
        for (int i = 0; i < A.length - 1; i++) {
            min = i; // starts by assigning and assuming the first spot as the smallet

            // scan the rest of the array for a smaller partition
            for (int j = i+1; j < A.length; j++) {
                // checking if current number is smaller than the partition
                if (A[j] < A[min]) {
                    min = j;
                }
                
                // swap the smallest number/partition into current spot
                temp = A[i];
                A[i] = A[min];
                A[min] = temp;
            }

        }
    }
    
}