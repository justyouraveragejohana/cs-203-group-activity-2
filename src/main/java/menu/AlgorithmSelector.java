package menu;

import algorithms.search.binary_search;
import algorithms.search.sequential_search;
import algorithms.sort.bubble_sort;
import algorithms.sort.heap_sort;
import algorithms.sort.insertion_sort;
import algorithms.sort.merge_sort;
import algorithms.sort.quick_sort;
import algorithms.sort.selection_sort;

import java.util.Arrays;
import java.util.Scanner;

import data.excel.excel_data;

// Prompts for and runs a sorting or searching algorithm. 
final class AlgorithmSelector {
    private AlgorithmSelector() {
    }

    // Runs the selected algorithm using the supplied numbers as the source data.
    static void run(int[] originalNumbers, Scanner scanner) {
        System.out.println("Choose an algorithm:");
        System.out.println("1. Bubble sort");
        System.out.println("2. Heap sort");
        System.out.println("3. Insertion sort");
        System.out.println("4. Merge sort");
        System.out.println("5. Quick sort");
        System.out.println("6. Selection sort");
        System.out.println("7. Sequential search");
        System.out.println("8. Binary search");
        
        // keeps asking until the type a number from 1 to 8
        int choice = menu_input.readNumber(scanner, "Enter a choice (1-8): ", 1, 8);

        if (choice >= 7) {
            runSelectedSearch(originalNumbers, scanner, choice == 8);
        } else {
            runSort(originalNumbers, choice);
        }
    }

    // Reads the target and runs the selected search algorithm.
    private static void runSelectedSearch(
            int[] originalNumbers, Scanner scanner, boolean useBinarySearch) {
        // keeps asking until they enter a whole number
        int target = menu_input.readAnyNumber(scanner, "Enter the number to search for: ");
        runSearch(originalNumbers, target, useBinarySearch);
    }

    // Dispatches the selected sorting algorithm. */
    private static void runSort(int[] originalNumbers, int choice) {
        switch (choice) {
            case 1:
                SortReporter.reportSort("Bubble sort", originalNumbers,
                        numbers -> new bubble_sort(numbers).durationInMillis,
                        () -> bubble_sort.basicOpCount);
                break;
            case 2:
                SortReporter.reportSort("Heap sort", originalNumbers, numbers -> {
                    heap_sort sorter = new heap_sort();
                    sorter.sort(numbers);
                    return sorter.durationInMillis;
                }, () -> heap_sort.basicOpCount);
                break;
            case 3:
                SortReporter.reportSort("Insertion sort", originalNumbers, numbers -> {
                    insertion_sort sorter = new insertion_sort();
                    sorter.InsertionSort(numbers);
                    return sorter.durationInMillis;
                }, () -> insertion_sort.basicOpCount);
                break;
            case 4:
                SortReporter.reportSort("Merge sort", originalNumbers, numbers -> {
                    merge_sort sorter = new merge_sort();
                    sorter.MergeSort(numbers);
                    return sorter.durationInMillis;
                }, () -> merge_sort.basicOpCount);
                break;
            case 5:
                SortReporter.reportSort("Quick sort", originalNumbers, numbers -> {
                    quick_sort sorter = new quick_sort();
                    sorter.sort(numbers);
                    return sorter.durationInMillis;
                }, () -> quick_sort.basicOpCount);
                break;
            case 6:
                SortReporter.reportSort("Selection sort", originalNumbers, numbers -> {
                    selection_sort sorter = new selection_sort();
                    sorter.SelectionSort(numbers);
                    return sorter.durationInMillis;
                }, () -> selection_sort.basicOpCount);
                break;
            default:
                throw new IllegalArgumentException("Unknown sorting algorithm choice: " + choice);
        }
    }

    // Copies the source data and reports the selected search result and timing.
    private static void runSearch(int[] originalNumbers, int target, boolean useBinarySearch) {
        int[] algorithmInput = Arrays.copyOf(originalNumbers, originalNumbers.length);
        int index;
        double durationInMillis;
        long basicOperations;
        String algorithmName;

        if (useBinarySearch) {
            // binary search only works on sorted array, so sort copy first
            // sort isn't counted or timed, only the search is
            Arrays.sort(algorithmInput);
            binary_search searcher = new binary_search();
            index = searcher.binarySearch(algorithmInput, target);
            durationInMillis = searcher.durationInMillis;
            basicOperations = binary_search.basicOpCount;
            algorithmName = "Binary search";
            System.out.println("Binary search sorts its copy before searching.");
        } else {
            sequential_search searcher = new sequential_search();
            index = searcher.search(algorithmInput, target);
            durationInMillis = searcher.durationInMillis;
            basicOperations = sequential_search.basicOpCount;
            algorithmName = "Sequential search";
        }

        SearchReporter.reportSearch(
                algorithmName, target, index, durationInMillis, basicOperations);
        
        // checking if the number was found so we knoe what to put in the position column
        String position;
        if (index >= 0) {
            position = String.valueOf(index);
        } else {
            position = "Not Found";
        }

        // save this run to excel
        // sorted output not made yet so save as N/A for now
        excel_data.saveRun(originalNumbers.length, "Search", algorithmName, 
        NumberFileInput.filePath, String.valueOf(target), position, basicOperations, durationInMillis, "N/A");
    }
}
