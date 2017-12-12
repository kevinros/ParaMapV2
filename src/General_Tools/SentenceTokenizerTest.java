package General_Tools;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SentenceTokenizerTest {

    @Test
    public void SentenceTokenizerTest() throws Exception{
        String test = "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius about nine times that of Earth.";
        ArrayList<String> correct1 = new ArrayList<>(Arrays.asList("Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter.", "It is a gas giant with an average radius about nine times that of Earth."));
        SentenceTokenizer tester = new SentenceTokenizer();
        tester.tokenize(test);


        // assert statements
        assertEquals(correct1, tester.getTokens());

    }
}