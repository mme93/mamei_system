package de.mameie.databasemanager.sql.server.database.table.service;

import de.mameie.databasemanager.sql.executor.table.TableSqlExecutor;
import de.mameie.databasemanager.sql.executor.table.view.TableViewSqlExecutor;
import de.mameie.databasemanager.sql.query.field.ISqlFieldDefinition;
import de.mameie.databasemanager.sql.server.database.model.SqlLoginDatabase;
import de.mameie.databasemanager.sql.server.database.model.SqlLoginTable;
import de.mameie.databasemanager.sql.server.database.table.model.view.DatabaseTableView;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SqlTableService {

    public List<String> getAllTableNames(SqlLoginDatabase sqlLoginDatabase) throws SQLException {
        return new TableSqlExecutor(
                sqlLoginDatabase.getServerName(),
                sqlLoginDatabase.getDatabaseName())
                .getTableNames();
    }

    public DatabaseTableView getTableByName(String serverName, String database, String tableName) {
        return TableViewSqlExecutor
                .builder()
                .withServerName(serverName)
                .withDatabaseName(database)
                .withTableName(tableName)
                .build()
                .generateTableView();
    }

    public boolean createTable(SqlLoginTable loginTable, List<ISqlFieldDefinition> fieldDefinitionList) {
        throw new RuntimeException("Not implemented Method!");
    }
}
