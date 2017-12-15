package main.java.backend;

import main.java.frontend.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MapBuilder {

    TextSimplifier textSimplifier;
    SubPredSeparator subPredSeparator;
    ArrayList<String> simplifiedSentences;
    HashMap<String, ArrayList<String>> subPreds;
    String userInput;
    ParaMap map;

    public MapBuilder(String userInput) throws Exception {
        this.userInput = userInput;
        this.map = new ParaMap();
        this.textSimplifier = new TextSimplifier(userInput);
        this.subPredSeparator = new SubPredSeparator();
    }

    public ParaMap buildMap() throws Exception {
        this.simplifiedSentences = textSimplifier.getSimplifiedSentences();
        this.subPredSeparator.createMap(simplifiedSentences);
        this.subPreds = subPredSeparator.getSubPreds();

        buildBoxForAllSubjects(this.subPreds);



        return this.map;
    }

    private void buildBoxForAllSubjects(HashMap<String, ArrayList<String>> subPreds) {
        BoxBuilder boxBuilder = new BoxBuilder();
        // all the jason things are for testing purposes
        //Box jasonBox = null;
        for (String head : subPreds.keySet()) {
            Box box = boxBuilder.buildBox(head, subPreds.get(head));
            //if (box.getHead().equals("Jason")) {
            //  jasonBox = box;
            //}
            this.map.addBox(box);
        }

        //jasonBox.addBoxConnection(this.map.getMap().get(0)); // Ralph
        //jasonBox.addBoxConnection(this.map.getMap().get(2)); // Mary


    }
}
