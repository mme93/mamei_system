package de.mameie.databasemanager.util.check.exception;

/**
 * This class represents a custom exception that is thrown when a SQL method is not implemented.
 * It extends the {@link RuntimeException}, allowing it to be used as an unchecked exception.
 *
 * <p>Instances of this exception can be created with a specific error message, and optionally, a cause.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     throw new SqlMethodNotImplementedException("This SQL method is not implemented.");
 * </pre>
 *
 * @see RuntimeException
 */
public class SqlMethodNotImplementedException extends RuntimeException{

    /**
     * Constructs a new {@code SqlMethodNotImplementedException} with the specified detail message.
     *
     * @param message the detail message
     */
    public SqlMethodNotImplementedException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code SqlMethodNotImplementedException} with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause (a {@code Throwable} that caused this exception to be thrown)
     */
    public SqlMethodNotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }
}
