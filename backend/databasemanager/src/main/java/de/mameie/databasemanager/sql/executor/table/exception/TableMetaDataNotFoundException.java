package de.mameie.databasemanager.sql.executor.table.exception;

public class TableMetaDataNotFoundException extends RuntimeException{

    public TableMetaDataNotFoundException(String message) {
        super(message);
    }

    public TableMetaDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
