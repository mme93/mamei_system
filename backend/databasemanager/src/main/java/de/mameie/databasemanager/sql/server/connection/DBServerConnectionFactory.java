package de.mameie.databasemanager.sql.server.connection;

import de.mameie.databasemanager.util.helper.StaticPropertyHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBServerConnectionFactory {

    private static List<DBServerConnectionFactory> instances = new ArrayList<>();

    private String serverName;

    private Connection con;

    private DBServerConnectionFactory(String serverName, Connection con) {
        this.con = con;
        this.serverName = serverName;
    }


    public static synchronized DBServerConnectionFactory getInstance(String serverName) throws SQLException {
        DBServerConnectionFactory instance;

        Optional<DBServerConnectionFactory> opt = instances.
                stream().
                filter(databaseConnection -> databaseConnection.serverName.equals(serverName)).findAny();

        if (!opt.isPresent()) {
            System.out.println("No connection found, create new connection.");
            return createNewDatabaseConnectionSingleton(serverName);
        }
        instance = opt.get();

        if (instance.getConnection().isClosed() || !instance.getConnection().isValid(2)) {
            System.out.println("Connection found, but was closed, create new connection.");
            instances.remove(instance);
            return createNewDatabaseConnectionSingleton(serverName);
        }
        System.out.println("Found existing connection.");
        return instance;
    }

    private static DBServerConnectionFactory createNewDatabaseConnectionSingleton(String serverName) throws SQLException {
        DBServerConnectionFactory instance;
        instance = new DBServerConnectionFactory(serverName, createConnection(serverName));
        instances.add(instance);
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    private static Connection createConnection(String serverName) throws SQLException {
        if (serverName.equals(DBServerSettings.CLOUD_SERVER)) {
            return DriverManager.getConnection(
                    StaticPropertyHolder.getStaticNameForCloudServerIp(),
                    StaticPropertyHolder.getStaticNameForCloudServerUserName(),
                    StaticPropertyHolder.getStaticNameForCloudServerPassword()
            );
        } else if (serverName.equals(DBServerSettings.CLOUD_XXL)) {
            return DriverManager.getConnection(
                    StaticPropertyHolder.getStaticNameForCloudXxlIp(),
                    StaticPropertyHolder.getStaticNameForCloudXxlUserName(),
                    StaticPropertyHolder.getStaticNameForCloudServerPassword()
            );
        }
        throw new RuntimeException(String.format("Can`t find server name: %s.", serverName));
    }

    public void closeConnection() throws SQLException {
        if (!this.con.isClosed()) {
            this.con.close();
        }
    }

    public String getServerName() {
        return serverName;
    }
}

