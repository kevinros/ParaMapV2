package main.java.backend;
import com.sun.javafx.binding.StringFormatter;

import java.util.ArrayList;

/**
 * Box is made up of a Head and a Body,
 * where the Head is a single word and
 * the Body is the description of a Head.
 */
public class Box {
    /**
     * boxHead contains the box head.
     */
    private Head boxHead;
    /**
     * boxBody contains the box body.
     */
    private Body boxBody;
    /**
     * boxConnections contains the connections
     * between the boxes.
     */
    private ArrayList<Box> boxConnections;

    /**
     * Initializes a Box with an empty head,
     * body, and connections.
     */
    public Box() {
        this.boxHead = new Head();
        this.boxBody = new Body();
        this.boxConnections = new ArrayList<>();
    }

    /**
     * Initializes a Box with provided
     * head and body.
     * @param boxHead the head of the box.
     * @param boxBody the body of the box.
     */
    public Box(Head boxHead, Body boxBody) {
        this.boxHead = boxHead;
        this.boxBody = boxBody;
        this.boxConnections = new ArrayList<>();
    }

    /**
     * Returns the head of a Box.
     * @return head.
     */
    public String getHead() {
        return this.boxHead.getHead();
    }

    /**
     * Returns the body of a Box.
     * @return body.
     */
    public ArrayList<String> getBody() {
        return this.boxBody.getBody();
    }

    /**
     * Returns connections between Boxes.
     * @return connections.
     */
    public ArrayList<Box> getBoxConnections() {
        return this.boxConnections;
    }

    /**
     * Sets head to given string.
     * @param head new head.
     */
    public void setHead(String head) {
        this.boxHead.setHead(head);
    }

    /**
     * Adds description to body of Box.
     * @param element what is added.
     */
    public void addElementToBody(String element) {
        this.boxBody.addDescriptionToBody(element);
    }

    /**
     * Adds many elements to body.
     * @param elements what is added.
     */
    public void addManyElementsToBody(ArrayList<String> elements) {
        this.boxBody.addManyDescriptionsToBody(elements);
    }
    /**
     * Removes an element from body
     */
    public void removeElementFromBody(String element) {
        this.boxBody.removeDescriptionFromBody(element);
    }

    /**
     * Removes all elements from body.
     */
    public void removeAllElementsFromBody() {
        this.boxBody.removeAllDescriptionsFromBody();
    }

    /**
     * Adds connection between boxes.
     * @param box connection.
     */
    public void addBoxConnection(Box box) {
        this.boxConnections.add(box);
    }

    /**
     * Clear entire box.
     * Sets head to "" and removes all descriptions.
     */
    public void clearBox() {
        this.boxHead.setHead("");
        this.boxBody.removeAllDescriptionsFromBody();
    }

    /**
     * Prints the contents of a box.
     */
    public void printBoxContents() {
        System.out.printf("Head: %s\n", this.getHead());
        this.boxBody.printBody();
    }

    public boolean equals(Box box) {
        if (!this.boxHead.equals(box.getHead())) {
            return false;
        }

        if (!this.boxBody.getBody().equals(box.getBody())) {
            return false;
        }

        if (!this.boxConnections.equals(box.getBoxConnections())) {
            return false;
        }

        return true;
    }

    public String toString() {
        String string = "";
        string = "Head: " + this.boxHead + "\n" + "Body : " + this.boxBody.getBody().toString();
        return string;
    }

}
