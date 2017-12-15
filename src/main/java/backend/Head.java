/**
 * main.java.backend contains head.
 */
package main.java.backend;

/**
 * Represents the subject of a sentence, held as a string.
 */
public class Head {

    /**
     * The head of the box.
     */
    private String head;

    /**
     * Creates head that is an empty string.
     */
    protected Head() {
        this.head = "";
    }

    /**
     * Creates head that is capitalized.
     * @param  head to be made.
     */
    protected Head(String head) { this.head = capitalizeHead(head); }

    /**
     * @return the current head.
     */
    protected String getHead() {
        return this.head;
    }


    /**
     * Sets head to value of string.
     * @param head the new head string.
     */
    protected void setHead(String head) {
        this.head = capitalizeHead(head);
    }

    /**
     * Capitalizes first letter of head
     * @param head that needs to be capitalized.
     * @return head with capital string.
     */
    protected String capitalizeHead(String head) {
        String result = head.substring(0, 1).toUpperCase() + head.substring(1);
        return result;
    }
}
