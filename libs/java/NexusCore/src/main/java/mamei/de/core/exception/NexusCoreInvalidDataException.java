package mamei.de.core.exception;

public class NexusCoreInvalidDataException extends RuntimeException{

    public NexusCoreInvalidDataException(String message) {
        super(message);
    }

    public NexusCoreInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
