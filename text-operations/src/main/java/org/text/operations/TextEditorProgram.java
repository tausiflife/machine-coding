package org.text.operations;

import org.text.operations.commands.Command;
import org.text.operations.commands.DeleteTextCommand;
import org.text.operations.commands.InsertTextCommand;
import org.text.operations.service.Invoker;
import org.text.operations.service.TextEditor;
import org.text.operations.service.TextOperationInvoker;

public class TextEditorProgram {
    public static void main(String[] args) {
        Invoker invoker = new TextOperationInvoker();
        TextEditor editor = new TextEditor();
        Command insertCommand = new InsertTextCommand(editor, "Hello world");
        invoker.executeCommand(insertCommand);
        System.out.println(editor.currentText());
        Command deleteCommand = new DeleteTextCommand(editor, 6, 11);
        invoker.executeCommand(deleteCommand);
        System.out.println(editor.currentText());
        invoker.undoCommand();
        System.out.println(editor.currentText());
        invoker.redoCommand();
        invoker.redoCommand();
        System.out.println(editor.currentText());
    }
}