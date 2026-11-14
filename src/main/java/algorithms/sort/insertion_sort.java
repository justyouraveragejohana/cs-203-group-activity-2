package algorithms.sort;

public class insertion_sort {

    public static void InsertionSort(int[] A) {
        int val = 0; // value we are trying to put in the correct spot
        int j = 0; // index walks backwards through sorted portion of array

        //start at the 2nd number since one number by itslef is already sorted
        for (int i = 0; i < A.length; i++) {
            val = A[i]; // save the current value
            j = i-1; // start checking from the number before it

            //move larger numbers one spot to the right to make room
            while ((j >= 0) && (A[j] > val)) {
                A[j+1] = A[j]; // shift the larger number to the right
                j = j-1; // move one spot to the left and check again
            }
            A[j+1] = val; // put the number in the open spot
        }
    }
    
}
