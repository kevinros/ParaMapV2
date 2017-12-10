package General_Tools;

import java.util.ArrayList;
import java.util.HashMap;

public class TextSimplifier {

    private HashMap<String, Integer> wordFrequencyMap;
    private ArrayList<String> taggedSentenceTokens;
    private String input;

    public TextSimplifier(String input) {
        this.input = input;
        this.wordFrequencyMap = new HashMap<>();
        this.taggedSentenceTokens = new ArrayList<>();
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

    private void tagSentences() {

    }

    public ArrayList<String> getTaggedSentenceTokens() {
        this.tagSentences();
        return this.taggedSentenceTokens;
    }
}
