package main.java.backend;

import java.util.ArrayList;
import java.util.HashMap;

public interface FreqBuilder {

    public HashMap<String, Integer> buildFrequencyMap(ArrayList<String> tokens);
    public void printFrequencyMap();
}


