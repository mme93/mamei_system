package de.mameie.databasemanager.sql.executor.systemuser.administration;

import de.mameie.databasemanager.sql.executor.systemuser.SystemUserSqlExecutor;

public class AdministrationSystemUserSqlExecutor extends SystemUserSqlExecutor {

    private AdministrationSystemUserSqlExecutor(String serverName) {
        super(serverName);
    }

    private AdministrationSystemUserSqlExecutor(String serverName, String databaseName) {
        super(serverName, databaseName);
    }

    public static AdministrationSystemUserSqlExecutorBuilder builder() {
        return new AdministrationSystemUserSqlExecutorBuilder();
    }

    public static class AdministrationSystemUserSqlExecutorBuilder {
        private String serverName;
        private String databaseName;

        public AdministrationSystemUserSqlExecutorBuilder withServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        public AdministrationSystemUserSqlExecutorBuilder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }


        public AdministrationSystemUserSqlExecutor build() {
            if(databaseName == null){
                return new AdministrationSystemUserSqlExecutor(serverName);
            }
            return new AdministrationSystemUserSqlExecutor(serverName, databaseName);
        }
    }
}
