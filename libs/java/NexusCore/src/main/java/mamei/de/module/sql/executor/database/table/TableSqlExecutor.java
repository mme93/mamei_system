package mamei.de.module.sql.executor.database.table;

import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.AbstractSqlExecutor;
import mamei.de.module.sql.model.ESqlEnvironment;

import java.sql.SQLException;
import java.util.List;

public class TableSqlExecutor extends AbstractSqlExecutor {

    public TableSqlExecutor(ConnectionCredentials connectionContext) {
        super(connectionContext, ESqlEnvironment.TABLE);
    }


    public List<String> show() throws SQLException {
        return List.of();
    }

    public boolean drop(String target) {
        return false;
    }


    public boolean create(String target) {
        return false;
    }

    public boolean alter(String target) {

        return false;
    }

    public boolean exist(String databaseName){

        return true;
    }
}
