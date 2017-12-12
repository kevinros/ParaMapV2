package Box_Tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeadTest {
    @Test
    public void getHead() throws Exception {
        Head testHead = new Head();
        testHead.setHead("Test");
        assertEquals(testHead.getHead(), "Test");
    }

    @Test
    public void setHead() throws Exception {
        Head testHead = new Head();
        testHead.setHead("new head");
        String newHead = "12345";
        testHead.setHead("12345");
        assertEquals(newHead, testHead.getHead());
    }

}