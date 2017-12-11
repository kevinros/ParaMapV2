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

    public void setHead(ArrayList<String> sentences) {
        //Adds sentences and tags to arrayLists
        createLists(sentences);

        //Set Head and Body and creates map in process
        setHeadandBod();
    }

    //Adds sentences and tags to arraylists
    private void createLists(ArrayList<String> sentences){
        for(String s : sentences){
            this.tagger.tagSentence(s);
            this.listofListofSentences.add(this.tagger.getSentence());
            this.listOfListOfTags.add(this.tagger.getTags());
            this.tagger.clear();
        }
    }

    //Sets the head and body
    private void setHeadandBod() {
        //List of all possible subjects
        ArrayList<String> subjectList = new ArrayList<>(Arrays.asList("NN", "NNS", "NNP", "NNPS"));
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
                    this.head = listOfSentences.get(j);
                    //iterate through rest of sentence and add to body
                    for (int k = j + 1; k < listOfSentences.size() - j - 1; k++) {
                        this.body.add(listOfSentences.get(k));
                    }
                    break;
                }
            }
            createMap();
            clear();


        }
    }



    private void createMap() {
        if (this.HeadToBod.containsKey(this.head))
            this.HeadToBod.get(this.head).add(this.body);
        else {
            this.HeadToBod.put(this.head, new ArrayList<>());
            this.HeadToBod.get(this.head).add(this.body);
        }
    }

    private void clear(){
        this.head = "";
        this.body.clear();
    }

    public String getHead() {
        return this.head;
    }

    public ArrayList<String> getBody() {
        return this.body;
    }

    public HashMap<String, ArrayList<ArrayList<String>>> getMap() {
        return this.HeadToBod;
    }


    public static void main(String[] args) throws Exception {
        SubPredSeparator test = new SubPredSeparator();

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
           /* for (String bod : test.HeadToBod.get(head)) {
                body += bod + " ";
           */ }
            System.out.println("Body of sentence is: " +body);
        }

    }


