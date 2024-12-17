package de.mameie.databasemanager.sql.executor.table.data.copy;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;
import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.query.clause.create.copy.SqlCreateCopy;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableRow;
import de.mameie.databasemanager.sql.server.database.table.model.view.TableMetadata;

import java.sql.SQLException;
import java.util.List;

public class TableCopySqlExecutor extends TableSqlExecutor {

    private String tableName;
    private List<TableMetadata> metaDataList;
    private List<DatabaseTableRow> tableRows;

    private TableCopySqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
        this.tableName = tableName;
    }

    public void changeDestinationTable(String tableName) {
        super.setTableName(tableName);
    }

    public void changeDestinationDatabase(String databaseName) {
        super.setDatabaseName(databaseName);
    }

    public void copyTable(String copyTableName)throws SQLException {
        ISqlQuery iSqlQuery = SqlCreateCopy
                .create()
                .withCopyTableName(copyTableName)
                .withExistTableName(tableName)
                .addAllColumns()
                .build();
        super.execute(iSqlQuery);
    }

    public boolean loadMetaData() {
        this.metaDataList = super.getMetaData();
        return true;
    }

    public void loadTableContent() {
        this.tableRows = super.getRows(this.metaDataList);
    }

    public static TableCopySqlExecutorBuilder builder() {
        return new TableCopySqlExecutorBuilder();
    }

    public static class TableCopySqlExecutorBuilder {
        private String serverName;
        private String databaseName;
        private String tableName;

        /**
         * Sets the server name for the builder.
         *
         * @param serverName the name of the server
         * @return the builder instance
         */
        public TableCopySqlExecutorBuilder withServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        /**
         * Sets the database name for the builder.
         *
         * @param databaseName the name of the database
         * @return the builder instance
         */
        public TableCopySqlExecutorBuilder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        /**
         * Sets the table name for the builder.
         *
         * @param tableName the name of the table
         * @return the builder instance
         */
        public TableCopySqlExecutorBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        /**
         * Builds and returns a {@code TableViewSqlExecutor} instance.
         *
         * @return a new {@code TableViewSqlExecutor} instance
         */
        public TableCopySqlExecutor build() {
            return new TableCopySqlExecutor(serverName, databaseName, tableName);
        }
    }

}
