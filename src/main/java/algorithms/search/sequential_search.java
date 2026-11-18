package algorithms.search;

public class sequential_search {
    public double durationInMillis = 0;
    public static long basicOpCount = 0;

    public int search(int[] A, int k) {
        long startTime = System.nanoTime();
        int i = 0; // Scan Index

        // Sequential list scan: stop at list end or when key is found
        basicOpCount = 0;
        while ((i < A.length) && (A[i] != k)) {
            basicOpCount++; // Counts every failed comparison
            i++;
        }

        // Count the final comparison when the key is found
        int result;
        if (i < A.length) {
            basicOpCount++;
            result = i;
        } else {
            result = -1;
        }

        long endTime = System.nanoTime();
        this.durationInMillis = (endTime - startTime) / 1_000_000.0;
        return result;
    }
}
