package main.java.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Every type of frequency builder will have these methods.
 */
public interface FreqBuilder {

    /**
     * Makes hashmap between Strings and Integers
     * to count frequency of something. 
     * @param tokens tokenized sentence.
     * @return HashMap of string to int.
     */
    public HashMap<String, Integer> buildFrequencyMap(ArrayList<String> tokens);

    /**
     * Prints the hashmap of strings to integers.
     */
    public void printFrequencyMap();
}


