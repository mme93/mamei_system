package mamei.de.module.sql.executor.administration;

import mamei.de.core.utils.CheckParam;
import mamei.de.module.sql.connection.SqlConnectionContext;
import mamei.de.module.sql.executor.AbstractSqlExecutor;
import mamei.de.module.sql.model.ESqlEnvironment;
import mamei.de.module.sql.model.SystemUser;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.clause.alias.ISqlAlias;
import mamei.de.module.sql.query.clause.alias.SqlColumnAlias;
import mamei.de.module.sql.query.clause.create.SqlCreateUser;
import mamei.de.module.sql.query.clause.drop.SqlDropUser;
import mamei.de.module.sql.query.clause.grant.SqlGrantUser;
import mamei.de.module.sql.query.clause.select.SqlSelect;
import mamei.de.module.sql.query.clause.grant.SqlGrantTable;
import mamei.de.module.sql.query.privileges.ESqlPrivilegesTyp;
import mamei.de.module.sql.query.privileges.SqlPrivileges;
import mamei.de.module.sql.query.user.SqlUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public Map<String, List<String>> getGrantFromSystemUser(SystemUser systemUser) throws SQLException {
        ISqlQuery sqlQuery = SqlSelect
                .builder()
                .select("*")
                .from("information_schema.user_privileges")
                .build();
        ResultSet resultSet = executeQuery(sqlQuery);

        Map<String, List<String>> userPrivilegesMap = new HashMap<>();
        while (resultSet.next()) {
            String grantee = resultSet.getString("GRANTEE");
            Pattern pattern = Pattern.compile("'(.*?)'@'.*?'");
            Matcher matcher = pattern.matcher(grantee);
            if (matcher.find() && systemUser.getName().equals(matcher.group(1))) {
                String privilegesTyp = resultSet.getString("PRIVILEGE_TYPE");
                userPrivilegesMap.computeIfAbsent(grantee, k -> new ArrayList<>()).add(privilegesTyp);
            }
        }
        CheckParam.isNotEmpty(userPrivilegesMap, "userPrivilegesMap");
        return userPrivilegesMap;
    }

    public boolean grantSystemUserToTable(SqlPrivileges sqlPrivileges, SystemUser user, String database, String table) {
        ISqlQuery sqlQuery = SqlGrantUser.builder()
                .withUser(user)
                .withPrivileges(sqlPrivileges)
                .onSqlEnvironment(database, table)
                .build();
        return execute(sqlQuery);
    }

    public boolean grantSystemUserToDatabase(SqlPrivileges sqlPrivileges, SystemUser user, String database) {
        ISqlQuery sqlQuery = SqlGrantUser.builder()
                .withUser(user)
                .withPrivileges(sqlPrivileges)
                .onSqlEnvironment(database)
                .build();
        return execute(sqlQuery);
    }

    public boolean grantSystemUserOnDb(SqlPrivileges sqlPrivileges, SystemUser user, String database) {
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
