package main.java.backend;

import java.util.ArrayList;

public class ParaMap {
    /**
     * Has a variable "boxes" Which houses an ArrayList
     * containing boxes. Can add boxes to boxes and can print
     * the contents of boxes.
     */
    private ArrayList<Box> boxes;

    /**
     * Initializes the variable box as an empty ArrayList
     */
    public ParaMap() {
        this.boxes = new ArrayList<>();
    }

    /**
     * Inserts a box b into the ArrayList boxes
     * @param b
     */
    public void addBox(Box b) {
        this.boxes.add(b);
    }

    /**
     * Returns the ArrayList<Box> boxes
     *
     */
    public ArrayList<Box> getMap() {
        return this.boxes;
    }

    /**
     * Prints out all boxes contained in the ArrayList boxed
     */
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
