package main.java.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordFrequency implements FreqBuilder{
    // The WordFrequency's purpose is to map how many times each token occurs in a String input.

    // Class Variables
    private HashMap<String, Integer> frequencyMap;
    private WhiteSpaceTokenizer wsTokenizer;
    private ArrayList<String> tokens;

    // WordFrequency() instantiates an empty HashMap<String, Integer> which will hold the frequency
    //  for each String token.
    public WordFrequency() throws Exception{

        this.frequencyMap = new HashMap<String, Integer>();
        this.wsTokenizer = new WhiteSpaceTokenizer();

    }

    // findFrequency(List<String>) takes in a list of Strings and records the frequency of their occurrence in
    //  a HashMap
    @Override
    public HashMap<String, Integer> buildFrequencyMap(String stringInput) {

        this.frequencyMap.clear(); // Allows findFrequency to be reusable. Therefore, only one
        // object of WordFrequency needs to be instantiated.

        this.wsTokenizer.tokenize(stringInput);
        this.tokens = this.wsTokenizer.getTokens();

        for (int i = 0; i < this.tokens.size(); i++) {
            if (!this.frequencyMap.containsKey(this.tokens.get(i).toLowerCase())) {
                this.frequencyMap.put(this.tokens.get(i).toLowerCase(), 1);
            } else {
                this.frequencyMap.put(this.tokens.get(i).toLowerCase(), this.frequencyMap.get(this.tokens.get(i).toLowerCase())+1);
            }
        }

        return this.frequencyMap;

    }

    // printFrequency() prints out the contents of frequencyMap in the form <String> : <frequency (int)>
    @Override
    public void printFrequencyMap() {
        for (String token : this.frequencyMap.keySet()) {
            System.out.printf("%s : %d\n", token, this.frequencyMap.get(token));
        }
    }


}
