package algorithms.search;

public class sequential_search(int [] A, int k) {
    public static long count = 0
    int i = 0; //Scan Index
    //Sequential list scan: stop at list end or when key is found
    count = 0
    while ((i < A.length) && (A[i] != k)) {
    count++; // Counts every failed comparison
    i++;
    }
        
    // If i < A.length, the loop stopped because A[i] != k was false (1 final comparison)
if (i < A.length) {
    count++; 
    return i;
    } else {
    return -1;
    }
}
