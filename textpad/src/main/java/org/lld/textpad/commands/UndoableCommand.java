package org.lld.textpad.commands;

public interface UndoableCommand {
    void execute();
    void undo();
}
