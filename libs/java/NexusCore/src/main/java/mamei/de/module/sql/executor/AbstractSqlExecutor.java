package mamei.de.module.sql.executor;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.connection.SqlConnectionFactory;
import mamei.de.module.sql.model.ESqlEnvironment;
import mamei.de.module.sql.query.ISqlQuery;

import java.sql.*;

public abstract class AbstractSqlExecutor implements ISqlExecutor {

    private ConnectionCredentials connectionContext;
    private ESqlEnvironment sqlEnvironment;

    public AbstractSqlExecutor(ConnectionCredentials connectionContext, ESqlEnvironment sqlEnvironment) {
        CheckValue.isNotNull(connectionContext, "connectionContext");
        CheckValue.isNotNull(sqlEnvironment, "sqlEnvironment");
        this.connectionContext = connectionContext;
        this.sqlEnvironment = sqlEnvironment;
    }

    public ResultSet executeQuery(ISqlQuery query) {
        ResultSet resultSet = null;
        try {
            Connection con = SqlConnectionFactory.getInstance(connectionContext).getConnection();
            PreparedStatement statement = con.prepareStatement(query.toSql());
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public int executeUpdate(ISqlQuery query) {
        try {
            Connection con = SqlConnectionFactory.getInstance(connectionContext).getConnection();
            Statement statement = con.createStatement();
            return statement.executeUpdate(query.toSql());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean execute(ISqlQuery query) {
        try {
            Connection con = SqlConnectionFactory.getInstance(connectionContext).getConnection();
            Statement statement = con.createStatement();
            return statement.execute(query.toSql());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void changeSqlEnvironment(ESqlEnvironment sqlEnvironment, ConnectionCredentials connectionContext) {
        this.sqlEnvironment = sqlEnvironment;
        this.connectionContext = connectionContext;
    }

    public boolean hasStatus(ESqlEnvironment sqlEnvironment) {
        return this.sqlEnvironment.equals(sqlEnvironment);
    }

    public ESqlEnvironment getSqlEnvironment() {
        return sqlEnvironment;
    }
}
