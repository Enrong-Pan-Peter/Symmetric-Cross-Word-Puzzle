package crossword;

import java.util.ArrayList;

import dictionary.Dictionary;

/**
 * A class for creating 4x4 symmetric crossword puzzles. A symmetric crossword
 * is a crossword where the word in row i is equal to the word in column i where
 * i = 0, 1, 2, 3 are the indexes of the rows and columns. Words of the puzzle
 * are always in uppercase.
 * 
 * <p>
 * To use this class, one creates a new {@code SymCross} object using the
 * no-argument constructor which initializes the crossword so that all of the
 * words are {@code XXXX}. This creates a symmetric crossword, but not a
 * complete crossword. A complete crossword is a symmetric crossword where all
 * of the words are 4-letter English words contained in a {@code Dictionary}
 * object.
 * 
 * <p>
 * To create a complete crossword, one adds words to the crossword using the
 * {@code setWord} method until the crossword is complete. {@code setWord} does
 * not enforce the symmetry property of the crossword; it is the responsibility
 * of the user to confirm that the crossword is symmetric and complete (perhaps
 * with the help of the {@code isSymmetric} and {@code isComplete} methods).
 * 
 * <p>
 * Alternatively, one may ask the class to create a crossword by specifying the
 * first word of the crossword (the word in the first row) using the static
 * method {@code makeCrossword}. This method searches a {@code Dictionary} for
 * words that satisfy the symmetry property of the crossword. Some starting
 * words do not lead to a complete crossword.
 *
 */
public class SymCross {

	/**
	 * A static dictionary used by all instances of the class.
	 */
	private static Dictionary dict = new Dictionary();

	/**
	 * The list of words in this puzzle stored in row order (word in the first row
	 * of the puzzle is the first in the list).
	 */
	private ArrayList<String> words;

	/**
	 * Initializes this crossword so that all four words are "XXXX".
	 */
	public SymCross() {
		
		this.words = new ArrayList<String>();
		
		for (int i = 0; i< 4; i++) {
			String temp = "";
			for (int j = 0; j < 4; j++) {
				temp = temp + 'X';
			}
			this.words.add(temp);
		}
	}

	/*
	 * Add the required constructors and methods after this comment.
	 */
	
	public SymCross(SymCross other) {
		
		this.words = new ArrayList<String>();
		
		for (int i = 0; i < 4; i++) {
			this.words.set(i, other.words.get(i));
		}
	}
	
	
	public void setWord(int row, String word) {
			
		word = word.toUpperCase();
		
		if (row < 0 || row > 3) {
			throw new IllegalArgumentException("row index is not valid");
		}
		
		if (word.length() != 4) {
			throw new IllegalArgumentException("word length is not 4");
		}
		
		this.words.set(row, word);
			
		}

	
	public String getRow(int row) {
		
		if (row < 0 || row > 3) {
			throw new IllegalArgumentException("row index not valid");
		}
		
		return this.words.get(row);
	}
	
	
	public String getColumn(int col) {
		
		if (col < 0 || col > 3) {
			throw new IllegalArgumentException("row index not valid");
		}
		
		String temp = "";
		
		for (int i = 0; i < 4; i++) {
			temp = temp + this.words.get(i).charAt(col);
		}
		
		return temp;
	}
	
	
	public boolean isSymmetric() {
			
		for (int i = 0; i < 4; i++) {
			if (!(this.getRow(i).equals(this.getColumn(i)))) {
				return false;
			}
		}
		
		return true;
		}
	
	
	public boolean isComplete() {
		
		boolean temp = true;
		
		if (this.isSymmetric()) {
			for (int i = 0; i < 4; i++) {
				if (!(dict.contains(this.words.get(i)))){
					temp = false;
				}
			}
		}
		else {
			temp = false;
		}
		
		return temp;
	}
	
	
	public static SymCross makeCrossword(String first) {
		
		SymCross puzzle = new SymCross();
		
		if (dict.contains(first)) {
			
			puzzle.setWord(0, first);
			
			//ArrayList<String> col1 = dict.startsWith(puzzle.getColumn(0).substring(0,1));
			ArrayList<String> col2 = dict.startsWith("" + first.charAt(1));
			ArrayList<String> col3 = dict.startsWith("" + first.charAt(2));
			ArrayList<String> col4 = dict.startsWith("" + first.charAt(3));
			
			for (String c1 : col2) {
				puzzle.setWord(1, c1);
				for (String c2 : col3) {
					puzzle.setWord(2, c2);
					for (String c3 : col4) {
						puzzle.setWord(3, c3);
						if (puzzle.isSymmetric()) {
							return puzzle;
						}
					}
				}
			}
		}
		
		return puzzle;
	}
		
	
	
	public String toString() {
		
		String puzzle = "";
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				puzzle = puzzle + this.words.get(i).charAt(j) + " ";
			}

			puzzle = puzzle + "\n";
		}
		
		puzzle = puzzle.substring(0, puzzle.length() - 1);
		return puzzle;
	}
	
	
	/**
	 * For testing purposes. Students are encouraged to modify this method to
	 * further test their code.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		String first = "COST";
		SymCross cw = SymCross.makeCrossword(first);
		if (cw.isComplete()) {
			for (int i = 0; i < 4; i++) {
				System.out.println(cw.getRow(i));
			}
		} else {
			System.out.println("no crossword starting with " + first + " exists");
		}
		
		System.out.println();
		System.out.println(cw.toString());

	}

}
