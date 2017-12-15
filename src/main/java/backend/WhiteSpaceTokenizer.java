package main.java.backend;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * The WhiteSpaceTokenizer's purpose is to tokenize a String input based on
 * OpenNLP's whitespace tokenizer model (i.e. every word).
 */

public class WhiteSpaceTokenizer implements Tokenizer {


    /**
     * wst is an instance of WhitespaceTokenizer used to tokenize sentences.
     */
    private WhitespaceTokenizer wst;

    /**
     * tokens is an ArrayList used to store tokens.
     */
    private ArrayList<String> tokens;

    /**
     * WhiteSpaceTokenizer() creates a tokenizer model and instantiates an
     * ArrayList<String> to hold the tokenized tokens.
     */
    public WhiteSpaceTokenizer() throws Exception {

        this.wst = WhitespaceTokenizer.INSTANCE;
        this.tokens = new ArrayList<>();

    }

    /**
     * tokenize(String) takes in a String, in this case a sentence, and splits it by whitespace using the tokenizer.
     * @param sentence
     */
    public void tokenize(String sentence) {
        String[] tokenArray = this.wst.tokenize(sentence);
        this.tokens = new ArrayList<>(Arrays.asList(tokenArray));
        this.removePunctuations();

    }

    /**
     * removePunctuation() removes any punctuation found in the token list.
     */
    public void removePunctuations() {
        for (int i = 0; i < this.tokens.size(); i++) {
            // If our token is a punctuation mark, we want to remove it.
            if (Pattern.matches("\\p{Punct}", this.tokens.get(i))) {
                this.tokens.remove(i);
            }

        }
    }

    /**
     * getTokens() returns the list of tokens
     */
    public ArrayList<String> getTokens() {
        return this.tokens;
    }

}


