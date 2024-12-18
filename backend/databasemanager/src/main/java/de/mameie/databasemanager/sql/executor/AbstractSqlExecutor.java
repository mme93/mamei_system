package de.mameie.databasemanager.sql.executor;

import de.mameie.databasemanager.sql.executor.model.SqlPrepStmtIndex;
import de.mameie.databasemanager.sql.executor.model.SqlPrepStmtParamName;
import de.mameie.databasemanager.sql.server.connection.DBServerConnectionFactory;
import de.mameie.databasemanager.sql.server.connection.H2ConnectionFactory;
import de.mameie.databasemanager.util.check.CheckParam;
import de.mameie.databasemanager.util.check.exception.SqlMethodNotImplementedException;
import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.server.database.connection.DBConnectionFactory;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract base class for SQL executors, providing common functionality.
 */
public abstract class AbstractSqlExecutor implements ISqlExecutor {

    private String serverName;

    @Setter
    private String databaseName;

    @Setter
    private String tableName;

    private String STATUS;

    public final String SERVER = "SERVER";

    public final String TEST = "TEST";

    public final String TABLE = "TABLE";

    public final String DATABASE = "DATABASE";

    /**
     * Constructor for initializing the executor with the server name.
     *
     * @param serverName the name of the server
     */
    public AbstractSqlExecutor(String serverName) {
        CheckParam.isNotNull(serverName, "serverName");
        STATUS = SERVER;
        this.serverName = serverName;
    }

    /**
     * Constructor for initializing the executor with the server and database name.
     *
     * @param serverName   the name of the server
     * @param databaseName the name of the database
     */
    public AbstractSqlExecutor(String serverName, String databaseName) {
        CheckParam.isNotNull(serverName, "serverName");
        CheckParam.isNotNull(databaseName, "databaseName");
        STATUS = DATABASE;
        this.serverName = serverName;
        this.databaseName = databaseName;
    }

