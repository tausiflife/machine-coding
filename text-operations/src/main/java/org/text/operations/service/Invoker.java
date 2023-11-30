package org.text.operations.service;

import org.text.operations.commands.Command;

public interface Invoker {
    void executeCommand(Command addCommand);
    void undoCommand();
    void redoCommand();
}
