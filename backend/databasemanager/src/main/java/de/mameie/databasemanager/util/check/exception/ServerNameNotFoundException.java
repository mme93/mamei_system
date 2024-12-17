package de.mameie.databasemanager.util.check.exception;

public class ServerNameNotFoundException extends RuntimeException{

    public ServerNameNotFoundException(String message) {
        super(String.format("No server found by name: %s",message));
    }

    public ServerNameNotFoundException(String message, Throwable cause) {
        super(String.format("No server found by name: %s",message), cause);
    }
}
