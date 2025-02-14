package mamei.de.module.sql.executor.administration;

import mamei.de.module.sql.connection.SqlConnectionContext;
import mamei.de.module.sql.executor.AbstractSqlExecutor;
import mamei.de.module.sql.model.ESqlEnvironment;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.clause.alias.ISqlAlias;
import mamei.de.module.sql.query.clause.alias.SqlColumnAlias;
import mamei.de.module.sql.query.clause.create.SqlCreateUser;
import mamei.de.module.sql.query.clause.drop.SqlDropUser;
import mamei.de.module.sql.query.clause.select.SqlSelect;
import mamei.de.module.sql.query.clause.grant.SqlGrantTable;
import mamei.de.module.sql.query.privileges.ESqlPrivilegesTyp;
import mamei.de.module.sql.query.privileges.SqlPrivileges;
import mamei.de.module.sql.query.user.SqlUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministrationSqlExecutor extends AbstractSqlExecutor {

    public AdministrationSqlExecutor(SqlConnectionContext connectionContext) {
        super(connectionContext, ESqlEnvironment.SERVER);
    }


    @Override
    public void changeSqlEnvironment(ESqlEnvironment sqlEnvironment, SqlConnectionContext connectionContext) {
        super.changeSqlEnvironment(sqlEnvironment, connectionContext);
    }

    public List<SystemUser> getAllSystemUser() throws SQLException {
        List<SystemUser> systemUsers = new ArrayList<>();
        ISqlAlias user = new SqlColumnAlias("USER", "User");
        ISqlAlias host = new SqlColumnAlias("HOST", "Host");
        ISqlQuery query = SqlSelect.builder().select(user, host).from("mysql.user").build();
        ResultSet resultSet = executeQuery(query);
        while (resultSet.next()) {
            String userName = resultSet.getString(user.getAlias());
            String hostName = resultSet.getString(host.getAlias());
            String grant = showGrantsForUser(userName, hostName);
            systemUsers.add(new SystemUser(userName, hostName, grant));
        }
        return systemUsers;
    }

    public int createSystemUser(SystemUser user, String password) {
        ISqlQuery sqlQuery = SqlCreateUser.builder()
                .createUser(user.getHost(), user.getName()).withPassword(password).build();
        return executeUpdate(sqlQuery);
    }

    public int deleteSystemUser(SystemUser user) {
        ISqlQuery sqlQuery = SqlDropUser.builder().dropUser(user.getName(), user.getHost()).build();
        return executeUpdate(sqlQuery);
    }

    public boolean grantSystemUser(SqlPrivileges sqlPrivileges, SystemUser user, String database) {
        ISqlQuery sqlQuery = SqlUser.builder()
                .withAction(ESqlPrivilegesTyp.GRANT)
                .withHost(user.getHost())
                .withUser(user.getName())
                .withPrivileges(database, sqlPrivileges)
                .build();
        return execute(sqlQuery);
    }

    public String showGrantsForUser(String user, String host) throws SQLException {
        ISqlQuery grantQuery = SqlGrantTable.builder().withUser(user).withHost(host).build();
        ResultSet resultSet = executeQuery(grantQuery);
        String grant = null;
        while (resultSet.next()) {
            grant = resultSet.getString(1);
        }
        return grant;
    }


}
