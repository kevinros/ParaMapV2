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



}