package org.lld.bestmatching;

import java.util.ArrayList;
import java.util.List;

public class BestMatchingSerialCalculation {
    public static BestMatchingData getBestMatchingWords(String word, List<String> dictionary) {
        List<String> results = new ArrayList<String>();
        int minDistance = Integer.MAX_VALUE;
        int distance;
        for (String str : dictionary) {
            distance = LevenshteinDistance.calculate(word, str);
            if (distance < minDistance) {
                results.clear();
                minDistance = distance;
                results.add(str);
            } else if (distance == minDistance) {
                results.add(str);
            }
        }
        BestMatchingData result = new BestMatchingData(results, minDistance);
        return result;
    }
}
