package mamei.de.module.sql.query.user;

import mamei.de.core.utils.check.CheckParam;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.privileges.ESqlPrivilegesTyp;
import mamei.de.module.sql.query.privileges.SqlPrivileges;

public class SqlUser implements ISqlQuery {

    private String host;
    private String user;
    private String password;

    private SqlUser(String host, String user, String password) {
        CheckParam.isNotBlank(host, "host");
        CheckParam.isNotBlank(user, "user");
        CheckParam.isNotBlank(password, "password");
        this.host = host;
        this.user = user;
        this.password = password;
    }

    @Override
    public String toSql() {
        return String.format("CREATE USER '%s'@'%s' IDENTIFIED BY '%s'",
                user, host, password);

    }

    @Override
    public String getAction() {
        return "CREATE";
    }

    public static SqlCreateUserBuilder builder() {
        return new SqlCreateUserBuilder();
    }

    public static class SqlCreateUserBuilder {
        private String host;
        private String user;
        private String password;
        private String privileges;
        private ESqlPrivilegesTyp sqlAction;

        public SqlCreateUserBuilder withAction(ESqlPrivilegesTyp sqlAction) {
            this.sqlAction = sqlAction;
            return this;
        }

        public SqlCreateUserBuilder withHost(String host) {
            this.host = host;
            return this;
        }

        public SqlCreateUserBuilder withUser(String user) {
            this.user = user;
            return this;
        }

        public SqlCreateUserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public SqlCreateUserBuilder withPrivileges(String grantDatabase, SqlPrivileges sqlPrivileges) {
            this.privileges = String.format("%s ON %s");
            return this;
        }

        public SqlCreateUserBuilder withAllPrivileges(String grantDatabase) {
            this.privileges = String.format("%s ON %s", ESqlPrivilegesTyp.ALL_PRIVILEGES.getPrivilege(), grantDatabase);
            return this;
        }

        public SqlCreateUserBuilder withAllPrivileges() {
            String database = "*.*";
            this.privileges = String.format("%s ON %s", ESqlPrivilegesTyp.ALL_PRIVILEGES.getPrivilege(), database);
            return this;
        }

        public SqlUser build() {
            CheckParam.isNotNull(host, "host");
            CheckParam.isNotNull(user, "user");
            CheckParam.isNotNull(password, "password");
            return new SqlUser(host, user, password);
        }
    }
}
