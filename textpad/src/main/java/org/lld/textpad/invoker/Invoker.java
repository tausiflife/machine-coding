package org.lld.textpad.invoker;

import org.lld.textpad.commands.UndoableCommand;

public interface Invoker {
    void executeCommand(UndoableCommand command);
    void undoCommand();
    void redoCommand();
}
