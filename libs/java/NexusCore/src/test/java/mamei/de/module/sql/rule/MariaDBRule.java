package mamei.de.module.sql.rule;

import mamei.de.module.sql.connection.ConnectionCredentials;
import org.junit.rules.ExternalResource;
import org.testcontainers.containers.MariaDBContainer;

/**
 * JUnit Rule that manages the lifecycle of a MariaDB container for testing purposes.
 * <p>
 * This rule sets up a MariaDB container using Testcontainers, which is started before each test
 * and stopped after the test completes. It also provides a {@link ConnectionCredentials} to be used
 * in tests for establishing a database connection to the running MariaDB container.
 * </p>
 * <p>
 * The rule sets system properties for Testcontainers, including enabling container reuse and
 * specifying the Docker host URL. Additionally, it loads the MariaDB JDBC driver to ensure the
 * necessary driver is available for connection.
 * </p>
 */
public class MariaDBRule extends ExternalResource {

    /**
     * The MariaDB container that will be started and stopped for each test.
     */
    public static MariaDBContainer<?> mariaDB;

    /**
     * The SQL connection context to interact with the MariaDB database.
     * This is initialized when the MariaDB container starts.
     */
    public static ConnectionCredentials CONNECTION_CREDENTIALS;

    /**
     * The SQL connection context to interact with the MariaDB database.
     * This is initialized when the MariaDB container starts.
     */
    public static ConnectionCredentials CONNECTION_DB_CREDENTIALS;

    static {
        System.setProperty("testcontainers.reuse.enable", "true");
        System.setProperty("DOCKER_HOST", "tcp://127.0.0.1:2375");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MariaDB JDBC driver not found", e);
        }
    }

    /**
     * Starts the MariaDB container before each test.
     * <p>
     * This method is automatically invoked by JUnit before each test is executed.
     * It starts the MariaDB container and initializes the {@link ConnectionCredentials}
     * with the appropriate connection details (URL, username, password).
     * </p>
     *
     * @throws Throwable if the container cannot be started or if there is an issue with initialization
     */
    @Override
    protected void before() throws Throwable {
        mariaDB = new MariaDBContainer<>("mariadb:10.6")
                .withDatabaseName("db")
                .withUsername("root")
                .withPassword("");
        mariaDB.start();

        CONNECTION_CREDENTIALS = new ConnectionCredentials(
                mariaDB.getJdbcUrl(), mariaDB.getUsername(), mariaDB.getPassword(), "Test");
        CONNECTION_DB_CREDENTIALS = new ConnectionCredentials(
                mariaDB.getJdbcUrl().substring(0, mariaDB.getJdbcUrl().length() - 3), mariaDB.getUsername(),
                mariaDB.getPassword(), "Test", "db");
    }

    /**
     * Stops the MariaDB container after each test.
     * <p>
     * This method is automatically invoked by JUnit after each test is executed.
     * It ensures that the MariaDB container is properly stopped to release resources.
     * </p>
     */
    @Override
    protected void after() {
        if (mariaDB != null && mariaDB.isRunning()) {
            mariaDB.stop();
        }
    }
}
