package de.mameie.databasemanager.sql.server.service;

import de.mameie.databasemanager.sql.server.database.model.SqlDatabaseOverview;
import de.mameie.databasemanager.sql.server.database.model.SqlLoginDatabase;
import de.mameie.databasemanager.sql.server.database.service.SqlDatabaseService;
import de.mameie.databasemanager.sql.server.database.table.service.SqlTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SqlServerService {

    private final SqlDatabaseService sqlDatabaseService;
    private final SqlTableService sqlTableService;

    @Autowired
    public SqlServerService(SqlDatabaseService sqlDatabaseService, SqlTableService sqlTableService) {
        this.sqlDatabaseService = sqlDatabaseService;
        this.sqlTableService = sqlTableService;
    }

    public List<SqlDatabaseOverview> getServerOverview(String serverName) throws SQLException {
        List<SqlDatabaseOverview> sqlDatabaseOverviews= new ArrayList<>();
        for(String databaseName:sqlDatabaseService.getDatabaseNames(serverName)){
            sqlDatabaseOverviews.add(new SqlDatabaseOverview(
                    databaseName,
                    sqlTableService.getAllTableNames(new SqlLoginDatabase(serverName,databaseName))
            ));
        }
        return sqlDatabaseOverviews;
    }
}
