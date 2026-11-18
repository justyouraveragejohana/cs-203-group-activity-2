package algorithms.sort;

public class bubble_sort {
    public double durationInMillis = 0;
    public static long basicOpCount = 0;
    
    public bubble_sort(int [] A) {
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
                basicOpCount++;
                    }
        }
        //If no swap is made, the list is sorted
        if(!swapMade) {break;}
    }
    long endTime = System.nanoTime();
    this.durationInMillis = (endTime - startTime) / 1_000_000.0;
        
}
}
