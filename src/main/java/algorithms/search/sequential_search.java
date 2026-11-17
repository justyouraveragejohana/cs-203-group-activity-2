package algorithms.search;

public class sequential_search(int [] A, int k) {
    public double durationInMillis = 0;
    public static long basicOpCount = 0;
     long startTime = System.nanoTime();
    int i = 0; //Scan Index
    //Sequential list scan: stop at list end or when key is found
    basicOpCount = 0;
    while ((i < A.length) && (A[i] != k)) {
    count++; // Counts every failed comparison
    i++;
    }
        
    // If i < A.length, the loop stopped because A[i] != k was false (1 final comparison)
if (i < A.length) {
    basicOpCount++; 
    return i;
    } else {
    return -1;
    }
    long endTime = System.nanoTime();
    this.durationInMillis = (endTime - startTime) / 1_000_000.0;
}
