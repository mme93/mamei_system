package mamei.de.module.sql.connection;

import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link ConnectionManager}.
 * This class tests the connection handling of the ConnectionManager, ensuring that
 * database connections are properly opened and closed.
 */
public class ConnectionManagerTest {

    /**
     * JUnit rule for setting up a MariaDB database instance.
     * This provides the necessary database environment for testing.
     */
    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    /**
     * Tests whether a database connection is properly closed after being used.
     * It verifies that:
     * <ul>
     *     <li>The connection is initially open</li>
     *     <li>The connection is closed after calling {@code closeConnection()}</li>
     * </ul>
     *
     * @throws SQLException if an error occurs while handling the database connection
     */
    @Test
    public void shouldCloseConnection() throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.getInstance(MariaDBRule.CONNECTION_CREDENTIALS);
        Connection con = connectionManager.getConnection();
        assertFalse(con.isClosed());
        connectionManager.closeConnection();
        assertTrue(con.isClosed());
    }
}
