
package main.java.backend;


import java.util.ArrayList;
import java.util.HashMap;

public class MapBuilder {
    /**
     * textSimplifier is an instance of the textSimplifier class that is used to replace pronouns
     * in a string with an appropiate noun
     */
    /**
     * subPredSeparator is an instane of the SubPredSeparator that separates the subject and
     * predicicate of a sentence
     */
    /**
     * boxMerger is an Instance of BoxMerger that is used to make connections
     * between boxes if the head of one box is in the body of another
     */
    /**
     * simplifiedSentences is a list of sentences that has been simplified by the
     * Text Simplifier
     */
    /**
     * subPreds is a Hash Map that contains the subjects and predicates of sentences run through
     * the SubPredSeparator
     */
    /**
     * userInput is a String inputted by the user
     */
    /**
     * map is a Paramap Which is a list of boxes.
     */
    TextSimplifier textSimplifier;
    SubPredSeparator subPredSeparator;
    BoxMerger boxMerger;
    ArrayList<String> simplifiedSentences;
    HashMap<String, ArrayList<String>> subPreds;
    String userInput;
    ParaMap map;

    /**
     * MapBuilder takes in a String input by a user and initializes a map variable,
     * an instance of SubPredSeparator, an instance of BoxMerger, and run the user input through the text simplifier
     * @param userInput A String input by a user
     * @throws Exception
     */
    public MapBuilder(String userInput) throws Exception {
        this.userInput = userInput;
        this.map = new ParaMap();
        this.textSimplifier = new TextSimplifier(userInput);
        this.subPredSeparator = new SubPredSeparator();
        this.boxMerger = new BoxMerger();
    }

    /**
     * Builds a Paramap from the Simplified sentences output from Mapbuilder
     * @return  returns a Paramap made from the user input
     * @throws Exception
     */
    public ParaMap buildMap() throws Exception {
        this.simplifiedSentences = textSimplifier.getSimplifiedSentences();
        this.subPredSeparator.createMap(simplifiedSentences);
        this.subPreds = subPredSeparator.getSubPreds();

        buildBoxForAllSubjects(this.subPreds);
        this.boxMerger.mergeBox(this.map.getMap());

        return this.map;
    }

    /**
     * Builds Boxes for all sentences input by user
     * @param subPreds takes in the hashmap of subjects and predicates output from
     *                  SubPredSeparator
     */
    private void buildBoxForAllSubjects(HashMap<String, ArrayList<String>> subPreds) {
        BoxBuilder boxBuilder = new BoxBuilder();
        for (String head : subPreds.keySet()) {
            Box box = boxBuilder.buildBox(head, subPreds.get(head));
            this.map.addBox(box);
        }



    }
}