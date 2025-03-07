# Symmetric Crossword Puzzle Generator

A web-based application that generates 4x4 symmetric crossword puzzles. This project is a JavaScript port of a Java application with the same functionality.

## Overview

This project implements a symmetric crossword puzzle generator. A symmetric crossword is a crossword where the word in row i is equal to the word in column i, where i = 0, 1, 2, 3 are the indexes of the rows and columns. Words of the puzzle are always in uppercase.

The application uses a dictionary of 4-letter English words found in the official Scrabble word list to generate valid crossword puzzles.

## Features

- Generate a crossword puzzle from a user-provided 4-letter word
- Create random puzzles using words from the dictionary
- Animated UI with a clean, modern design
- Responsive layout that works on mobile and desktop devices
- Input validation and error handling
- Visual feedback during puzzle generation

## File Details

### index.html
- Contains the HTML structure
- Includes Tailwind CSS via CDN
- Sets up the user interface with input field, buttons, and grid

### js/main.js
- Manages UI interactions
- Handles button clicks and user input
- Displays the crossword grid
- Shows status messages

### js/dictionary.js
- Loads the word list from four.txt
- Provides methods to search for words
- Indexes words for efficient lookup

### js/symCross.js
- Implements the crossword generation algorithm
- Creates and validates 4x4 symmetric puzzles
- Ensures word symmetry across rows and columns

### data/four.txt
- Contains the list of valid 4-letter words
- Used as the dictionary for the puzzle generator

### original_java/SymCross.java
- Original java code for symCross.js

### original_java/Dictionary.java
- Original java code for dictionary.js

## How It Works

### Dictionary Class

The Dictionary class provides functionality to store and retrieve 4-letter words efficiently:
- Loads the dictionary from a text file
- Checks if a word exists in the dictionary
- Retrieves a list of all words that start with a given prefix
- Uses optimized indexing to speed up word lookups

### SymCross Class

The SymCross class is responsible for generating symmetric crossword puzzles:
- Takes a starting word (first row of the puzzle)
- Searches for compatible words that maintain the symmetry property
- Builds a 4x4 grid where each row i and column i contain the same word

### UI/UX

- User enters a 4-letter word or clicks "Random"
- Application validates the input and generates the puzzle
- Crossword grid animates into view with each cell fading in sequentially
- Status messages provide feedback on the generation process

## Technologies Used

- **JavaScript** - Core application logic
- **HTML5** - Structure and content
- **Tailwind CSS** - Styling and responsive design
- **CSS Animations** - Enhanced user experience
- **Fetch API** - Asynchronous dictionary loading

## How to Use

1. Clone the repository: https://github.com/Enrong-Pan-Peter/Symmetric-Cross-Word-Puzzle.git
2. Open the project in VS Code and use the Live Server extension to run the application.

Alternatively, you can click on the link provided here to run it locally: https://somelink

3. Open the application in your browser.
4. Enter a 4-letter word in the input field.
5. Click "Generate" to create a crossword puzzle starting with your word.
6. Alternatively, click "Random" to generate a puzzle with a randomly selected word.
7. If the puzzle cannot be generated with the given word, an error message will appear.
