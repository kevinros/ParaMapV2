package main.java.backend;

import java.util.ArrayList;

/*
This class' primary purpose is to link related boxes
*/
public class BoxMerger {
    ArrayList<Box> boxes;

/*
Constructor instantiates an arrayList of boxes when a BoxMerger object is created
 */
    public BoxMerger() throws Exception{
        this.boxes = new ArrayList<>();
    }
//This method takes in an ArrayList of boxes and looks for any part of a BoxBody that is also the Head of another box
        public void mergeBox(ArrayList<Box> boxes){
            int numOfBoxes = boxes.size();
            for(int i = 0; i <numOfBoxes; i++){
                Box comparedBox = boxes.get(i);
                String comparedHead = comparedBox.getHead();
                        for(int k = 0; k<numOfBoxes; k++){
                            Box otherBox = boxes.get(k);
                            ArrayList<String> otherBoxBodies = otherBox.getBody();
                            for(String body : otherBoxBodies){
                                if(body.toLowerCase().contains(comparedHead.toLowerCase())){
                               if(k!=i){
                                   otherBox.addBoxConnection(comparedBox);
                               }

                        }
                    }
                }
            }
        }


    public static void main(String[] args) throws Exception {
        BoxMerger testMerger = new BoxMerger();
        MapBuilder testMapper = new MapBuilder("Saturn is a planet. Planet is big. ");
        ArrayList<Box> testboxes = new ArrayList<>(testMapper.buildMap().getMap());
        testMerger.mergeBox(testboxes);
        for (Box box : testboxes) {
          box.printBoxContents();
           for (Box box2 : box.getBoxConnections()) {
               box2.printBoxContents();;
          }
        }
    }
}
