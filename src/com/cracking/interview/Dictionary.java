package com.cracking.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Dictionary {

	private TrieNode trieNode = new TrieNode();

	private final static Set<Character> validCharacters = new TreeSet<>();

	class TrieNode {

		Map<String, TrieNode> nextLetters = new HashMap<>();
		boolean isWord;
		int occurences = 0;

	}

	static {
		Character[] specialCharacters = { ' ', '\'', '-', ',', '_', '.', '/' };

		for (Character specialCharacter : specialCharacters) {

			validCharacters.add(specialCharacter);
		}

		for (char num = '0'; num <= '9'; num++) {
			validCharacters.add(num);
		}

		for (char alpha = 'a'; alpha <= 'z'; alpha++) {
			validCharacters.add(alpha);
		}

	}

	public void addWord(String word) {
		word = word.toLowerCase();
		TrieNode currentNode = trieNode;
		for (int i = 0; i < word.length(); i++) {
			TrieNode newNode = currentNode.nextLetters.get(String.valueOf(word.charAt(i)));
			if (newNode == null) {
				newNode = new TrieNode();
			}
		
			currentNode.nextLetters.put(String.valueOf(word.charAt(i)), newNode);
			if (i == (word.length() - 1)) {
				newNode.isWord = true;
				newNode.occurences++;
			}
			currentNode = newNode;

		}
	}

	public boolean findWord(String word) {

		return findNode(word).isWord;
	}

	public boolean isPrefix(String word) {

		return findNode(word) != null;
	}

	public List<String> findWordsThatStartWith(String word) {
		List<String> words = new ArrayList<>();
		TrieNode currentNode = findNode(word);
		if (currentNode != null) {
			retrieveWords(currentNode, words,
					new StringBuilder(word),null);
		}
		return words;
	}

	private TrieNode findNode(String word) {
		word = word.toLowerCase();
		TrieNode currentNode = trieNode;
		for (int i = 0; i < word.length(); i++) {
			currentNode = currentNode.nextLetters.get(String.valueOf(word.charAt(i)));
			if (currentNode == null) {
				return null;
			}
			if (i == (word.length() - 1)) {
				return currentNode;
			}

		}
		return currentNode;

	}

	public boolean deleteWord(String word) {
		word = word.toLowerCase();

		TrieNode currentNode = trieNode;
		TrieNode deleteNode = currentNode;
		String deleteLetter = String.valueOf(word.charAt(0));
		for (int i = 0; i < word.length(); i++) {
			TrieNode tempNode = currentNode.nextLetters.get(String.valueOf(word.charAt(i)));
			if (tempNode == null) {
				return false;
			}
			if (tempNode.nextLetters.size() == 0) {
				if (i == (word.length() - 1)) {

					deleteNode.nextLetters.remove(deleteLetter);
					return true;

				}
			} else if (tempNode.nextLetters.size() == 1) {
				if (i == (word.length() - 1)) {

					tempNode.isWord = false;
					return true;

				} else if (tempNode.isWord) {
					deleteNode = tempNode;
					deleteLetter = String.valueOf(word.charAt(i + 1));
				}
			} else {
				if (i == (word.length() - 1)) {

					tempNode.isWord = false;
					return true;

				} else {
					deleteNode = tempNode;
					deleteLetter = String.valueOf(word.charAt(i + 1));
				}
			}
			currentNode = tempNode;

		}
		return false;

	}

	public List<String> retrieveWordsAlphabetically() {

		List<String> words = new ArrayList<String>();
		TrieNode currentNode = trieNode;
		retrieveWords(currentNode, words, new StringBuilder(),null);
		return words;
	}

	public List<String> retrieveWordsReverseAlphabetically() {
		List<String> words = retrieveWordsAlphabetically();
		Collections.reverse(words);
		return words;
	}

	private void retrieveWords(TrieNode currentNode, List<String> words, StringBuilder word, String letter) {

		if (currentNode == null) {
			return;
		}
		if (letter != null) {
			word.append(letter);
		}

		if (currentNode.isWord) {
			words.add(word.toString());
		}

		if (currentNode.nextLetters.size() > 0) {
			for (Character validCharacter : validCharacters) {
				retrieveWords(currentNode.nextLetters.get(String.valueOf(validCharacter)), words, word,String.valueOf(validCharacter));
			}
		}
		if (letter != null) {
			word.deleteCharAt(word.length() - 1);
		}

	}

	public Map<String, Integer> retrieveAllWords() {
		Map<String, Integer> words = new HashMap<>();
		TrieNode currentNode = trieNode;
		retrieveAllWords(currentNode, words, new StringBuilder(),null);
		return words;
	}

	private void retrieveAllWords(TrieNode currentNode, Map<String, Integer> words, StringBuilder word, String letter) {

		if (currentNode == null) {
			return;
		}
		if (letter != null) {
			word.append(letter);
		}
		if (currentNode.isWord) {
			words.put(word.toString(), currentNode.occurences);
		}
		if (currentNode.nextLetters.size() > 0) {
			for (Character validCharacter : validCharacters) {
				retrieveAllWords(currentNode.nextLetters.get(String.valueOf(validCharacter)), words, word,String.valueOf(validCharacter));
			}
		}
		if (letter != null) {
			word.deleteCharAt(word.length() - 1);
		}
	}

	public int numberOfOccurences(String word) {
		TrieNode currentNode = trieNode;
		if (currentNode != null) {
			return currentNode.occurences;
		}
		return 0;
	}

	public int getTotalNumberOfUniqueWords() {

		TrieNode currentNode = trieNode;
		return getTotalNumberOfWords(currentNode, true);
	}

	public int getTotalNumberOfWords() {
		TrieNode currentNode = trieNode;
		return getTotalNumberOfWords(currentNode, false);
	}

	private int getTotalNumberOfWords(TrieNode currentNode, boolean isUnique) {

		if (currentNode == null) {
			return 0;
		}

		int total = (isUnique) ? (currentNode.occurences > 0) ? 1 : 0 : currentNode.occurences;
		for (Map.Entry<String, TrieNode> nextLetters : currentNode.nextLetters.entrySet()) {

			total += getTotalNumberOfWords(nextLetters.getValue(), isUnique);
		}

		return total;
	}



	public void destroy() {
		trieNode.nextLetters.clear();
	}
}
