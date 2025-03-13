package mamei.de.module.sql.connection;

import mamei.de.core.exception.NexusCoreNullPointerException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link ConnectionCredentials}.
 * <p>
 * This class tests the validation logic of SQL connection parameters, ensuring
 * that valid connections are accepted and invalid inputs throw appropriate exceptions.
 */
public class ConnectionCredentialsTest {

    private static final String VALID_IP = "8.8.8.8";

    /**
     * Tests if a valid SQL connection context is correctly identified.
     */
    @Test
    public void shouldReturnTrueForValidConnection() {
        ConnectionCredentials connectionCredentials = new ConnectionCredentials(VALID_IP, "Test", "Test", "Google DNS");
        assertTrue("Expected IP to be valid, but it was not.", connectionCredentials.isValidIp());
    }

    /**
     * Tests if an invalid SQL connection context is correctly identified.
     */
    @Test
    public void shouldReturnFalseForInvalidConnection() {
        ConnectionCredentials connectionCredentials = new ConnectionCredentials("INVALID_IP", "Test", "Test", "Google DNS");
        assertFalse("Expected IP to be invalid, but it was not.", connectionCredentials.isValidIp());
    }

    /**
     * Ensures that passing a null IP address throws a {@link NexusCoreNullPointerException}.
     */
    @Test
    public void shouldThrowExceptionWhenIpIsNull() {
        try {
            new ConnectionCredentials(null, "Test", "Test", "Google DNS");
            fail("Expected NexusCoreNullPointerException for null IP address.");
        } catch (NexusCoreNullPointerException e) {
            assertNotNull("Exception message should not be null", e.getMessage());
        }
    }

    /**
     * Ensures that passing a null username throws a {@link NexusCoreNullPointerException}.
     */
    @Test
    public void shouldThrowExceptionWhenUserNameIsNull() {
        try {
            new ConnectionCredentials(VALID_IP, null, "Test", "Google DNS");
            fail("Expected NexusCoreNullPointerException for null username.");
        } catch (NexusCoreNullPointerException e) {
            assertNotNull("Exception message should not be null", e.getMessage());
        }
    }

    /**
     * Ensures that passing a null password throws a {@link NexusCoreNullPointerException}.
     */
    @Test
    public void shouldThrowExceptionWhenPasswordIsNull() {
        try {
            new ConnectionCredentials(VALID_IP, "Test", null, "Google DNS");
            fail("Expected NexusCoreNullPointerException for null password.");
        } catch (NexusCoreNullPointerException e) {
            assertNotNull("Exception message should not be null", e.getMessage());
        }
    }

    /**
     * Ensures that passing a null server name throws a {@link NexusCoreNullPointerException}.
     */
    @Test
    public void shouldThrowExceptionWhenServerNameIsNull() {
        try {
            new ConnectionCredentials(VALID_IP, "Test", "Test", null);
            fail("Expected NexusCoreNullPointerException for null server name.");
        } catch (NexusCoreNullPointerException e) {
            assertNotNull("Exception message should not be null", e.getMessage());
        }
    }
}
