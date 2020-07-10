# README for the Code Assignment.

## Assignment details

The assignment reads three files in the `data` directory of the src/resources folder. There are three files a.txt, b.txt, and c.txt.

Logical steps of excecution in the code:
1. The code reads the number of files in the `data` directory.
2. It then reads the contents of each file into a Set<String>. The intent behind using a Set was to eliminate duplicates, since a Set doesnt allow for Duplicates.
3. It then uses the lambda function to stream through the list of sets, and construct a Set containing the contents of all of the files.
4. It then applies the lexicographic sort. The Java Collections.sort method does that for us, and finally, we write that output to a file.
5. The code writes the sorted data to an outfile in the /tmp directory called, /tmp/out.txt. 

##Performance considerations:

I used the Lambda functions, and the capabilities of the Java collections framework, to read, eliminate dups, and sort through the files, 
instead of manually writing code for it, which would have been slower, and less efficient.

# Java
All modules require **Java 11** to compile and run.

# Building with Gradle

The build is configured so that you can build all modules at once or each module separately.

## Building the code

In the main folder, run 
```
./gradlew clean build
```

## Running the module

In the folder of the module, run

```
./gradlew bootRun
```

