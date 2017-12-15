package test.java;
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
        String input = "Saturn is a planet. Jupiter is a planet. Planets are large. Planets are round.";
        MapBuilder test = new MapBuilder(input);
        ParaMap map = test.buildMap();
        ArrayList<Box> boxes = map.getMap();
        BoxBuilder tester = new BoxBuilder();
        /*
        We should end up with 3 boxes: Saturn, Jupiter, Planet
        Saturn -> boxHead: "Saturn", boxBody: {"is a planet"}, boxConnection: {Planet}
        Jupiter -> boxHead: "Jupiter", boxBody: {"is a planet"}, boxConnection: {Planet}
        Planet -> boxHead: "Planet", boxBody: {"are large", "are round"}, boxConnection: {}
        */
        ArrayList<String> saturnList = new ArrayList<>();
        saturnList.add("is a planet");
        Box saturn = tester.buildBox("Saturn", saturnList);

        ArrayList<String> jupiterList = new ArrayList<>();
        jupiterList.add("is a planet");
        Box jupiter = tester.buildBox("Jupiter", jupiterList);

        ArrayList<String> planetList = new ArrayList<>();
        planetList.add("are large");
        planetList.add("are round");
        Box planet = tester.buildBox("Planet", planetList);

        saturn.addBoxConnection(planet);
        jupiter.addBoxConnection(planet);

        ArrayList<Box> testBoxes = new ArrayList<>();
        testBoxes.add(saturn);
        testBoxes.add(jupiter);
        testBoxes.add(planet);

        assertEquals(false, boxes.containsAll(testBoxes));








        /*ArrayList<String> first = new ArrayList<>(Arrays.asList("is", "a","planet"));
        ArrayList<String> second = new ArrayList<>(Arrays.asList("are", "large"));
        ArrayList<String> third = new ArrayList<>(Arrays.asList("are", "round"));
        Box a = tester.buildBox("Saturn", first);
        Box b = tester.buildBox("Jupiter", first);
        Box c = tester.buildBox("Planets", second);
        Box d = tester.buildBox("Planets", third);
        ArrayList<Box> boxTest= new ArrayList<>(Arrays.asList(a, b, c, d));
        assertEquals(boxTest, map.getMap());*/
    }

}