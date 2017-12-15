package main.java.backend;
import java.util.ArrayList;

/**
 * Body holds an ArrayList of strings,
 * where the strings represent predicates/descriptions.
 */
public class Body {

    /**
     *  The body of a box.
     */
    private ArrayList<String> body;

    /**
     * Creates a body initialized to empty.
     */
    protected Body() {
        this.body = new ArrayList<>();
    }

    /**
     * Creates a body from the given descriptions.
     * @param descriptions the descriptions to be added.
     */
    protected Body(ArrayList<String> descriptions) {
        this.body = descriptions;
    }

    /**
     * Add a description to a body
     * @param description the description to be added
     */
    protected void addDescriptionToBody(String description) {
        this.body.add(description);
    }

    /**
     * Add collection of descriptions to body.
     * @param descriptions added to body.
     */
    protected void addManyDescriptionsToBody(ArrayList<String> descriptions) {
        this.body.addAll(descriptions);
    }

    /**
     * Gets body variable.
     * @return body ArrayList<String>.
     */
    protected ArrayList<String> getBody() {
        return this.body;
    }

    /**
     * Removes description from body.
     * @param body where description is removed.
     */
    protected void removeDescriptionFromBody(String body) {
        this.body.remove(body);
    }

    /**
     * Clears body (removes all descriptions).
     */
    protected void removeAllDescriptionsFromBody() {
        this.body.clear();
    }

    /**
     * Prints the descriptions of a body. 
     */
    protected void printBody() {
        System.out.println("Body: ");

        for (Object s : this.body) {
            System.out.println((String)s);
        }

        System.out.println("-------------------------------");

    }
}
