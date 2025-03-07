package dictionary;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * A simple dictionary of 4-letter English words that appear in the official
 * list of Scrabble words. This dictionary allows the user to test if a word
 * exists in the dictionary and allows the user to retrieve a list of all of the
 * words that start with a given string (for example, find all of the words that
 * start with "TH").
 *
 */
public class Dictionary {

	/**
	 * The list of words in this dictionary in sorted alphabetic order.
	 */
	private ArrayList<String> words;

	/**
	 * The list of 26 indexes in this.words for the first word starting with A, B,
	 * C, ... That is:
	 * 
	 * indexOfLetter.get(0) is the index of the first word starting with A
	 * indexOfLetter.get(1) is the index of the first word starting with B
	 * indexOfLetter.get(2) is the index of the first word starting with C and so on
	 */
	private ArrayList<Integer> indexOfLetter;

	/**
	 * Initializes this dictionary according to the description in the assignment
	 * document.
	 */
	public Dictionary() {
		// Do not change the next two lines...
		this.words = new ArrayList<>();
		this.readFile();
		
		System.out.println(words);
		//System.out.println(words.size());

		// Complete this constructor...
		this.indexOfLetter = new ArrayList<Integer>(26); // create a list of 26 entries
		
		for (int i = 0; i < 26; i ++) {
			this.indexOfLetter.add(-1); //fill the list with default -1 values
		}
		
		for (int i = 0; i < words.size(); i++) {
			char firstLetterOfWord = words.get(i).charAt(0);
			int letterIndex = firstLetterOfWord - 'A';
			
			if (indexOfLetter.get(letterIndex) == -1) {
				indexOfLetter.set(letterIndex, i);
			}
		}
	}

	/**
	 * Reads the words of this dictionary from the file found in words/four.txt
	 * 
	 * <p>
	 * The words in the file are already in sorted alphabetic order.
	 * 
	 * <p>
	 * ALREADY IMPLEMENTED FOR YOU.
	 */
	void readFile() {
		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
		Path filePath = Paths.get(root.toString(), "words", "four.txt");
		String path = filePath.toString();
		
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(this.words::add);
		} 
		catch (IOException ex) {
			throw new RuntimeException("error reading dictionary file");
		}
	}

	
	/*
	 * Add the required methods after this comment.
	 */
	
	public boolean contains(String word) {
		// the case of word is ignored when searching this dictionary
		
		word = word.toUpperCase();
		
		for (int i = 0; i < this.words.size(); i++) {
			if (this.words.get(i).equals(word)) {
				return true;
			}	
		}
		
		return false;

	}
	
	private int indexOf(int c) {
		
		if (c < 'A') {
			return 0;
		}
		
		if (c > 'Z') {
			return this.words.size();
		}
		
		int letterIndex = c - 'A';
		
		return this.indexOfLetter.get(letterIndex);
		
	}
	
	public ArrayList<String> startsWith(String prefix){
		
		ArrayList<String> wordList = new ArrayList<>();
		
		char firstChar = prefix.charAt(0);
		int letterIndex = this.indexOf(firstChar);
		
		if (prefix.equals("")) {
			wordList.addAll(this.words);
		}
		else if (prefix.length() > 4){
			wordList.clear();
		}
		else {
			for (int i = letterIndex; i < this.indexOf(firstChar + 1); i++){
				
				boolean tempBoo = true;
				String tempString = this.words.get(i);
				
				for (int j = 0; j < prefix.length(); j++) {
					if (tempString.charAt(j) != prefix.charAt(j)) {
						tempBoo = false;
					}
				}
				
				if (tempBoo){
					wordList.add(this.words.get(i));
				}
			}
		}
		
		return wordList;
	}
	

	/**
	 * For testing purposes. Students are encouraged to modify this method to
	 * further test their code.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		System.out.println("Contains \"SORT\"? " + d.contains("SORT"));
		System.out.println("Contains \"EROS\"? " + d.contains("EROS"));
		System.out.println("Contains \"AH\"? " + d.contains("AH"));
		System.out.println("Contains \"AAH\"? " + d.contains("AAH"));
		System.out.println("Contains \"YODE\"? " + d.contains("YODE"));
		System.out.println("Contains \"ZYME\"? " + d.contains("ZYME"));
		System.out.println("Contains \"ZYMM\"? " + d.contains("ZYMM"));
		System.out.println("Words starting with \"AB\"? " + d.startsWith("AB"));
		System.out.println("Words starting with \"DE\"? " + d.startsWith("DE"));
		System.out.println("Words starting with \"GECEYI\"? " + d.startsWith("GECEYI"));
		System.out.println("Words starting with \"AAAA\"? " + d.startsWith("AAAA"));
		System.out.println("Index of \"A\"? " + d.indexOf('A'));
		System.out.println("Index of \"B\"? " + d.indexOf('B'));
		System.out.println("Index of \"J\"? " + d.indexOf('J'));
		System.out.println("Index of \"Z\"? " + d.indexOf('Z'));
	}
}
