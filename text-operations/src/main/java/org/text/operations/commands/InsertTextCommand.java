package org.text.operations.commands;

import org.text.operations.service.TextEditor;

public class InsertTextCommand implements Command{
    private TextEditor editor;
    private String textToInsert;
    private int lastEditedIndex;
    public InsertTextCommand(TextEditor pEditor, String textToInsert) {
        this.editor = pEditor;
        this.textToInsert = textToInsert;
    }

    @Override
    public void execute() {
        this.lastEditedIndex = editor.currentText().length();
        editor.insertText(this.textToInsert);
    }

    @Override
    public void undo() {
        editor.deleteText(this.lastEditedIndex - 1, this.lastEditedIndex + textToInsert.length());
    }
}
