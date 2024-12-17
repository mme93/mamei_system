package de.mameie.databasemanager.util.check.exception;
/**
 * This class represents a custom exception that is thrown when there is an issue with method parameters.
 * It extends the {@link RuntimeException}, allowing it to be used as an unchecked exception.
 *
 * <p>Instances of this exception can be created with a specific error message, and optionally, a cause.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     if (parameter == null) {
 *         throw new ParamException("Parameter cannot be null.");
 *     }
 * </pre>
 *
 * @see RuntimeException
 */
public class ParamException extends RuntimeException {

    /**
     * Constructs a new {@code ParamException} with the specified detail message.
     *
     * @param message the detail message
     */
    public ParamException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ParamException} with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause (a {@code Throwable} that caused this exception to be thrown)
     */
    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

}
