package org.lld.locker.exception;

public class PickUpCodeExpiredException extends RuntimeException{
    public PickUpCodeExpiredException(String message) {
        super(message);
    }
}
