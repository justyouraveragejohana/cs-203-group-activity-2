# CS 203 Group Activity 2

## Project overview

This Java console program lets you sort or search integer data using several
algorithms. It can generate an input file or read an existing text file, reports
the selected algorithm's runtime and basic operation count, and records each
run in an Excel workbook.

## Requirements

- JDK 25
- Maven (only needed to build and run from a terminal)
- VS Code Java support, if using the VS Code Run button

The project uses Apache POI to create and update the Excel results workbook.
Maven resolves this dependency from `pom.xml`.

## Compile and run

### In VS Code

1. Make sure the project is using JDK 25.
2. Open `src/main/java/menu/AlgorithmRunner.java`.
3. Click **Run** above the `main` method.

The Run button compiles and starts the interactive program. Run
`AlgorithmRunner` only; it coordinates input, algorithm selection, and results
reporting.

### From a terminal

From the project root, compile the project and run the interactive program:

```shell
mvn clean package
mvn exec:java -Dexec.mainClass="menu.AlgorithmRunner"
```

## Using the program

1. Choose whether to generate a random input file or use an existing file.
2. For generated input, enter a count from 0 to 1,000,000 and a file name.
   The program saves the `.txt` file in your `Downloads` folder; the extension
   is added if omitted. The generated integers range from 0 to 1,000 inclusive.
3. For an existing input file, place the `.txt` file in your `Downloads`
   folder, then enter its name (with or without the extension). The file may
   contain integers separated by spaces, commas, or line breaks. Empty files
   are valid.
4. Choose one of the sorting or searching algorithms. For a search, enter the
   integer to find. Binary search sorts a copy of the input before searching.

Sort and search operations work on a copy of the original input. The program
prints whether a search target was found, its position when found, elapsed time
in milliseconds, and the basic operation count.

Each run is appended to `Downloads/algorithm-results.xlsx`. The workbook
includes the input size, operation type, algorithm, input file, search details
when applicable, operation count, and runtime.

## Algorithms

| Type | Algorithm | Summary |
| --- | --- | --- |
| Sort | Bubble sort | Repeatedly swaps adjacent out-of-order values. |
| Sort | Heap sort | Builds a max heap and repeatedly moves its largest value to the end. |
| Sort | Insertion sort | Inserts each value into its position in the sorted portion. |
| Sort | Merge sort | Recursively sorts halves and merges them. |
| Sort | Quick sort | Partitions around a pivot and recursively sorts each side. |
| Sort | Selection sort | Repeatedly selects the smallest remaining value. |
| Search | Sequential search | Checks values in order until the target is found or the input ends. |
| Search | Binary search | Searches a sorted copy by repeatedly halving the search range. |

## Project structure

- `src/main/java/menu/` — `AlgorithmRunner` entry point, input and algorithm
  menus, and sorting/search result reporting.
- `src/main/java/algorithms/sort/` — the six sorting implementations.
- `src/main/java/algorithms/search/` — sequential and binary search
  implementations.
- `src/main/java/data/generator/` — random integer text-file generation.
- `src/main/java/data/reader/` — validation and reading of integer text files.
- `src/main/java/data/excel/` — Excel workbook creation and run logging.
