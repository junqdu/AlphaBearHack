package jotaro.alphabearhack;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Scanner;

// AlphaBearSolver contains a dictionary, and can take a string of letter then produce
//     a list of words that contains the input letters
public class AlphaBearSolver implements Runnable {
	private ArrayList<LetterInventory> dict;
	private Scanner dictionary;
	private Button b;

	// Construct AlphaBearSolver with a dictionary
	public AlphaBearSolver(Scanner dictionary, Button b) {
		this.dictionary = dictionary;
		this.b = b;
	}

	// Takes a string of letter and produce a list of words that made from the input letters
	// For example, input:"der", ouput:"red,..."
	public ArrayList<String> getWords(String input) {
		int max = 0;
		ArrayList<String> results = new ArrayList<String>();
		LetterInventory in = new LetterInventory(input);
		for(LetterInventory li:dict){			
			if(li.getOriginal().length() <= in.getOriginal().length() && li.getOriginal().length() >= max-1 && in.subtract(li) != null){
				max = li.getOriginal().length();
				results.add(li.getOriginal());
			}
		}
		java.util.Collections.sort(results, new StringLengthComparator(""));
		return results;
	}

	@Override
	public void run() {
		dict = new ArrayList<LetterInventory>();
		while(dictionary.hasNextLine()){
			String current = dictionary.nextLine();
			dict.add(new LetterInventory(current));
		}
	}
}
