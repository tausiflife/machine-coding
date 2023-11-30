package org.text.operations.commands;

import org.text.operations.service.TextEditor;

public class DeleteTextCommand implements Command {
    private TextEditor editor;
    private String lastDeletedText;
    private int deleteStartIndex;
    private int deleteEndIndex;

    public DeleteTextCommand(TextEditor editor, int deleteStartIndex, int deleteEndIndex) {
        this.editor = editor;
        this.deleteStartIndex = deleteStartIndex;
        this.deleteEndIndex = deleteEndIndex;
    }

    @Override
    public void execute() {
        String textToBeDeleted = editor.currentText();
        this.editor.deleteText(deleteStartIndex, deleteEndIndex);
        lastDeletedText = textToBeDeleted.substring(deleteStartIndex, deleteEndIndex);
    }

    @Override
    public void undo() {
        this.editor.insertText(lastDeletedText);
    }
}
