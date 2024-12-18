package de.mameie.databasemanager.sql.server.database.service;

import de.mameie.databasemanager.sql.executor.database.DatabaseSqlExecutor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SqlDatabaseService {

    public boolean deleteDatabase(String databaseName,String serverName)throws SQLException {
        DatabaseSqlExecutor executor = new DatabaseSqlExecutor(serverName);
        return executor.drop(databaseName);
    }

    public boolean createDatabase(String databaseName,String serverName)throws SQLException {
        DatabaseSqlExecutor executor = new DatabaseSqlExecutor(serverName);
        return executor.createDatabase(databaseName);
    }

    public List<String>
    getDatabaseNames(String serverName) {
        DatabaseSqlExecutor executor = new DatabaseSqlExecutor(serverName);
        return  executor.show();
    }
}
