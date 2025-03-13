package mamei.de.core.exception;

public class NexusCoreIsEmptyException extends RuntimeException {

    public NexusCoreIsEmptyException(String message) {
        super(message);
    }

    public NexusCoreIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
