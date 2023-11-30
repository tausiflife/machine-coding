package org.text.operations;

import org.text.operations.commands.Command;
import org.text.operations.commands.DeleteTextCommand;
import org.text.operations.commands.InsertTextCommand;
import org.text.operations.service.TextEditor;
import org.text.operations.service.TextOperationInvoker;

public class TextEditorThreadSafe {
    public static void main(String[] args) throws InterruptedException {
        TextOperationInvoker invoker = new TextOperationInvoker();
        TextEditor editor = new TextEditor();
        Thread customer1 = new Thread(() -> {
            try {
                Command add = new InsertTextCommand(editor, "Hello 1 ");
                invoker.executeCommand(add);
                System.out.println(editor.currentText());
                Thread.sleep(1000);
                add = new InsertTextCommand(editor, " world 1");
                invoker.executeCommand(add);
                System.out.println(editor.currentText());
                Thread.sleep(1000);
                Command delete = new DeleteTextCommand(editor, 0, 2);
                invoker.executeCommand(delete);
                System.out.println(editor.currentText());
                Thread.sleep(1000);
                invoker.undoCommand();
                System.out.println(editor.currentText());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        TextEditor editor1 = new TextEditor();
        Thread customer2 = new Thread(() -> {
            try {
                Command add = new InsertTextCommand(editor1, "Hello 2 ");
                invoker.executeCommand(add);
                System.out.println(editor1.currentText());
                Thread.sleep(1000);
                add = new InsertTextCommand(editor1, " world 2");
                invoker.executeCommand(add);
                System.out.println(editor1.currentText());
                Thread.sleep(1000);
                Command delete = new DeleteTextCommand(editor1, 3, 6);
                invoker.executeCommand(delete);
                System.out.println(editor1.currentText());
                Thread.sleep(1000);
                invoker.undoCommand();
                System.out.println(editor1.currentText());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        customer1.start();
        customer2.start();
        customer1.join();
        customer2.join();
    }
}
