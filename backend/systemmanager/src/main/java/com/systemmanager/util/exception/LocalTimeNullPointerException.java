package com.systemmanager.util.exception;

/**
 * Custom exception class for handling null pointers related to local time operations.
 */
public class LocalTimeNullPointerException extends RuntimeException{

    /**
     * Constructs a new LocalTimeNullPointerException with the specified detail message.
     *
     * @param message the detail message
     */
    public LocalTimeNullPointerException(String message) {
        super(message);
    }

    /**
     * Constructs a new LocalTimeNullPointerException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public LocalTimeNullPointerException(String message, Throwable cause) {
        super(message, cause);
    }
}


