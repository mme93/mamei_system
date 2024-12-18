package de.mameie.databasemanager.util.check.exception;

/**
 * This class represents a custom exception that is thrown when a specific value-related error occurs.
 * It extends the {@link RuntimeException}, allowing it to be used as an unchecked exception.
 *
 * <p>Instances of this exception can be created with a specific error message, and optionally, a cause.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     throw new ValueException("The provided value is invalid.");
 * </pre>
 *
 * @see RuntimeException
 */
public class ValueException extends RuntimeException {

    /**
     * Constructs a new {@code ValueException} with the specified detail message.
     *
     * @param message the detail message
     */
    public ValueException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ValueException} with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause (a {@code Throwable} that caused this exception to be thrown)
     */
    public ValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
