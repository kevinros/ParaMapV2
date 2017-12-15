package main.java.backend;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

/**
 * This class tags a given sentence
 */

public class Tagger {

    private POSModel model;
    private POSTaggerME tag;
    private POSSample tagsAndSentence;
    private String SentenceWithTags;
    private ArrayList<String> JustTags;
    private ArrayList<String> JustSentence;

    public Tagger() throws Exception {


        InputStream inputStream = new
                FileInputStream("src/models/en-pos-maxent.bin");
        this.model = new POSModel(inputStream);
        this.tag = new POSTaggerME(model);
        this.SentenceWithTags = "";
        this.JustTags = new ArrayList<>();
        this.JustSentence = new ArrayList<>();
    }

    //Returned a tagged array from a given sentence
    public void tagSentence(String sentence) {
        //Tokenizing the sentence using WhitespaceTokenizer class
        WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
        String[] tokens = whitespaceTokenizer.tokenize(sentence);

        //Generating tags
        String[] tags = tag.tag(tokens);
        this.tagsAndSentence = new POSSample(tokens, tags);

        //Sets the tag field
        this.JustTags.addAll(Arrays.asList(this.tagsAndSentence.getTags()));

        //Sets the sentence field
        this.JustSentence.addAll(Arrays.asList(this.tagsAndSentence.getSentence()));

        //Sets the Sentence with Tags field
        this.SentenceWithTags = tagsAndSentence.toString();
    }

    public ArrayList<String> getTags(){ return this.JustTags; }
    public ArrayList<String> getSentence(){
        return this.JustSentence;
    }
    public String getSentenceWithTags(){
        return this.SentenceWithTags;
    }

    /**
     * Clears the Tagger class to allow class reusability
     */
    public void clear() {
        this.JustTags.clear();
        this.JustSentence.clear();
        this.SentenceWithTags = "";
    }

    public static void main(String[] args) throws Exception {


        Tagger test = new Tagger();
        test.tagSentence("Saturn is a planet");
        String testSentenceAndTags = test.getSentenceWithTags();
        ArrayList<String> testTags = new ArrayList<>(test.getTags());
        ArrayList<String> testSentence = new ArrayList<>(test.getSentence());


     //   for(String s : testTags){
       //     System.out.println(s);
        //}
        for(String s : testSentence){
            System.out.println(s);
        }

        System.out.println(testSentenceAndTags);
        test.clear();
        test.tagSentence("A man is afraid");
        ArrayList<String> testSentence2 = new ArrayList<>(test.getSentence());
        for(String s : testSentence2){
            System.out.println(s);
        }




    }
}
