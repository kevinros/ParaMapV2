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
     * @param stringInput string of sentences.
     * @return HashMap of string to int.
     */
    public HashMap<String, Integer> buildFrequencyMap(String stringInput);

    /**
     * Prints the hashmap of strings to integers.
     */
    public void printFrequencyMap();
}


