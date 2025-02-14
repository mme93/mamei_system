package mamei.de.module.sql.rule;

import mamei.de.module.sql.connection.SqlConnectionContext;
import org.junit.rules.ExternalResource;
import org.testcontainers.containers.MariaDBContainer;

public class MariaDBRule extends ExternalResource {

    public static MariaDBContainer<?> mariaDB;

    public static SqlConnectionContext CONNECTION_CONTEXT;

    static {
        System.setProperty("testcontainers.reuse.enable", "true");
        System.setProperty("DOCKER_HOST", "tcp://127.0.0.1:2375");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MariaDB JDBC-Treiber nicht gefunden", e);
        }
    }

    @Override
    protected void before() throws Throwable {
        mariaDB = new MariaDBContainer<>("mariadb:10.6")
                .withDatabaseName("db")
                .withUsername("root")
                .withPassword("");
        mariaDB.start();

        CONNECTION_CONTEXT = new SqlConnectionContext(
                mariaDB.getJdbcUrl(), mariaDB.getUsername(), mariaDB.getPassword(), "Test", "db");
    }

    @Override
    protected void after() {
        if (mariaDB != null && mariaDB.isRunning()) {
            mariaDB.stop();
        }
    }
}
