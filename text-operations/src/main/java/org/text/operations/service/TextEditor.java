package org.text.operations.service;

public class TextEditor implements Editor{
    private StringBuffer text;

    public TextEditor() {
        text = new StringBuffer();
    }

    @Override
    public void insertText(String text) {
        this.text.append(text);
    }

    @Override
    public void deleteText(int startIndex, int endIndex) {
        this.text.delete(startIndex, endIndex);
    }

    @Override
    public void formatText(Formatter formatter) {
        //TODO implement formatting of the text
    }

    @Override
    public void removeFormatting() {
        //TODO remove formatting of text
    }

    public String currentText() {
        return this.text.toString();
    }

}
