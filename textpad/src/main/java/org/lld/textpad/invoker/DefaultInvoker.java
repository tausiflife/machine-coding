package org.lld.textpad.invoker;

import org.lld.textpad.commands.UndoableCommand;

import java.util.ArrayDeque;

public class DefaultInvoker implements Invoker{
    private ArrayDeque<UndoableCommand> undoCommands;
    private ArrayDeque<UndoableCommand> redoCommands;

    public DefaultInvoker() {
        undoCommands = new ArrayDeque<>();
        redoCommands = new ArrayDeque<>();
    }

    @Override
    public void executeCommand(UndoableCommand command) {
        command.execute();
        undoCommands.offerLast(command);
        redoCommands.clear();
    }

    @Override
    public void undoCommand() {
        if(!undoCommands.isEmpty()) {
            UndoableCommand lastCommand = undoCommands.pollLast();
            lastCommand.undo();
            redoCommands.offerLast(lastCommand);
        }
    }

    @Override
    public void redoCommand() {
        if(!redoCommands.isEmpty()) {
            UndoableCommand lastCommand = redoCommands.pollLast();
            lastCommand.execute();
            undoCommands.offerLast(lastCommand);
        }
    }
}
