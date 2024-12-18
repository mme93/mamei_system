package de.mameie.databasemanager.sql.executor.table.view;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;
import de.mameie.databasemanager.sql.query.clause.select.SqlSelect;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableCell;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableRow;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableView;
import de.mameie.databasemanager.sql.server.database.table.model.view.TableMetadata;
import de.mameie.databasemanager.util.check.CheckParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TableViewSqlExecutor} class provides methods for generating views of database tables.
 * It extends the {@code TableSqlExecutor} class and adds functionality to retrieve and format table data as views.
 */
public class TableViewSqlExecutor extends TableSqlExecutor {

    private String databaseName;
    private String tableName;
    private String serverName;

    /**
     * Constructs a {@code TableViewSqlExecutor} with the specified server name and database name.
     *
     * @param serverName   the name of the server
     * @param databaseName the name of the database
     */
    private TableViewSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
        CheckParam.isNotNull(serverName, "serverName");
        CheckParam.isNotNull(databaseName, "databaseName");
        this.serverName = serverName;
        this.databaseName = databaseName;
    }

    /**
     * Constructs a {@code TableViewSqlExecutor} with the specified server name, database name, and table name.
     *
     * @param serverName   the name of the server
     * @param databaseName the name of the database
     * @param tableName    the name of the table
     */
    private TableViewSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        CheckParam.isNotNull(serverName, "serverName");
        CheckParam.isNotNull(databaseName, "databaseName");
        CheckParam.isNotNull(tableName, "tableName");
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    /**
     * Generates a view of the table including its metadata and data rows.
     *
     * @return a {@code DatabaseTableView} object representing the table view
     */
    public DatabaseTableView generateTableView() {
        List<TableMetadata> metadata = super.getMetaData();
        List<DatabaseTableRow> rows = super.getRows(metadata);
        return DatabaseTableView
                .builder()
                .withTableName(tableName)
                .withMetaData(metadata)
                .withRows(rows)
                .build();
    }

    /**
     * Returns a new {@code TableViewSqlExecutorBuilder} for building a {@code TableViewSqlExecutor} instance.
     *
     * @return a new {@code TableViewSqlExecutorBuilder}
     */
    public static TableViewSqlExecutorBuilder builder() {
        return new TableViewSqlExecutorBuilder();
    }

    /**
     * Builder class for {@code TableViewSqlExecutor}.
     */
    public static class TableViewSqlExecutorBuilder {
        private String serverName;
        private String databaseName;
        private String tableName;

        /**
         * Sets the server name for the builder.
         *
         * @param serverName the name of the server
         * @return the builder instance
         */
        public TableViewSqlExecutorBuilder withServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        /**
         * Sets the database name for the builder.
         *
         * @param databaseName the name of the database
         * @return the builder instance
         */
        public TableViewSqlExecutorBuilder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        /**
         * Sets the table name for the builder.
         *
         * @param tableName the name of the table
         * @return the builder instance
         */
        public TableViewSqlExecutorBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        /**
         * Builds and returns a {@code TableViewSqlExecutor} instance.
         *
         * @return a new {@code TableViewSqlExecutor} instance
         */
        public TableViewSqlExecutor build() {
            if (tableName != null) {
                return new TableViewSqlExecutor(serverName, databaseName, tableName);
            }
            return new TableViewSqlExecutor(serverName, databaseName);
        }
    }

}
