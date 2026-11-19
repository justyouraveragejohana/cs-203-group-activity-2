package menu;

/** Reports a search result, its position, duration, and operation count. */
final class SearchReporter {
    private SearchReporter() {
    }

    /** Prints a found element's zero-based index and one-based position, or reports it missing. */
    static void reportSearch(
            String name, int target, int index, double durationInMillis, long basicOperations) {
        if (index >= 0) {
            System.out.println(name + " found " + target + " at index " + index
                    + " (element position " + (index + 1) + ").");
        } else {
            System.out.println(name + " did not find " + target + ".");
        }
        System.out.println("Search completed in " + durationInMillis + " ms.");
        System.out.println("Basic operations: " + basicOperations);
    }
}
