package mamei.de.module.sql.executor.database;

import mamei.de.module.sql.connection.SqlConnectionContext;
import mamei.de.module.sql.executor.AbstractSqlExecutor;
import mamei.de.module.sql.model.ESqlEnvironment;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.clause.create.SqlCreate;
import mamei.de.module.sql.query.clause.drop.SqlDrop;
import mamei.de.module.sql.query.clause.show.SqlShow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseSqlExecutor extends AbstractSqlExecutor {

    public DatabaseSqlExecutor(SqlConnectionContext connectionContext) {
        super(connectionContext, ESqlEnvironment.DATABASE);
    }

    public boolean create(String databaseName) {
        ISqlQuery sqlQuery = SqlCreate
                .create()
                .database(databaseName)
                .build();
        return execute(sqlQuery);
    }

    public List<String> show() throws SQLException {
        List<String>results=new ArrayList<>();
        ISqlQuery sqlQuery = SqlShow.show().showDatabase().build();
        ResultSet resultSet = executeQuery(sqlQuery);
        while (resultSet.next()) {
            String result = resultSet.getString(1);
            results.add(result);
        }
        return results;
    }

    public boolean drop(String databaseName) {
        ISqlQuery sqlQuery = SqlDrop
                .drop()
                .database(databaseName)
                .build();
        return execute(sqlQuery);
    }

}
