package mamei.de.module.sql.connection;

import mamei.de.core.utils.CheckValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConnectionManager {

    private static List<ConnectionManager> instances = new ArrayList<>();

    private Connection con;
    private ConnectionCredentials context;

    private ConnectionManager(Connection con, ConnectionCredentials context) {
        CheckValue.isNotNull(con, "con");
        CheckValue.isNotNull(context, "context");
        this.con = con;
        this.context = context;
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    public static ConnectionManager getInstance(ConnectionCredentials context) throws SQLException {
        CheckValue.isNotBlank(context.getIp(), "ip");
        CheckValue.isNotBlank(context.getUserName(), "userName");
        CheckValue.isNotBlank(context.getServerName(), "serverName");
        CheckValue.isNotNull(context.getPassword(), "password");
        Optional<ConnectionManager> connectionManagerOpt = existInstance(context);

        if (!connectionManagerOpt.isPresent()) {
            return createConnection(context);
        }
        ConnectionManager connectionManager = connectionManagerOpt.get();
        Connection con = connectionManager.getConnection();

        if (con.isClosed() || !con.isValid(2)) {
            return createConnection(context);
        }

        return connectionManager;

    }

    private static Optional<ConnectionManager> existInstance(ConnectionCredentials context) {
        return instances.stream().filter(instance -> instance.context.match(context)).findAny();
    }

    private static ConnectionManager createConnection(ConnectionCredentials context) throws SQLException {
        ConnectionManager instance = new ConnectionManager(DriverManager.getConnection(
                context.getIp(),
                context.getUserName(),
                context.getPassword()
        ), context);
        instances.add(instance);
        return instance;
    }

}
