package mamei.de.module.sql.executor.database.table;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.AbstractSqlExecutor;
import mamei.de.module.sql.model.ESqlEnvironment;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.clause.drop.SqlDrop;
import mamei.de.module.sql.query.clause.show.SqlShow;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableSqlExecutor extends AbstractSqlExecutor {

    public TableSqlExecutor(ConnectionCredentials connectionContext) {
        super(connectionContext, ESqlEnvironment.TABLE);
        CheckValue.isNotNull(connectionContext.getDatabaseName(), "database");
    }

    public List<String> show() throws SQLException {
        List<String> results = new ArrayList<>();
        ISqlQuery sqlQuery = SqlShow.show().showTables().build();
        ResultSet resultSet = executeQuery(sqlQuery);
        while (resultSet.next()) {
            String result = resultSet.getString(1);
            results.add(result);
        }
        return results;
    }

    public boolean drop(String tableName) {
        ISqlQuery sqlQuery = SqlDrop.drop().table(tableName).build();
        return execute(sqlQuery);
    }


    public boolean create(String target) {
        return false;
    }

    public boolean alter(String target) {

        return false;
    }

    public boolean exist(String tableName) throws SQLException {
        Connection con = getConnection();
        DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName.toUpperCase(), new String[]{"TABLE"})) {
            return resultSet.next();
        }
    }
}
