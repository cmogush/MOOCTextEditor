package textgen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// DONE: Implement this method
		//first get the words, including punctuation
		List<String> words = getTokens("[^ ]+", sourceText); 
		
		//set starter to first word in the text
		if(words.size() == 0) {
			words.add("");
		}
		else { starter = words.get(0); }
		
		//set previous word to "starter"
		String prevWord = starter;
		
		ListNode first = new ListNode(starter);
		wordList.add(first);
		
		//iterate over list of words
		for(int k=1; k < words.size(); k++) {
			String w = words.get(k);
			
			//if prevWord is already node in the list, add "w" as a nextWord to the prevWord
			//else add a new node to the list with the previous word as the node's word
			//then add that new node to the wordList
			
			//getNode is a helper function that either returns a node if it already exists
			//or creates a new node and adds it to the wordList
			getNode(prevWord).addNextWord(w);
						
			//set new prevWord
			prevWord = w;
			

			//add starter word to be next word for last node of list
			if(k == words.size()-1) {
				ListNode newWord = new ListNode(prevWord);
				newWord.addNextWord(starter);
				wordList.add(newWord);
			}
		}
		
		/*for(ListNode l : wordList) {
			System.out.println(l.toString());
		}*/
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // DONE: Implement this method

		if(numWords <=0) {
			return "";
		}
		
		if(wordList.size() <= 0) {
			return "";
		}
		
		//set "currWord" to be the starter word
		 String currWord = starter;
		 
		//set "output" to be ""
		 String output = "";
		 
		//add "currWord" to output
		 output += currWord;
		
		 //while you need more words
		 for(int wordsAdded = 1; wordsAdded  < numWords; wordsAdded++) {
			 //find the "node" corresponding to "currWord" in the list
			 //then select a random word "w" from the "wordList" for "node"

			 String w = getNode(currWord).getRandomNextWord(rnGenerator);
			 
			//add "w" to the "output"
			 output += " " + w;
			 
			//set "currWord" to be "w" 
			 currWord = w;
		 }
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// DONE: Implement this method.
		starter = "";
		wordList.clear();
		train(sourceText);
	}
	
	// DONE: Add any private helper methods you need here.
	//getTokens helper function
	protected List<String> getTokens(String pattern, String text)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	//method to see if word is already in wordList
	private ListNode getNode(String prevWord) {
		for(ListNode l : wordList) {
			if(l.getWord() != null) { //safety check
				if(l.getWord().equals(prevWord)) {
					return l;
				}
			}
		}
		//if none found, return empty ListNode
		ListNode newNode = new ListNode(prevWord);
		wordList.add(newNode);
		return newNode;
	}
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		String testText = "hi there hi Leo";
		MarkovTextGeneratorLoL gen2 = new MarkovTextGeneratorLoL(new Random(42));
		gen2.train(testText);
		System.out.println(gen2.toString());
		System.out.println(gen2.generateText(20));
		String testText2 = "hi there hi Bob";
		gen2.retrain(testText2);
		System.out.println(gen2.toString());
		System.out.println(gen2.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		if(nextWords.size() > 0) {
			int wordIndex = generator.nextInt(nextWords.size());
			return nextWords.get(wordIndex);
		}
	    return "";
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


