package algorithms.sort;

public class bubble_sort(int [] A) {
    public double durationInMillis;
    public static long count = 0;
    //Start time
    long startTime = System.nanoTime();
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
               count++
                    }
        }
        //If no swap is made, return early (list sorted)
        if(!swapMade) {return}
    }
     long endTime = System.nanoTime();
     this.durationInMillis = (endTime - startTime) / 1_000_000.0;
        
}
