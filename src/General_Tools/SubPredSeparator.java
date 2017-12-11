package General_Tools;

import java.util.ArrayList;
import java.util.HashMap;

public class SubPredSeparator {
    //Class Variables
    private HashMap<String, ArrayList<String>> HeadToBod;
    private String head;
    private ArrayList<String> body;
    private WhiteSpaceTokenizer tokenizer;

    public SubPredSeparator() throws Exception{
        this.head = "";
        this.body = new ArrayList<String>();
        this.HeadToBod = new HashMap<String, ArrayList<String>>();
        tokenizer = new WhiteSpaceTokenizer();
    }

    public void setHead(String sentence) {
        tokenizer.tokenize(sentence);
        ArrayList<String> words = new ArrayList<>(tokenizer.getTokens());
        this.head = words.get(0);
    }

    public void setBody(String sentence) {
        tokenizer.tokenize(sentence);
        ArrayList<String> words = new ArrayList<>(tokenizer.getTokens());
        words.remove(0);
        this.body = words;
    }

    public void createMap(String sentence) {
        setHead(sentence);
        setBody(sentence);
        this.HeadToBod.put(this.head, this.body);
    }

    public String getHead() {
        return this.head;
    }

    public ArrayList<String> getBody() {
        return this.body;
    }

    public HashMap<String, ArrayList<String>> getMap() {
        return this.HeadToBod;
    }


    public static void main(String[] args) throws Exception {
        SubPredSeparator test = new SubPredSeparator();

        test.createMap("Saturn is a planet. Saturn is big");

        String testHead = test.getHead();
        System.out.println(testHead);

        ArrayList<String> testBody =  new ArrayList<>(test.getBody());
        for (String bod : testBody) {
            System.out.print(bod + " ");
        }

        System.out.println();

        String body = "";
        for(String head : test.HeadToBod.keySet()) {
            System.out.println("Head of sentence is: " + head);
            for (String bod : test.HeadToBod.get(head)) {
                body += bod + " ";
            }
            System.out.println("Body of sentence is: " +body);
        }

    }
}

