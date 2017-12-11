package Box_Tools;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BodyTest {
    @Test
    public void addDescriptionToBody() throws Exception {
        Body Test = new Body();
        Test.addDescriptionToBody("12345");
        ArrayList<String> Description = new ArrayList<>(Arrays.asList("12345"));
        assertEquals(Description,Test.getBody());
    }

    @Test
    public void addManyDescriptionsToBody() throws Exception {
        Body Test = new Body();
        ArrayList<String> Descriptions = new ArrayList<>(Arrays.asList("12345","678910","11121314"));
        Test.addManyDescriptionsToBody(Descriptions);
        assertEquals(Descriptions,Test.getBody());

    }

    @Test
    public void getBody() throws Exception {
    }

    @Test
    public void removeDescriptionFromBody() throws Exception {
        Body Test = new Body();
        Test.addDescriptionToBody("12345");
        Test.removeDescriptionFromBody("12345");
        ArrayList<String> Description = new ArrayList<>(Arrays.asList(""));
        assertEquals(Description,Test.getBody());

    }

    @Test
    public void removeAllDescriptionsFromBody() throws Exception {
        Body Test = new Body();
        ArrayList<String> Descriptions = new ArrayList<>(Arrays.asList("12345","678910","11121314"));
        ArrayList<String> Null = new ArrayList<>(Arrays.asList("12345"));
        Test.addManyDescriptionsToBody(Descriptions);
        Test.removeAllDescriptionsFromBody();
        assertEquals(Null,Test.getBody());
    }

    @Test
    public void printBody() throws Exception {
        Body Test = new Body();
        Test.addDescriptionToBody("12345");
        ArrayList<String> Description = new ArrayList<>(Arrays.asList("12345"));
        assertEquals(Description,Test.getBody());

    }

}