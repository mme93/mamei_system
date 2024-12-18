package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.executor.table.exception.TableMetaDataNotFoundException;
import de.mameie.databasemanager.sql.executor.table.metadata.TableMetaDataSqlExecutor;
import de.mameie.databasemanager.sql.query.SqlDatabaseClause;
import de.mameie.databasemanager.sql.query.clause.create.SqlCreate;
import de.mameie.databasemanager.sql.query.clause.describe.SqlDescribe;
import de.mameie.databasemanager.sql.query.clause.select.SqlSelect;
import de.mameie.databasemanager.sql.query.clause.show.SqlShow;
import de.mameie.databasemanager.sql.query.field.ISqlFieldDefinition;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableCell;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableRow;
import de.mameie.databasemanager.sql.server.database.table.model.view.TableMetadata;
import org.apache.tomcat.util.buf.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code TableSqlExecutor} class provides implementations for executing SQL queries
 * related to tables. It extends {@code AbstractSqlExecutor} and implements {@code ITableSqlExecutor}.
 * This class includes methods for creating, dropping, checking existence, and retrieving metadata of tables.
 */
public class TableSqlExecutor extends AbstractSqlExecutor implements ITableSqlExecutor {

    private String databaseName;
    private String tableName;
    private String serverName;

    /**
     * Constructs a {@code TableSqlExecutor} with the specified server name and database name.
     *
     * @param serverName   the name of the server.
     * @param databaseName the name of the database.
     */
    public TableSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
        this.serverName = serverName;
        this.databaseName = databaseName;
    }

    /**
     * Constructs a {@code TableSqlExecutor} with the specified server name, database name, and table name.
     *
     * @param serverName   the name of the server.
     * @param databaseName the name of the database.
     * @param tableName    the name of the table.
     */
    public TableSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    /**
     * Drops the table associated with this executor.
     *
     * @return {@code true} if the table was successfully dropped, {@code false} otherwise.
     */
    @Override
    public boolean drop() throws SQLException {
        return super.execute(
                SqlDatabaseClause
                        .drop()
                        .table()
                        .name(tableName)
                        .build()
        );
    }

    /**
     * Drops the specified table.
     *
     * @param tableName the name of the table to drop.
     * @return {@code true} if the table was successfully dropped, {@code false} otherwise.
     */
    @Override
    public boolean drop(String tableName) throws SQLException {
        return super.execute(
                SqlDatabaseClause
                        .drop()
                        .table()
                        .name(tableName)
                        .build()
        );
    }

    /**
     * Checks if the table associated with this executor exists.
     *
     * @return {@code true} if the table exists, {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean exist() throws SQLException {
        Connection con = super.createConnection();
        DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    /**
     * Checks if the specified table exists.
     *
     * @param tableName the name of the table to check.
     * @return {@code true} if the table exists, {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean exist(String tableName) throws SQLException {
        Connection con = super.createConnection();
        DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }

    /**
     * Creates a table with the specified field definitions.
     *
     * @param fieldDefinitionList a list of field definitions for the table.
     * @return {@code true} if the table was successfully created, {@code false} otherwise.
     */
    @Override
    public boolean createTable(List<ISqlFieldDefinition> fieldDefinitionList) throws SQLException {
        return super.execute(
                SqlCreate
                        .create()
                        .tableName(tableName)
                        .addColumns(fieldDefinitionList)
                        .build()
        );
    }

    /**
     * Retrieves metadata for the table associated with this executor.
     *
     * @return a list of {@code TableMetadata} objects representing the table's metadata.
     */
    @Override
    public List<TableMetadata> getMetaData() {
        try {
            return new TableMetaDataSqlExecutor(serverName, databaseName, tableName).init().loadMetaData();
        } catch (SQLException e) {
            throw new TableMetaDataNotFoundException(String.format("Can't read the column information header from table: %s.", tableName), e);
        }
    }

    /**
     * Retrieves the names of all tables in the database.
     *
     * @return a list of table names.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<String> getTableNames() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        ResultSet resultSet = super.executeQuery(SqlShow.showTables());
        while (resultSet.next()) {
            tableNames.add(resultSet.getString(1));
        }
        return tableNames;
    }

    @Override
    public List<DatabaseTableRow> getRows(List<TableMetadata> headers) {
        List<DatabaseTableRow> databaseTableRows = new ArrayList<>();
        ResultSet resultSet = this.executeQuery(SqlSelect.builder().select(SqlSelect.WILDCARD).from(tableName));
        try {
            int index = 1;
            while (resultSet.next()) {
                List<DatabaseTableCell> databaseTableCells = new ArrayList<>();
                for (TableMetadata header : headers) {
                    databaseTableCells.add(new DatabaseTableCell(resultSet.getString(header.getField())));
                }
                databaseTableRows.add(new DatabaseTableRow(index, databaseTableCells));
                index++;
            }
            return databaseTableRows;
        } catch (SQLException e) {
            throw new RuntimeException("Can't read the value.", e);
        }
    }

    @Override
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        super.setDatabaseName(databaseName);
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
        super.setTableName(tableName);
    }
}
