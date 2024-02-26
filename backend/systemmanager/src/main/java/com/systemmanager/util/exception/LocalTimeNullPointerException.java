package com.systemmanager.util.exception;

public class LocalTimeNullPointerException extends RuntimeException{

    public LocalTimeNullPointerException(String message) {
        super(message);
    }

    public LocalTimeNullPointerException(String message, Throwable cause) {
        super(message, cause);
    }
}
