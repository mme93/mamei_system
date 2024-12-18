package de.mameie.databasemanager.sql.server.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

/**
 * Test class for the {@link H2ConnectionFactory} class.
 * This class contains unit tests to verify the functionality of the H2ConnectionFactory.
 */
@SpringBootTest
public class H2ConnectionFactoryTest {

    /**
     * Tests if the same instance of {@link H2ConnectionFactory} is returned every time.
     * It also checks if the connection obtained from the factory is not closed initially.
     *
     * @throws SQLException if a database access error occurs
     */
    @Test
    void testIfInstanceIsSameRef() throws SQLException {
        H2ConnectionFactory firstFactory = H2ConnectionFactory.getInstance();
        H2ConnectionFactory secondFactory = H2ConnectionFactory.getInstance();
        Assertions.assertFalse(firstFactory.getConnection().isClosed());
        Assertions.assertEquals(firstFactory,secondFactory);
    }

    /**
     * Tests the {@link H2ConnectionFactory#closeConnection()} method.
     * It ensures that the connection is properly closed when the method is called.
     *
     * @throws SQLException if a database access error occurs
     */
    @Test
    void testCloseConnection() throws SQLException {
        H2ConnectionFactory factory = H2ConnectionFactory.getInstance();
        Assertions.assertFalse(factory.getConnection().isClosed());
        factory.closeConnection();
        Assertions.assertTrue(factory.getConnection().isClosed());
    }

}
