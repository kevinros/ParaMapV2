package Box_Tools;

import java.util.ArrayList;

public class ParaMap {

    private ArrayList<Box> boxes;

    public ParaMap() {
        this.boxes = new ArrayList<>();
    }

    public void addBox(Box b) {
        this.boxes.add(b);
    }

    public ArrayList<Box> getMap() {
        return this.boxes;
    }

    public void printMap() {
        System.out.println("Printing out the contents of the map...");
        int i = 1;

        for (Box b : this.boxes) {
            System.out.printf("Box #%d:\n", i);
            System.out.printf("   Head: %s\n", b.getHead());

            int j = 1;

            for (String element : b.getBody()) {
                System.out.printf("     Element #%d: %s\n", j, element);
                j++;
            }

            System.out.println("-----------------------");
            i++;
        }
    }
}
