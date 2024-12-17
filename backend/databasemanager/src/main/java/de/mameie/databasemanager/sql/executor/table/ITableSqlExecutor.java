package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.query.field.ISqlFieldDefinition;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableRow;
import de.mameie.databasemanager.sql.server.database.table.model.view.TableMetadata;

import java.sql.SQLException;
import java.util.List;

/**
 * The {@code ITableSqlExecutor} interface defines the operations that can be performed on SQL tables.
 * Implementations of this interface provide methods for creating, dropping, checking existence,
 * and retrieving metadata of tables in a SQL database.
 */
public interface ITableSqlExecutor {

    /**
     * Drops the table associated with this executor.
     *
     * @return {@code true} if the table was successfully dropped, {@code false} otherwise.
     */
    boolean drop() throws SQLException;

    /**
     * Drops the specified table.
     *
     * @param tableName the name of the table to drop.
     * @return {@code true} if the table was successfully dropped, {@code false} otherwise.
     */
    boolean drop(String tableName) throws SQLException;

    /**
     * Checks if the table associated with this executor exists.
     *
     * @return {@code true} if the table exists, {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean exist() throws SQLException;

    /**
     * Checks if the specified table exists.
     *
     * @param tableName the name of the table to check.
     * @return {@code true} if the table exists, {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean exist(String tableName) throws SQLException;

    /**
     * Creates a table with the specified field definitions.
     *
     * @param fieldDefinitionList a list of field definitions for the table.
     * @return {@code true} if the table was successfully created, {@code false} otherwise.
     */
    boolean createTable(List<ISqlFieldDefinition> fieldDefinitionList) throws SQLException;

    /**
     * Retrieves metadata for the table associated with this executor.
     *
     * @return a list of {@code TableMetadata} objects representing the table's metadata.
     */
    List<TableMetadata> getMetaData();

    /**
     * Retrieves the names of all tables in the database.
     *
     * @return a list of table names.
     * @throws SQLException if a database access error occurs.
     */
    List<String> getTableNames() throws SQLException;

    /**
     * Retrieves rows of the table based on the provided metadata.
     *
     * @param headers a list of {@code TableMetadata} objects representing the table's metadata
     * @return a list of {@code DatabaseTableRow} objects containing the table's data
     */
    List<DatabaseTableRow> getRows(List<TableMetadata> headers);
}