    /**
     * Constructor for initializing the executor with the server, database, and table name.
     *
     * @param serverName   the name of the server
     * @param databaseName the name of the database
     * @param tableName    the name of the table
     */
    public AbstractSqlExecutor(String serverName, String databaseName, String tableName) {
        CheckParam.isNotNull(serverName, "serverName");
        CheckParam.isNotNull(databaseName, "databaseName");
        CheckParam.isNotNull(tableName, "tableName");
        STATUS = TABLE;
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    /**
     * Executes a query and returns the result set.
     *
     * @param query the query to execute
     * @return the result set of the query
     */
    @Override
    public final ResultSet executeQuery(ISqlQuery query) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = createPreparedStatementFromConnection(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Executes the specified query.
     *
     * @param query the query to execute
     * @return true if the execution was successful, false otherwise
     */
    @Override
    public final boolean execute(ISqlQuery query) throws SQLException {
        PreparedStatement statement = createPreparedStatementFromConnection(query);
        Boolean result =statement.execute();
        return result;
    }

    /**
     * Checks if the query has a result.
     *
     * @param query the query to check
     * @return true if the query has a result, false otherwise
     */
    @Override
    public boolean hasResult(ISqlQuery query)throws SQLException {
        return execute(query);
    }

    /**
     * Executes an update query. Not implemented in the abstract class.
     *
     * @param query the query to execute
     * @return 0
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public final int executeUpdate(ISqlQuery query) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeUpdate"));
    }

    /**
     * Executes a batch of queries. Not implemented in the abstract class.
     *
     * @param query the list of queries to execute
     * @return null
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public final int[] executeBatch(List<ISqlQuery> query) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeBatch"));
    }

    /**
     * Executes a query with prepared statement index. Not implemented in the abstract class.
     *
     * @param query the query to execute
     * @param index the prepared statement index
     * @return null
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public ResultSet executeQuery(ISqlQuery query, SqlPrepStmtIndex index) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeQuery"));
    }

    /**
     * Executes a query with prepared statement index. Not implemented in the abstract class.
     *
     * @param query the query to execute
     * @param index the prepared statement index
     * @return false
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public boolean execute(ISqlQuery query, SqlPrepStmtIndex index) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "execute"));
    }

    /**
     * Checks if the query has a result with prepared statement index. Not implemented in the abstract class.
     *
     * @param query the query to check
     * @param index the prepared statement index
     * @return false
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public boolean hasResult(ISqlQuery query, SqlPrepStmtIndex index) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "hasResult"));
    }

    /**
     * Executes an update query with prepared statement index. Not implemented in the abstract class.
     *
     * @param query the query to execute
     * @param index the prepared statement index
     * @return 0
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public int executeUpdate(ISqlQuery query, SqlPrepStmtIndex index) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeUpdate"));
    }

    /**
     * Executes a batch of queries with prepared statement index. Not implemented in the abstract class.
     *
     * @param query the list of queries to execute
     * @param index the prepared statement index
     * @return null
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtIndex index) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeBatch"));
    }

    /**
     * Executes a query with prepared statement parameter name. Not implemented in the abstract class.
     *
     * @param query     the query to execute
     * @param paramName the prepared statement parameter name
     * @return null
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public ResultSet executeQuery(ISqlQuery query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeQuery"));
    }

    /**
     * Executes a query with prepared statement parameter name. Not implemented in the abstract class.
     *
     * @param query     the query to execute
     * @param paramName the prepared statement parameter name
     * @return false
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public boolean execute(ISqlQuery query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "execute"));
    }

    /**
     * Checks if the query has a result with prepared statement parameter name. Not implemented in the abstract class.
     *
     * @param query     the query to check
     * @param paramName the prepared statement parameter name
     * @return false
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public boolean hasResult(ISqlQuery query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "hasResult"));
    }

    /**
     * Executes an update query with prepared statement parameter name. Not implemented in the abstract class.
     *
     * @param query     the query to execute
     * @param paramName the prepared statement parameter name
     * @return 0
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public int executeUpdate(ISqlQuery query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeUpdate"));
    }

    /**
     * Executes a batch of queries with prepared statement parameter name. Not implemented in the abstract class.
     *
     * @param query     the list of queries to execute
     * @param paramName the prepared statement parameter name
     * @return null
     * @throws SqlMethodNotImplementedException always thrown
     */
    @Override
    public int[] executeBatch(List<ISqlQuery> query, SqlPrepStmtParamName paramName) {
        throw new SqlMethodNotImplementedException(String.format("Method %s is not implemented.", "executeBatch"));
    }

    /**
     * Creates a connection using the provided query and prepares a statement.
     *
     * @param query the query to prepare the statement with
     * @return the prepared statement
     * @throws SQLException if a database access error occurs
     */
    private PreparedStatement createPreparedStatementFromConnection(ISqlQuery query) throws SQLException {
        return createConnection().prepareStatement(query.toSql());
    }

    public Connection createConnection() throws SQLException {
        Connection con = switch (STATUS) {
            case SERVER -> DBServerConnectionFactory.getInstance(serverName).getConnection();
            case TABLE, DATABASE -> DBConnectionFactory.getInstance(serverName, databaseName).getConnection();
            case TEST -> H2ConnectionFactory.getInstance().getConnection();
            default -> throw new RuntimeException(String.format("Status with input %s was not found."));
        };
        return con;
    }

    /**
     * Change the current status (need for test with H2-DB).
     *
     * @param STATUS the status from the needed connection.
     */
    public void changeStatus(String STATUS) {
        this.STATUS = STATUS;
    }


    /**
     *  Check the current status.
     *
     * @param STATUS comparison STATUS.
     * @return true if STATUS match the current STATUS.
     */
    public boolean hasStatus(String STATUS){
        return this.STATUS == STATUS;
    }


}
