package main.java.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface tokenizer used to tokenize sentences.
 */
public interface Tokenizer {

    /**
     * interface method for tokenizing sentences.
     * @param sentence
     */
    public void tokenize(String sentence);

    /**
     * getTokens() returns the tokens as a List.
     * @return
     */
    public List getTokens();




}

