# cs-203-group-activity-2

the algorithms folder is under src/main/java now

## Random number text file

Run `data.RandomNumberFileGenerator`, enter a size from 0 to 1,000,000, then enter an output file name. It writes that many random integers from 0 to 10, one per line, to the named `.txt` file in the program's working directory. The `.txt` extension is added if omitted, and an existing file with the same name is overwritten.

Use `data.NumberFileReader.readNumbers(Path)` to load a numbers file as an `int[]`. It verifies that the path is a readable regular file and that every line contains one 32-bit integer; an empty file is valid and returns an empty array.
