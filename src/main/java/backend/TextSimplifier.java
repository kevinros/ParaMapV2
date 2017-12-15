package main.java.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * TextSimplifier uses interfaces and instances of other classes to break down and simplify the input.
 */
public class TextSimplifier {

    /**
     * wordFrequencyMap is a HashMap that stores words and their corresponding count.
     */
    private HashMap<String, Integer> wordFrequencyMap;

    /**
     * simplifiedSentences is an ArratList that stores strings post-simplification.
     */
    private ArrayList<String> simplifiedSentences;
    /**
     * tokenizedTaggedSentences is an ArratList of ArrayLists that stores strings and their POS (part of speech).
     */
    private ArrayList<ArrayList<String>> tokenizedTaggedSentences;
    /**
     * input is an String that holds the users input.
     */
    private String input;

    /**
     * TextSimplifier takes the user input and creates new instances of the variables declared above.
     * @param input
     */
    public TextSimplifier(String input) {
        this.input = input;
        this.wordFrequencyMap = new HashMap<>();
        this.simplifiedSentences = new ArrayList<>();
        this.tokenizedTaggedSentences = new ArrayList<>();
    }

    /**
     * frequencyMap makes a hashmap with each word mapped with its frequency.
     */
    private void frequencyMap() throws Exception {
        WhiteSpaceTokenizer wst = new WhiteSpaceTokenizer();
        WordFrequency wf = new WordFrequency();

        this.wordFrequencyMap = wf.buildFrequencyMap(this.input);
    }

    /**
     * getWordFrequencyMap() returns the HashMap of the word-frequency map.
     * @return
     * @throws Exception
     */
    public HashMap<String, Integer> getWordFrequencyMap() throws Exception {
        this.frequencyMap();
        return this.wordFrequencyMap;
    }

    /**
     * tagSentences tags the sentences using OpenNLP tagger.
     * @throws Exception
     */
    private void tagSentences() throws Exception {
        ArrayList<String> tokens;
        SentenceTokenizer st = new SentenceTokenizer();
        Tagger tagger = new Tagger();

        st.tokenize(this.input);
        tokens = st.getTokens();

        for (String token : tokens) {
            tagger.tagSentence(token);
            this.simplifiedSentences.add(tagger.getSentenceWithTags());
        }

        this.resolvePronouns();

    }

    /**
     * resolvePronouns() deals with the pronouns in the input, converting them to appropriate nouns.
     * @throws Exception
     */
    private void resolvePronouns() throws Exception {
        WhiteSpaceTokenizer wst = new WhiteSpaceTokenizer();
        ArrayList<String> subjects = new ArrayList<>();

        for (String sentence : this.simplifiedSentences) {
            wst.tokenize(sentence);
            this.tokenizedTaggedSentences.add(wst.getTokens());
        }

        subjects = createSubjectList();
        this.replacePronounsWithNouns(subjects);

        for (int i = 0; i < this.tokenizedTaggedSentences.size(); i++) {
            String sentence = "";
            for (int j = 0; j < this.tokenizedTaggedSentences.get(i).size()-1; j++) {
                sentence += this.tokenizedTaggedSentences.get(i).get(j) + " ";
            }
            sentence += this.tokenizedTaggedSentences.get(i).get(this.tokenizedTaggedSentences.get(i).size()-1);
            this.simplifiedSentences.set(i, sentence);
        }

        this.removeTags();

    }

    /**
     * createSubjectList creates an ArrayList of strings by going through the tagged sentences
     * and extracts the subjects.
     *
     * @return
     */
    private ArrayList<String> createSubjectList() {
        ArrayList<String> subjects = new ArrayList<>();

        for (int i = 0; i < this.tokenizedTaggedSentences.size(); i++) {
            for (int j = 0; j < this.tokenizedTaggedSentences.get(i).size(); j++) {
                if (this.tokenizedTaggedSentences.get(i).get(j).contains("_PRP") ||
                        this.tokenizedTaggedSentences.get(i).get(j).contains("_NN")) {
                    subjects.add(this.tokenizedTaggedSentences.get(i).get(j));
                    break;
                }

            }
        }

        return subjects;
    }

    /**
     * Refer to resolvePronouns [helper function].
     * @param subjects
     */
    private void replacePronounsWithNouns(ArrayList<String> subjects) {
        for (int i = 0; i < this.tokenizedTaggedSentences.size(); i++) {
            for (int j = 0; j < this.tokenizedTaggedSentences.get(i).size(); j++) {
                if (this.tokenizedTaggedSentences.get(i).get(j).contains("_PRP")) {
                    this.tokenizedTaggedSentences.get(i).set(j, subjects.get(i-1));
                    subjects.set(i, subjects.get(i-1));
                    // we just need to change the pronoun in the beginning, break out of loop
                    //break;
                }
            }
        }
    }

    /**
     * removeTags strips the attached tags from the tagged-and-pronoun-resolved sentences.
     */
    private void removeTags() {
        for (int i = 0; i < this.simplifiedSentences.size(); i++) {
            String str = this.simplifiedSentences.get(i);
            this.simplifiedSentences.set(i, str.replaceAll("(_(\\w|\\p{Punct})+)",""));
        }
    }

    /**
     * getSimplifiedSentences returns the final version of the simplified sentence.
     * @return
     * @throws Exception
     */
    public ArrayList<String> getSimplifiedSentences() throws Exception {
        this.tagSentences();
        return this.simplifiedSentences;
    }
}

