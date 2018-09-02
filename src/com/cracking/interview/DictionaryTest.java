package com.cracking.interview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DictionaryTest {

	public void test() {
		List<String> words = Arrays.asList("mus", "muslim", "must", "cat", "bus", "train", "train", "she", "shells",
				"shell", "within", "with");

		Dictionary dictionary = new Dictionary();

		words.stream().forEach(s -> {
			dictionary.addWord(s);
		});

		dictionary.findWordsThatStartWith("mus");
		List<String> printWords = dictionary.retrieveWordsAlphabetically();

		
		printWords.stream().forEach(s -> {
			System.out.println(s);
		});

		System.out.println(dictionary.retrieveAllWords());
		dictionary.deleteWord("she");
		System.out.println("******************");
		printWords = dictionary.retrieveWordsAlphabetically();

		printWords.stream().forEach(s -> {
			System.out.println(s);
		});

		System.out.println(dictionary.retrieveAllWords());

		System.out.println(dictionary.getTotalNumberOfWords());
	}

	public void loadDeclarationOfIndependence(String fileName) {

		Dictionary dictionary = new Dictionary();
		List<String> words = new ArrayList<>();
		try {
			Files.lines(Paths.get(fileName)).forEach(w -> {
				words.add(w);
				dictionary.addWord(w);

			});

			for(char alpha = 'a'; alpha<='z'; alpha++) {
				System.out.println(alpha+":"+dictionary.findWordsThatStartWith(String.valueOf(alpha)).size());
			}
			//int numOfWords = dictionary.getTotalNumberOfWords();
//			long sTime = System.nanoTime();
//			System.out.println(dictionary.getTotalNumberOfWords());
//			System.out.println(words.size());
//			long eTime=System.nanoTime();
//			System.out.println("Time took: "+(eTime-sTime)+" ns!");
			
			//System.out.println(numOfWords);
	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
