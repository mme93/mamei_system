package mamei.de.module.sql.connection;


import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class H2ConnectionFactoryTest {

    /**
     * Tests if the same instance of {@link H2ConnectionFactory} is returned every time.
     * It also checks if the connection obtained from the factory is not closed initially.
     *
     * @throws SQLException if a database access error occurs
     */
    @Test
    public void testIfInstanceIsSameRef() throws SQLException {
        H2ConnectionFactory firstFactory = H2ConnectionFactory.getInstance();
        H2ConnectionFactory secondFactory = H2ConnectionFactory.getInstance();
        assertFalse(firstFactory.getConnection().isClosed());
        assertEquals(firstFactory,secondFactory);
    }

    /**
     * Tests the {@link H2ConnectionFactory#closeConnection()} method.
     * It ensures that the connection is properly closed when the method is called.
     *
     * @throws SQLException if a database access error occurs
     */
    @Test
    public void testCloseConnection() throws SQLException {
        H2ConnectionFactory factory = H2ConnectionFactory.getInstance();
        assertFalse(factory.getConnection().isClosed());
        factory.closeConnection();
        assertTrue(factory.getConnection().isClosed());
    }

}
