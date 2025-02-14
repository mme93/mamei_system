package mamei.de.core.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NexusCoreMethodNotImplementedExceptionTest {

    @Test
    public void shouldThrowExceptionMessage() {
        String message = "Illegal Argument";
        NexusCoreMethodNotImplementedException exception = new NexusCoreMethodNotImplementedException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionMessageWithCause() {
        String message = "Illegal Argument";
        Throwable cause = new NullPointerException("Null value encountered");
        NexusCoreMethodNotImplementedException exception = new NexusCoreMethodNotImplementedException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void shouldExceptionIsRuntimeException() {
        NexusCoreMethodNotImplementedException exception = new NexusCoreMethodNotImplementedException("Test");

        assertTrue(exception instanceof RuntimeException);
    }
}
