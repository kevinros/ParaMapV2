package main.java.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PoSFrequency implements FreqBuilder {
    /**
     * Builds a HashMap out of the frequencies of parts of speech appearing
     * in the input String.
     */
    /**
     * Initializes the Class variables Tags(an ArrayList of Strings), pOSFreqMap(an HashMap with a String key and an integer value),
     * , frequency (an Int), and a Tagger.
     *
     */
    //Class Variables
    private ArrayList<String> tags;
    private HashMap<String, Integer> pOSFreqMap;
    private int frequency;
    private Tagger tagger;

    public PoSFrequency() throws Exception {
        this.pOSFreqMap = new HashMap<String, Integer>();
        this.tagger = new Tagger();
        this.tags = new ArrayList<>();
    }

    /**
     * This takes in an ArrayList of tokens, tags them, and computes frequency of tags.
     */
    @Override
    public HashMap<String, Integer> buildFrequencyMap(String stringInput) {
        //Parameter 'tokens' is an ArrayList of 'Tags' which you can get by calling tagger.getTags()!
        tagger.tagSentence(stringInput);
        this.tags = tagger.getTags();

        //Determine frequency from tags ArrayList
        for (int i = 0; i < this.tags.size(); i++) {
            if (!(this.pOSFreqMap.containsKey(this.tags.get(i)))) {
                this.pOSFreqMap.put(this.tags.get(i), 1);
            } else {
                this.pOSFreqMap.put(this.tags.get(i), this.pOSFreqMap.get(this.tags.get(i)) + 1);
            }
        }

        convertNullToZeroes();
        return this.pOSFreqMap;

    }

    protected void convertNullToZeroes() {
        for (String key : this.pOSFreqMap.keySet()) {
            if (this.pOSFreqMap.get(key) == null) {
                this.pOSFreqMap.put(key, 0);
            }
        }
    }

    /**
     * This prints all POSs and their frequencies
     */
    @Override
    public void printFrequencyMap() {
        for(String POS : this.pOSFreqMap.keySet()){
            this.frequency = this.pOSFreqMap.get(POS);
        System.out.println("Frequency of "+ POS + " is: " + this.frequency);
        }
}
    /**
     * This prints the frequency of just one specified POS
     */
    public void getFrequency(String POS) {
        if(this.pOSFreqMap.containsKey(POS)){
            this.frequency = this.pOSFreqMap.get(POS);
        }
        else{
            this.frequency = 0;
        }
        System.out.println(this.frequency);
    }

}
