package org.text.operations.service;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.text.operations.commands.Command;

public class TextOperationInvoker implements Invoker {
    private ConcurrentLinkedDeque<Command> undoCommands;
    private ConcurrentLinkedDeque<Command> redoCommands;
    private Lock lock;
    public TextOperationInvoker() {
        undoCommands = new ConcurrentLinkedDeque<>();
        redoCommands = new ConcurrentLinkedDeque<>();
        this.lock = new ReentrantLock();
    }

    @Override
    public void executeCommand(Command pCommand) {
        lock.lock();
        pCommand.execute();
        undoCommands.offerLast(pCommand);
        redoCommands.clear();
        lock.unlock();
    }

    @Override
    public void undoCommand() {
        if (!undoCommands.isEmpty()) {
            lock.lock();
            Command undoCommand = undoCommands.pollLast();
            redoCommands.offerLast(undoCommand);
            undoCommand.undo();
            lock.unlock();
        } else {
            System.out.println("No command to undo");
        }
    }

    @Override
    public void redoCommand() {
        if (!redoCommands.isEmpty()) {
            lock.lock();
            Command redoCommand = redoCommands.pollLast();
            undoCommands.offerLast(redoCommand);
            redoCommand.execute();
            lock.unlock();
        } else {
            System.out.println("No command to redo");
        }
    }
}
