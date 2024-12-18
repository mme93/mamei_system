package de.mameie.databasemanager.sql.executor.systemuser;

import de.mameie.databasemanager.sql.executor.AbstractSqlExecutor;
import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.query.alias.SqlParamWithAlias;
import de.mameie.databasemanager.sql.query.clause.select.SqlSelect;
import de.mameie.databasemanager.sql.query.clause.show.SqlShow;
import de.mameie.databasemanager.sql.query.grant.SqlGrantTable;
import de.mameie.databasemanager.sql.server.systemuser.model.SystemUser;
import de.mameie.databasemanager.util.check.CheckParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SystemUserSqlExecutor extends AbstractSqlExecutor {

    public SystemUserSqlExecutor(String serverName) {
        super(serverName);
        CheckParam.isNotNull(serverName,"serverName");
    }

    public SystemUserSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
        CheckParam.isNotNull(serverName,"serverName");
        CheckParam.isNotNull(databaseName,"databaseName");
    }

    public List<SystemUser> getAllSystemUser() throws SQLException {
        List<SystemUser>systemUsers= new ArrayList<>();
        if(hasStatus(SERVER)){
            SqlParamWithAlias user= new SqlParamWithAlias("User","USER");
            SqlParamWithAlias host= new SqlParamWithAlias("Host","HOST");
            ResultSet resultSet = executeQuery(SqlSelect.builder().selectWithAlias(user, host).from("mysql.user").build());
            while(resultSet.next()){
                String userName=resultSet.getString(user.getAlias());
                String hostName=resultSet.getString(host.getAlias());
                String grant=getGrantFromUser(userName,hostName);
                systemUsers.add(new SystemUser(userName,hostName,grant));
            }
        }
        return systemUsers;
    }

    public String getGrantFromUser(String user,String host) throws SQLException {
        ISqlQuery grantQuery = SqlGrantTable.builder().withUser(user).withHost(host).build();
        ResultSet resultSet = executeQuery(grantQuery);
        String grant = null;
        while(resultSet.next()){
            grant=resultSet.getString(1);
        }
        return grant;
    }

    public List<SystemUser> getAllGrantedSystemUserFromDatabase(){
        List<SystemUser>systemUsers= new ArrayList<>();

        return systemUsers;
    }

    public List<SystemUser> getAllGrantedSystemUserFromTable(){
        List<SystemUser>systemUsers= new ArrayList<>();

        return systemUsers;
    }

    public boolean isSystemUserGrantedToTable(){

        return true;
    }

    public boolean isSystemUserGrantedToDatabase(){

        return true;
    }

    public boolean grantedSystemUserToTable(){

        return true;
    }

    public boolean grantedSystemUserToDatabase(){

        return true;
    }

}
