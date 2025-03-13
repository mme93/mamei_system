package mamei.de.core.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NexusCoreNullPointerExceptionTest {

    @Test
    public void shouldThrowExceptionMessage() {
        String message = "Illegal Argument";
        NexusCoreNullPointerException exception = new NexusCoreNullPointerException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionMessageWithCause() {
        String message = "Illegal Argument";
        Throwable cause = new NullPointerException("Null value encountered");
        NexusCoreNullPointerException exception = new NexusCoreNullPointerException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void shouldExceptionIsRuntimeException() {
        NexusCoreNullPointerException exception = new NexusCoreNullPointerException("Test");

        assertTrue(exception instanceof RuntimeException);
    }
}
