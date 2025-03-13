package mamei.de.core.exception;

public class NexusCoreIllegalArgumentException extends RuntimeException {

    public NexusCoreIllegalArgumentException(String message) {
        super(message);
    }

    public NexusCoreIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
