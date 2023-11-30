package org.lld.textpad.commands;

import org.lld.textpad.data.TextPad;

public class FullTextDisplay implements UndoableCommand{
    private TextPad textPad;

    public FullTextDisplay(TextPad textPad) {
        this.textPad = textPad;
    }

    @Override
    public void execute() {
        this.textPad.display();
    }

    @Override
    public void undo() {
        System.out.println("Nothing to undo!!");
    }
}
