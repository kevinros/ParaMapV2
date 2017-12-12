package General_Tools;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class WhiteSpaceTokenizerTest {

    @Test
    public void TokenizeTest() throws Exception {
        String test1 = "One two Three";
        String test2 = "One. Two. Three.";
        String test3 = "Pluto is a planet. Jupiter is a planet.";
        ArrayList<String> correct1 = new ArrayList<>(Arrays.asList("One", "Two", "Three"));
        ArrayList<String> correct2 = new ArrayList<>(Arrays.asList("Pluto", "is", "a", "planet", "Jupiter", "is", "a", "planet"));
        WhiteSpaceTokenizer  tester1 = new WhiteSpaceTokenizer(); // White Space Tokenizer is tested
        WhiteSpaceTokenizer  tester2 = new WhiteSpaceTokenizer();
        WhiteSpaceTokenizer  tester3 = new WhiteSpaceTokenizer();
        tester1.tokenize(test1);
        tester2.tokenize(test2);
        tester2.removePunctuations();
        tester3.tokenize(test3);
        tester3.removePunctuations();

        // assert statements
        assertEquals(correct1, tester1.getTokens());
        assertEquals(correct1, tester2.getTokens());
        assertEquals(correct2, tester3.getTokens());
    }



}