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
import General_Tools.*;


public class main {

    public static void main(String[] args) throws Exception {

        String filePath = "src/StringInput.txt";
        String newSentence = new String(Files.readAllBytes(Paths.get(filePath)));

        TextSimplifier textSimplifier = new TextSimplifier(newSentence);
        ArrayList<String> simplifiedSentences = textSimplifier.getSimplifiedSentences();

        SubPredSeparator subPredSeparator = new SubPredSeparator();
        subPredSeparator.doALot(simplifiedSentences);
        HashMap<String, ArrayList<ArrayList<String>>> testMap = subPredSeparator.getMap();

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

