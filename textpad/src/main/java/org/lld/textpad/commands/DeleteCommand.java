package org.lld.textpad.commands;

import org.lld.textpad.data.TextPad;

public class DeleteCommand implements UndoableCommand{
    private TextPad textPad;
    private int lineDeleted;
    private String lastDeletedText;
    public DeleteCommand(TextPad textPad, int lineDeleted) {
        this.textPad = textPad;
        this.lineDeleted = lineDeleted;
    }

    @Override
    public void execute() {
        lastDeletedText = this.textPad.display(lineDeleted, lineDeleted).get(0);
        this.textPad.delete(lineDeleted);
    }

    @Override
    public void undo() {
        this.textPad.insert(lineDeleted, lastDeletedText);
    }
}
