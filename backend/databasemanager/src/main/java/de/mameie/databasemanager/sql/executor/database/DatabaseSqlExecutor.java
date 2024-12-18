package de.mameie.databasemanager.sql.executor.database;

import de.mameie.databasemanager.sql.server.connection.DBServerConnectionFactory;
import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.query.SqlDatabaseClause;
import de.mameie.databasemanager.util.check.CheckServerName;
import lombok.Getter;
import lombok.Setter;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DatabaseSqlExecutor} class provides SQL execution functionalities specific to database operations.
 * It extends the {@code AbstractSqlExecutor} to leverage its execution capabilities.
 * This class allows creating, dropping, and listing databases.
 */
@Getter
@Setter
public class DatabaseSqlExecutor extends AbstractSqlExecutor {

    private String serverName;
    private ISqlQuery query;

    /**
     * Constructs a {@code DatabaseSqlExecutor} with the specified server name.
     *
     * @param serverName the name of the server
     */
    public DatabaseSqlExecutor(String serverName) {
        super(serverName);
        CheckServerName.exist(serverName);
        this.serverName = serverName;
    }

    /**
     * Drops the specified database.
     *
     * @param databaseName the name of the database to be dropped
     * @return {@code true} if the operation was successful, {@code false} otherwise
     */
    public boolean drop(String databaseName)throws SQLException{
        return super.execute(
                SqlDatabaseClause
                        .drop()
                        .database()
                        .name(databaseName)
                        .build()
        );
    }

    /**
     * Creates a new database with the specified name.
     *
     * @param databaseName the name of the database to be created
     * @return {@code true} if the operation was successful, {@code false} otherwise
     */
    public boolean createDatabase(String databaseName) throws SQLException{
        return super.execute(
                SqlDatabaseClause
                        .create()
                        .database()
                        .name(databaseName)
                        .build()
        );
    }

    /**
     * Retrieves a list of all database names available on the server.
     *
     * @return a list of database names
     */
    public List<String> show() {
        List<String> databaseNames = new ArrayList<>();
        try {
            DatabaseMetaData metaData = DBServerConnectionFactory.getInstance(serverName).getConnection().getMetaData();
            ResultSet resultSet = metaData.getCatalogs();

            while (resultSet.next()) {
                String databaseName = resultSet.getString("TABLE_CAT");
                databaseNames.add(databaseName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseNames;
    }
}
