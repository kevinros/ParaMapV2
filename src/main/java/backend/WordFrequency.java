package main.java.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * WordFrequency implements FreqBuilder and The its purpose is to map
 * how many times each token occurs in a String input.
 */
public class WordFrequency implements FreqBuilder{


    /**
     * frequencyMap is a hashmap that stores each word and the
     * the number of times it appears in the input.
     */
    private HashMap<String, Integer> frequencyMap;
    /**
     * wsTokenizer is a WhiteSpaceTokenizer instance used to
     * tokenize the sentences.
     */
    private WhiteSpaceTokenizer wsTokenizer;
    /**
     * tokens is an ArrayList that stores each word.
     */
    private ArrayList<String> tokens;


    /**
     * WordFrequency() instantiates an empty HashMap<String, Integer> which
     * will hold the frequency for each String token.
     */
    public WordFrequency() throws Exception{

        this.frequencyMap = new HashMap<String, Integer>();
        this.wsTokenizer = new WhiteSpaceTokenizer();

    }

    /**
     *     findFrequency(List<String>) takes in a list of Strings and
     *     records the frequency of their occurrence in a HashMap.
     */
    @Override
    public HashMap<String, Integer> buildFrequencyMap(String stringInput) {

        this.frequencyMap.clear();
        this.wsTokenizer.tokenize(stringInput);
        this.tokens = this.wsTokenizer.getTokens();

        for (int i = 0; i < this.tokens.size(); i++) {
            if (!this.frequencyMap.containsKey(this.tokens.get(i).toLowerCase().replaceAll("\\s*\\p{Punct}+\\s*$", ""))) {
                this.frequencyMap.put(this.tokens.get(i).toLowerCase().replaceAll("\\s*\\p{Punct}+\\s*$", ""), 1);
            } else {
                this.frequencyMap.put(this.tokens.get(i).toLowerCase().replaceAll("\\s*\\p{Punct}+\\s*$", ""),
                        this.frequencyMap.get(this.tokens.get(i).toLowerCase().replaceAll("\\s*\\p{Punct}+\\s*$", ""))+1);
            }
        }

        return this.frequencyMap;

    }

    /**
     *  printFrequency() prints out the contents of frequencyMap in the form <String> : <frequency (int)>.
     */
    @Override
    public void printFrequencyMap() {
        for (String token : this.frequencyMap.keySet()) {
            System.out.printf("%s : %d\n", token, this.frequencyMap.get(token));
        }
    }


}

