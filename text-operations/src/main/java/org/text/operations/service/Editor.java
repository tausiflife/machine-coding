package org.text.operations.service;

public interface Editor {
    void insertText(String text);
    void deleteText(int startIndex, int endIndex);
    void formatText(Formatter formatter);
    void removeFormatting();
}
