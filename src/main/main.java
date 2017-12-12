package main;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Box_Tools.Box;
import Box_Tools.BoxBuilder;
import Box_Tools.ParaMap;
import General_Tools.*;


public class main {

    public static void main(String[] args) throws Exception {

        String filePath = "src/StringInput.txt";
        String newSentence = new String(Files.readAllBytes(Paths.get(filePath)));

        TextSimplifier textSimplifier = new TextSimplifier(newSentence);
        ArrayList<String> simplifiedSentences = textSimplifier.getSimplifiedSentences();

        SubPredSeparator subPredSeparator = new SubPredSeparator();
        subPredSeparator.createMap(simplifiedSentences);
        HashMap<String, ArrayList<ArrayList<String>>> tokenizedSubPreds = subPredSeparator.getTokenizedSubPreds();
        HashMap<String, ArrayList<String>> subPreds = subPredSeparator.getSubPreds();

        ParaMap paraMaps = new ParaMap();
        BoxBuilder boxBuilder = new BoxBuilder();

        for (String head : subPreds.keySet()) {
            Box box = boxBuilder.buildBox(head, subPreds.get(head));
            paraMaps.addBox(box);
        }

        paraMaps.printMap();




    }

}

