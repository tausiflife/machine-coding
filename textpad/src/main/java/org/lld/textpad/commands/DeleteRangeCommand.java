package org.lld.textpad.commands;

import org.lld.textpad.data.TextPad;

import java.util.List;

public class DeleteRangeCommand implements UndoableCommand{
    private TextPad textPad;
    private int lineDeletedStart;
    private int lineDeletedEnd;
    private List<String> lastDeletedTexts;

    public DeleteRangeCommand(TextPad textPad, int lineDeletedStart, int lineDeletedEnd) {
        this.textPad = textPad;
        this.lineDeletedStart = lineDeletedStart;
        this.lineDeletedEnd = lineDeletedEnd;
    }

    @Override
    public void execute() {
        lastDeletedTexts = this.textPad.display(lineDeletedStart, lineDeletedEnd);
        this.textPad.delete(lineDeletedStart, lineDeletedStart);
    }

    @Override
    public void undo() {
        for(int i=lineDeletedStart; i<=lineDeletedEnd; i++)
            this.textPad.insert(i, lastDeletedTexts.get(i));
    }
}
