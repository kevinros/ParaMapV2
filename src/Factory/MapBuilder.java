package Factory;

import Box_Tools.Box;
import Box_Tools.BoxBuilder;
import Box_Tools.ParaMap;
import General_Tools.SubPredSeparator;
import General_Tools.TextSimplifier;

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
        for (String head : subPreds.keySet()) {
            Box box = boxBuilder.buildBox(head, subPreds.get(head));
            this.map.addBox(box);
        }
    }
}
