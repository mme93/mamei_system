package mamei.de.module.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlConnectionFactory {

    private static List<SqlConnectionFactory> instances = new ArrayList<>();

    private SqlConnectionContext context;

    private Connection con;

    private SqlConnectionFactory(SqlConnectionContext context, Connection con) {
        this.con = con;
        this.context = context;
    }


    public static synchronized SqlConnectionFactory getInstance(SqlConnectionContext context) throws SQLException {
        SqlConnectionFactory instance;
        String serverName = context.getServerName();
        Optional<SqlConnectionFactory> opt = instances.
                stream().
                filter(connectionFactory -> connectionFactory.getContext().getServerName().equals(serverName)).findAny();

        if (!opt.isPresent()) {
            // System.out.println("No connection found, create new connection.");
            return createNewDatabaseConnectionSingleton(context);
        }
        instance = opt.get();

        if (instance.getConnection().isClosed() || !instance.getConnection().isValid(2)) {
            //System.out.println("Connection found, but was closed, create new connection.");
            instances.remove(instance);
            return createNewDatabaseConnectionSingleton(context);
        }
        // System.out.println("Found existing connection.");
        return instance;
    }

    private static SqlConnectionFactory createNewDatabaseConnectionSingleton(SqlConnectionContext context) throws SQLException {
        SqlConnectionFactory instance = new SqlConnectionFactory(context, createConnection(context));
        instances.add(instance);
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    private static Connection createConnection(SqlConnectionContext context) throws SQLException {
        return DriverManager.getConnection(
                context.getIp(),
                context.getUserName(),
                context.getPassword()
        );
    }

    public void closeConnection() throws SQLException {
        if (!this.con.isClosed()) {
            this.con.close();
        }
    }

    public SqlConnectionContext getContext() {
        return context;
    }
}
