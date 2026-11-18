package algorithms.sort;

public class merge_sort {

    // counts the basic operation (comparing B[i] <= C[j] while merging)
    // long instead of int because larger arrays can go past int's limit
    public static long basicOpCount = 0;
    public double durationInMillis = 0;

    // this will take two sorted arrays, B and C, and combines them into one sorted array, A
    public static void MergeSortedArrays(int[] A, int[] B, int[] C) {
        int i = 0; int j = 0; int k = 0; // i is the index for array B, j for array C and k for array A

        // this will keep going while both array B and C still have numbers left to be sorted??
        while ((i < B.length) && (j < C.length)) {
            basicOpCount++; // counting comparison on next line
            // whichever number is smaller goes into A next
            if (B[i] <= C[j]) {
                A[k] = B[i];
                i++;
            }
            else {
                A[k] = C[j];
                j++;
            }
            k++;
        }

        //one array ran out??, copy whatever is left in the other one into A
        if (i == B.length) {
            System.arraycopy(C, j, A, k, C.length - j);
        }
        else {
            System.arraycopy(B, i, A, k, B.length - i);
        }
    }

    public void MergeSort (int[] A) {
        long startTime = System.nanoTime();
        if (A.length > 1) { //checking if there is more than one number, if not it's already sorted
            int h = (int) Math.floor(A.length / 2); // find the middle to split the array in half

            // copy the first half of array to B and second half to C
            int B[] = new int[h] ;
            System.arraycopy(A, 0, B, 0, h);
            int C[] = new int[A.length - h];
            System.arraycopy(A, h, C, 0, A.length - h);

            // sort each half of array the same way. It'll keep splitting until they are left with 1 number
            this.MergeSort(B);
            this.MergeSort(C);

            // combine the two sorted halves back into A
            MergeSortedArrays(B, C, A);

        }
    long endTime = System.nanoTime();
    this.durationInMillis = (endTime - startTime) / 1_000_000.0;
    }
}
