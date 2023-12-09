package org.lld.bestmatching;

import java.util.Collections;
import java.util.List;

public class BestMatchingData {
    private final List<String> words;
    private final int minDistance;

    public BestMatchingData(List<String> words, int minDistance) {
        this.words = words;
        this.minDistance = minDistance;
    }

    public List<String> getWords() {
        return Collections.unmodifiableList(words);
    }

    public int getMinDistance() {
        return minDistance;
    }
}
