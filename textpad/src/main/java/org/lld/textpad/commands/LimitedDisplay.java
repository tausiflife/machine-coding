package org.lld.textpad.commands;

import org.lld.textpad.data.TextPad;

public class LimitedDisplay implements UndoableCommand{
    private TextPad textPad;
    private int startLine;
    private int endLine;
    public LimitedDisplay(TextPad textPad, int pStartLine, int pEndLine) {
        this.textPad = textPad;
        this.startLine = pStartLine;
        this.endLine = pEndLine;
    }

    @Override
    public void execute() {
        this.textPad.display(startLine, endLine);
    }

    @Override
    public void undo() {
        System.out.println("Nothing to undo!!");
    }
}
