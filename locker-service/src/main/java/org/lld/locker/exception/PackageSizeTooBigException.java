package org.lld.locker.exception;

public class PackageSizeTooBigException extends RuntimeException{
    public PackageSizeTooBigException(String message) {
        super(message);
    }
}
