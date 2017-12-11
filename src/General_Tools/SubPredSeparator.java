package General_Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SubPredSeparator{
    //Class Variables
    private HashMap<String, ArrayList<ArrayList<String>>> HeadToBod;
    private String head;
    private ArrayList<String> body;
    private Tagger tagger;
    private ArrayList<ArrayList<String>> listofListofSentences;
    private ArrayList<ArrayList<String>> listOfListOfTags;

    public SubPredSeparator() throws Exception{
        this.head = "";
        this.body = new ArrayList<>();
        this.HeadToBod = new HashMap<>();
        this.tagger = new Tagger();
        this.listofListofSentences = new ArrayList<>();
        this.listOfListOfTags = new ArrayList<>();
    }

    public void doALot(ArrayList<String> sentences) {
        //Adds sentences and tags to arrayLists
        createLists(sentences);

        //Sets Head and Body and creates map in process
        setHeadandBod();
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

    //Sets the head and body and creates map in process
    private void setHeadandBod() {
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
            //creates hashmap and checks if head already exists. If so, it merges heads.
            createMap();
            //Clean slate for next iteration
            clear();
        }
    }


    //creates hashmap and checks if head already exists. If so, it merges heads.
    private void createMap() {
        String head = this.head.toLowerCase();
        ArrayList<String> newbie = new ArrayList<>(this.body);
        if (this.HeadToBod.containsKey(head))
            this.HeadToBod.get(head).add(newbie);
        else {
            this.HeadToBod.put(head, new ArrayList<>());
            this.HeadToBod.get(head).add(newbie);
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

    public HashMap<String, ArrayList<ArrayList<String>>> getMap() {
        return this.HeadToBod;
    }


    public static void main(String[] args) throws Exception {
        SubPredSeparator test = new SubPredSeparator();
        ArrayList<String> testSentences = new ArrayList<>(Arrays.asList("A class is horrible", " A Planet is huge", "Saturn is next to Uranus", "Planets may explode", "Classes suck"));

        test.doALot(testSentences);
        HashMap<String, ArrayList<ArrayList<String>>> testMap = new HashMap<>(test.getMap());
        System.out.println(testMap.keySet());

        String body = "";
        for(String head : testMap.keySet()) {
            System.out.println("Head of sentence is: " + head);
           for (ArrayList<String> bods : testMap.get(head)) {
               for(String s : bods) {
                   body += s + " ";
               }
               System.out.println("Body of sentence is: " +body);
               body = "";
           }
            System.out.println();
            }
        }

    }


