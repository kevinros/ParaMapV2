package General_Tools;

import java.util.ArrayList;
import java.util.HashMap;

public class TextSimplifier {

    private HashMap<String, Integer> wordFrequencyMap;
    private ArrayList<String> simplifiedSentences;
    private ArrayList<ArrayList<String>> tokenizedTaggedSentences;
    private String input;

    public TextSimplifier(String input) {
        this.input = input;
        this.wordFrequencyMap = new HashMap<>();
        this.simplifiedSentences = new ArrayList<>();
        this.tokenizedTaggedSentences = new ArrayList<>();
    }

    private void frequencyMap() throws Exception {
        WhiteSpaceTokenizer wst = new WhiteSpaceTokenizer();
        WordFrequency wf = new WordFrequency();
        ArrayList<String> tokens;

        wst.tokenize(this.input);
        tokens = wst.getTokens();

        this.wordFrequencyMap = wf.buildFrequencyMap(tokens);
    }

    public HashMap<String, Integer> getWordFrequencyMap() throws Exception {
        this.frequencyMap();
        return this.wordFrequencyMap;
    }

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

    private void resolvePronouns() throws Exception {
        WhiteSpaceTokenizer wst = new WhiteSpaceTokenizer();
        ArrayList<String> subjects = new ArrayList<>();

        // Tokenize the tagged sentences by whitespace so that we can extract individual
        //  words for replacement.
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

    private void replacePronounsWithNouns(ArrayList<String> subjects) {
        for (int i = 0; i < this.tokenizedTaggedSentences.size(); i++) {
            for (int j = 0; j < this.tokenizedTaggedSentences.get(i).size(); j++) {
                if (this.tokenizedTaggedSentences.get(i).get(j).contains("_PRP")) {
                    this.tokenizedTaggedSentences.get(i).set(j, subjects.get(i-1));
                    subjects.set(i, subjects.get(i-1));
                }
            }
        }
    }

    private void removeTags() {
        for (int i = 0; i < this.simplifiedSentences.size(); i++) {
            String str = this.simplifiedSentences.get(i);
            this.simplifiedSentences.set(i, str.replaceAll("(_(\\w|\\p{Punct})+)",""));
        }
    }

    public ArrayList<String> getSimplifiedSentences() throws Exception {
        this.tagSentences();
        return this.simplifiedSentences;
    }
}
