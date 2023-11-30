package org.lld.textpad.commands;

import org.lld.textpad.data.TextPad;

public class InsertCommand implements UndoableCommand{
    private TextPad textPad;
    private int lineNumber;
    private String text;
    public InsertCommand(TextPad textPad, int lastLine, String pText) {
        this.textPad = textPad;
        this.lineNumber = lastLine;
        this.text = pText;
    }

    @Override
    public void execute() {
        this.textPad.insert(lineNumber, text);
    }

    @Override
    public void undo() {
        this.textPad.delete(lineNumber);
    }
}
