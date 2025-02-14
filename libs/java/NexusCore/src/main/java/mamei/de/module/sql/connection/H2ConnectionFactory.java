package mamei.de.module.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton factory for creating and managing a connection to an H2 database.
 * This class ensures that only one instance of the database connection is created and shared.
 */
public class H2ConnectionFactory {

    private final static String JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;MODE=MySQL;";
    private final static String USER = "sa";
    private final static String PASS = "";

    private static H2ConnectionFactory h2ConnectionFactory;

    private Connection con;

    /**
     * Private constructor to prevent direct instantiation.
     *
     * @param con the database connection
     */
    private H2ConnectionFactory(Connection con) {
        this.con = con;
    }

    /**
     * Returns the singleton instance of the {@link H2ConnectionFactory}.
     * If no connection exists or the connection is closed, a new connection is created.
     *
     * @return the singleton instance of the {@link H2ConnectionFactory}
     * @throws SQLException if a database access error occurs
     */
    public static synchronized H2ConnectionFactory getInstance() throws SQLException {
        if (h2ConnectionFactory == null || h2ConnectionFactory.getConnection().isClosed()) {
            System.out.println("No connection found/open, create new connection.");
            createNewDatabaseConnectionSingleton();
        }
        return h2ConnectionFactory;
    }

    /**
     * Creates a new singleton instance of the {@link H2ConnectionFactory} with a new database connection.
     */
    private static void createNewDatabaseConnectionSingleton() {
        try {
            Class.forName(JDBC_DRIVER);
            h2ConnectionFactory = new H2ConnectionFactory(DriverManager.getConnection(DB_URL, USER, PASS));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current database connection.
     *
     * @return the current database connection
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * Closes the current database connection.
     * If an error occurs during closing, it is caught and printed.
     */
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
