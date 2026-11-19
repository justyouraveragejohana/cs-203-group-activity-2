package algorithms.search;

public class binary_search {

    // basic operation count (comparing k with A[middle])
    // the slides count the ==, <, and > check as one comparison so thats counted once per middle
    public static long basicOpCount = 0;

    // loop version
    public static int BinarySearchLoop (int[] A, int k) {

        // left and right are ends of the part we are searching
        int left = 0;
        int middle = 0;
        int right = A.length - 1;

        // keep going until theres nothing left to search
        while (left <= right) {
            middle = (int) Math.floor((left + right) / 2); // finds middle spot
            count++; // counting basic operation (comparison with A[middle])
            if (k == A[middle]) {
                return middle; 
            }
            else if (k < A[middle]) {
                right = middle - 1; // number is smaller so search the left half
            }
            else {
                left = middle + 1; // number is bigger so search the right half
            }
        }
        return -1; // number isn't in the array

    }
    
}
