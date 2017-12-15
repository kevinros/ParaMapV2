package test.java ;
import main.java.backend.Box;
import main.java.backend.BoxBuilder;
import main.java.backend.MapBuilder;
import main.java.backend.ParaMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MapBuilderTest {
    @Test
    public void buildMap() throws Exception {
        String Input = "Saturn is a planet. Jupiter is a planet. Planets are Large. Planets are Round.";
        MapBuilder test = new MapBuilder("Saturn is a planet. Jupiter is a planet. Planets are large. Planets are round.");
        BoxBuilder tester = new BoxBuilder();
        ArrayList<String> first = new ArrayList<>(Arrays.asList("is", "a","planet"));
        ArrayList<String> second = new ArrayList<>(Arrays.asList("are", "large"));
        ArrayList<String> third = new ArrayList<>(Arrays.asList("are", "round"));
        Box a = tester.buildBox("Saturn", first);
        Box b = tester.buildBox("Jupiter", first);
        Box c = tester.buildBox("Planets", second);
        Box d = tester.buildBox("Planets", third);
        ParaMap map = test.buildMap();
        ArrayList<Box> boxTest= new ArrayList<>(Arrays.asList(a, b, c, d));
        assertEquals(boxTest, map.getMap());
    }

}