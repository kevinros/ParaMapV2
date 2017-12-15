package main.java.backend;


import java.util.ArrayList;

public class BoxMerger {

    public static void mergeBox(ArrayList<Box> Boxes) throws Exception {
        WhiteSpaceTokenizer wsTokenizer = new WhiteSpaceTokenizer();
        int a;
        int b;
        int i;
        int j;
        for (a = 0; a < Boxes.size(); a++) {
            Box firstBox = Boxes.get(a);
            for (b = 0; b < (Boxes.size() - a); b++) {
                Box secondBox = Boxes.get(b);
                for (i = 0; i < secondBox.getBody().size(); i++) {
                    ArrayList<String> secondBody = secondBox.getBody();
                    wsTokenizer.tokenize(secondBody.get(i));
                    wsTokenizer.removePunctuations();
                    ArrayList<String> body = wsTokenizer.getTokens();
                    for (j = 0; j < body.size(); j++) {

                        if (firstBox.getHead().equals(body.get(j))) {

                            secondBox.getBody().remove(i);

                        }

                    }

                }

                for (i = 0; i < firstBox.getBody().size(); i++) {
                    ArrayList<String> firstBody = firstBox.getBody();
                    wsTokenizer.tokenize(firstBody.get(i));
                    wsTokenizer.removePunctuations();
                    ArrayList<String> body = wsTokenizer.getTokens();
                    for (j = 0; j < body.size(); j++) {

                        if (secondBox.getHead().equals(body.get(j))) {
                            firstBox.getBody().remove(i);

                        }

                    }

                }



            }
        }
        return;
    }
}
