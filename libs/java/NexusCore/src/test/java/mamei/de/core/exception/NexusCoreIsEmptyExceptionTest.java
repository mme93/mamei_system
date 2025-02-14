package mamei.de.core.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NexusCoreIsEmptyExceptionTest {

    @Test
    public void shouldThrowExceptionMessage() {
        String message = "Illegal Argument";
        NexusCoreIsEmptyException exception = new NexusCoreIsEmptyException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionMessageWithCause() {
        String message = "Illegal Argument";
        Throwable cause = new NullPointerException("Null value encountered");
        NexusCoreIsEmptyException exception = new NexusCoreIsEmptyException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void shouldExceptionIsRuntimeException() {
        NexusCoreIsEmptyException exception = new NexusCoreIsEmptyException("Test");

        assertTrue(exception instanceof RuntimeException);
    }
}
