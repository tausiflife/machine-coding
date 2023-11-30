package org.lld.textpad.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TextPad {
    private List<String> lines;
    private ClipBoard clipBoard;
    public TextPad() {
        lines = new ArrayList<>();
    }

    public List<String> display() {
        return Collections.unmodifiableList(lines);
    }

    public List<String> display(int n, int m) {
        return Collections.unmodifiableList(lines.stream().skip(n-1).limit(m).collect(Collectors.toList()));
    }

    public void insert(int n, String text) {
        this.lines.add(n, text );
    }

    public void delete(int n) {
        this.lines.remove(n);
    }

    public void delete(int n, int m) {
        Iterator<String> iterator = this.lines.listIterator(n);
        int c= 0;
        while(iterator.hasNext() && c < m-n+1) {
            iterator.remove();
            c++;
        }
    }

    public void copy(int n, int m) {

    }

    public void paste(int n){

    }

    public ClipBoard getClipBoard() {
        return clipBoard;
    }

    public void setClipBoard(ClipBoard clipBoard) {
        this.clipBoard = clipBoard;
    }
}
