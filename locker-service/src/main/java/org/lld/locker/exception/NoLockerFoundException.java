package org.lld.locker.exception;

public class NoLockerFoundException extends RuntimeException{
    public NoLockerFoundException(String message) {
        super(message);
    }
}
