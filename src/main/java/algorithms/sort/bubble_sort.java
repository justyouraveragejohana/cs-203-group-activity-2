package algorithms.sort;

public class bubble_sort(int [] A) {
    public static long basicOpCount = 0;
    // Loop to perform n-2 scans
    for (int i = 0; i < A.length - 1; i++) {
        // Bubble up items (partial scan)
        boolean swapMade = false; //initalize flag to track swaps made
        basicOpCount = 0; //initalize basic operation counter
        for(int j = 0; j < A.length - 1 - i; j++){
            if( A[j+1] < A[j] ) {
                //swap adjacent items.
                int temp = A[j];
                A[j] = A[j+1];
                A[j+1] = temp;
                swapMade = true;// Update flag to track swaps made
                basicOpCount++
                    }
        }
        //If no swap is made, return early (list sorted)
        if(!swapMade) {return}
    }
        
}
