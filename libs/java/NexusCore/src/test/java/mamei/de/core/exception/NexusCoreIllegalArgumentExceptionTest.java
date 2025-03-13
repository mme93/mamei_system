package mamei.de.core.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NexusCoreIllegalArgumentExceptionTest {

    @Test
    public void shouldThrowExceptionMessage() {
        String message = "Illegal Argument";
        NexusCoreIllegalArgumentException exception = new NexusCoreIllegalArgumentException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionMessageWithCause() {
        String message = "Illegal Argument";
        Throwable cause = new NullPointerException("Null value encountered");
        NexusCoreIllegalArgumentException exception = new NexusCoreIllegalArgumentException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void shouldExceptionIsRuntimeException() {
        NexusCoreIllegalArgumentException exception = new NexusCoreIllegalArgumentException("Test");

        assertTrue(exception instanceof RuntimeException);
    }

}
