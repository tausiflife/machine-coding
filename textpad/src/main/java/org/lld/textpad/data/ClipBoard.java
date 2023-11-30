package org.lld.textpad.data;

import java.util.ArrayList;
import java.util.List;

public class ClipBoard {
    private List<String> contents;

    public ClipBoard() {
        this.contents = new ArrayList<>();
    }

    public void copy(List<String> lines) {
        contents.clear();
        contents.addAll(lines);
    }

    public List<String> paste() {
        return new ArrayList<>(contents);
    }

}
