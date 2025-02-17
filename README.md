# Symmetric-Cross-Word-Puzzle

## Overview

This project consists of two Java classes: `SymCross.java` and `Dictionary.java`. The purpose of the project is to generate 4x4 symmetric crossword puzzles using a dictionary of valid 4-letter English words found in the official Scrabble word list.

## Files

### `Dictionary.java`

The `Dictionary` class provides functionality to store and retrieve 4-letter words efficiently. The key features of this class include:

- Checking if a word exists in the dictionary.
- Retrieving a list of all words that start with a given prefix (e.g., all words that start with "TH").
- Using an internal `ArrayList<String>` to store words in alphabetical order.
- Using an `ArrayList<Integer>` to store indexes to optimize search performance.

### `SymCross.java`

The `SymCross` class is responsible for generating 4x4 symmetric crossword puzzles. A symmetric crossword is a crossword where:

- The word in row `i` is equal to the word in column `i`, for `i = 0, 1, 2, 3`.
- It systematically searches the dictionary to construct a valid crossword.
- Implements a method `makeCrossword(String firstWord)` that builds the crossword given a starting word.

## Usage

1. **Compile the Java files:**

   ```sh
   javac Dictionary.java SymCross.java
   ```

2. **Run the program:**

   ```sh
   java SymCross
   ```

3. **Example usage:**

   - Load the dictionary.
   - Search for words starting with a prefix.
   - Generate a symmetric crossword using a given starting word.

## How it Works

### Dictionary Operations

- The `Dictionary` class reads words from a file and stores them in a list.
- The `startsWith(String prefix)` method efficiently retrieves all words that match a given prefix.
- The `readFile()` method loads words from an external source and initializes an index list for faster lookup.

### Crossword Generation

- The `makeCrossword` method starts with a given first word.
- It recursively searches for words that satisfy the symmetric crossword constraints.
- The crossword is built by ensuring that the words in rows match the words in columns at each index.

## Future Improvements

- Extend the dictionary to support words of different lengths.
- Improve the efficiency of crossword generation.
- Provide a graphical interface to visualize the crossword puzzles.

## License

This project is licensed under the MIT License.

## Author

Pan, Enrong
