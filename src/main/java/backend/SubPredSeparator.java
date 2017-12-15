package main.java.backend;

import java.util.*;

public class SubPredSeparator{
    //Class Variables
    private HashMap<String, ArrayList<ArrayList<String>>> tokenizedSubPreds;
    private HashMap<String, ArrayList<String>> subPreds;
    private String head;
    private ArrayList<String> body;
    private Tagger tagger;
    private ArrayList<ArrayList<String>> listofListofSentences;
    private ArrayList<ArrayList<String>> listOfListOfTags;

    public SubPredSeparator() throws Exception{
        this.head = "";
        this.body = new ArrayList<>();
        this.tokenizedSubPreds = new HashMap<>();
        this.subPreds = new HashMap<>();
        this.tagger = new Tagger();
        this.listofListofSentences = new ArrayList<>();
        this.listOfListOfTags = new ArrayList<>();
    }

    //Creates map of head to body from a given list of sentences
    public void createMap(ArrayList<String> sentences) {
        //Adds sentences and tags to arrayLists
        createLists(sentences);

        //List of all possible subjects
        ArrayList<String> subjectList = new ArrayList<>(Arrays.asList("NN", "NNS", "NNP", "NNPS"));
        ArrayList<String> pluralList = new ArrayList<>(Arrays.asList("NNS", "NNPS"));
        //iterate through ArrayList of ArrayList of listOfListOfTags
        for (int i = 0; i < this.listOfListOfTags.size(); i++) {
            //ith ArrayList of Tags to iterate through
            ArrayList<String> listOfTags = this.listOfListOfTags.get(i);
            //Corresponding ith ArrayList of Sentences to extract head and body from
            ArrayList<String> listOfSentences = this.listofListofSentences.get(i);

            //Iterate through ith ArrayList of Tags
            for (int j = 0; j < listOfTags.size(); j++) {
                //jth tag to inspect
                String comparedTag = listOfTags.get(j);

                //Check if the tag is a subject tag
                if (subjectList.contains(comparedTag)) {
                    if(pluralList.contains(comparedTag)){
                        //if plurar, convert to singular first
                        this.head = convertToSingular(listOfSentences.get(j));
                    }
                    else {
                        this.head = listOfSentences.get(j);
                    }
                    //iterate through rest of sentence and add to body
                    for (int k = j+1; k < listOfSentences.size(); k++) {
                        this.body.add(listOfSentences.get(k));
                    }
                    break;
                }
            }
            //Adds the current head and body to the map. Checks if head already exists. If it does, it merges
            // it with new body
            addToMap();
            //Clean slate for next iteration
            clear();
        }

        createSubPreds();
    }

    //Adds sentences and tags to arrayLists
    private void createLists(ArrayList<String> sentences){
        for(String s : sentences){
            this.tagger.tagSentence(s);
            ArrayList<String> sent = new ArrayList<>(this.tagger.getSentence());
            ArrayList<String> tagz = new ArrayList<>(this.tagger.getTags());
            this.listofListofSentences.add(sent);
            this.listOfListOfTags.add(tagz);
            this.tagger.clear();
        }
    }


    //Adds the current head and body to the map. Checks if head already exists. If it does, it merges it with new body
    private void addToMap() {
        String head = this.head.toLowerCase();
        ArrayList<String> newbie = new ArrayList<>(this.body);
        if (this.tokenizedSubPreds.containsKey(head))
            if(this.tokenizedSubPreds.get(head).contains(newbie)){
                return;
            }
            else {
                this.tokenizedSubPreds.get(head).add(newbie);
            }
        else {
            this.tokenizedSubPreds.put(head, new ArrayList<>());
            this.tokenizedSubPreds.get(head).add(newbie);
        }
    }
    //Allows class reusability
    private void clear(){
        this.head = "";
        this.body.clear();
    }

    //Converts common plurals to singular
    private String convertToSingular(String word) {
        String singular = "";
        if (word.charAt(word.length() - 1) == 's') {
            if (word.charAt(word.length() - 2) == 'e') {
                if (word.charAt(word.length() - 3) == 'i') {
                    singular = word.substring(0, word.length() - 3) + "y";
                } else {
                    singular = word.substring(0, word.length() - 2);
                }
            }
            else {
                singular = word.substring(0, word.length()-1);
            }
        }
        else if ((word.charAt(word.length() - 1) == 'i')) {
            singular = word.substring(0, word.length()-1) + "us";
        }
        return singular;

    }

    public HashMap<String, ArrayList<ArrayList<String>>> getTokenizedSubPreds() {
        return this.tokenizedSubPreds;
    }

    public HashMap<String, ArrayList<String>> getSubPreds() {
        return this.subPreds;
    }

    private void createSubPreds() {
        String body = "";
        for(String head : this.tokenizedSubPreds.keySet()) {
            ArrayList<String> concatenatedBody = new ArrayList<>();
            for (ArrayList<String> bods : this.tokenizedSubPreds.get(head)) {
                for(String s : bods) {
                    body += s + " ";
                }
                concatenatedBody.add(body.trim());
                body = "";
            }
            this.subPreds.put(head, concatenatedBody);
        }

        splitPredsByConjunctions();
    }

    private void splitPredsByConjunctions() {
        String[] conjunctions = {"\\band\\b", "\\bbut\\b", "\\byet\\b"};
        for (String head : this.subPreds.keySet()) {
            ArrayList<String> newBodyElements = new ArrayList<>();
            for (String bodyElement : this.subPreds.get(head)) {
                String[] tokens = null;
                for (int i = 0; i < conjunctions.length; i++) {
                    tokens = bodyElement.split(conjunctions[i]);
                    if (tokens.length > 1) {
                        for (int j = 0; j < tokens.length; j++) {
                            String newElement = tokens[j].trim();
                            newBodyElements.add(newElement);
                        }
                    }
                }
            }
            if (newBodyElements.size() == 0) {
                newBodyElements.add(this.subPreds.get(head).get(0));
            }
            // Remove all duplicates in the body list
            this.subPreds.put(head, newBodyElements);
        }


    }

    public void printMap(){
        String body = "";
        for(String head : this.tokenizedSubPreds.keySet()) {
            System.out.println("Head of sentence is: " + head);
            for (ArrayList<String> bods : this.tokenizedSubPreds.get(head)) {
                for(String s : bods) {
                    body += s + " ";
                }
                System.out.println("Body of sentence is: " +body);
                body = "";
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws Exception {
        SubPredSeparator test = new SubPredSeparator();
        ArrayList<String> testSentences = new ArrayList<>(Arrays.asList("A class is horrible", " A Planet is huge",
                "Saturn is next to Uranus", "Planets may explode", "Classes suck"));

        test.createMap(testSentences);
        test.printMap();
    }

}


