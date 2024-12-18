package de.mameie.databasemanager.sql.executor.table.data.backup;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;

public class TableBackupSqlExecutor extends TableSqlExecutor {

    public TableBackupSqlExecutor(String serverName, String databaseName, String tableName) {
        super(serverName, databaseName, tableName);
    }
}
