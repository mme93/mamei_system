package mamei.de.module.sql.query.clause.grant;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.model.SystemUser;
import mamei.de.module.sql.query.ISqlQuery;

public class SqlGrantUser implements ISqlQuery {

    private ISqlQuery privileges;
    private String user;
    private String sqlEnvironment;

    private SqlGrantUser(ISqlQuery privileges, String user, String sqlEnvironment) {
        this.privileges = privileges;
        this.user = user;
        this.sqlEnvironment = sqlEnvironment;
    }

    @Override
    public String toSql() {
        return String.format("GRANT %s ON %s TO %s", privileges.toSql(), sqlEnvironment, user);
    }

    @Override
    public String getAction() {
        return "GRANT";
    }

    public static SqlGrantUserBuilder builder() {
        return new SqlGrantUserBuilder();
    }

    public static class SqlGrantUserBuilder {

        private ISqlQuery privileges;
        private String host;
        private String user;
        private String sqlEnvironment;

        public SqlGrantUserBuilder withPrivileges(ISqlQuery privileges) {
            this.privileges = privileges;
            return this;
        }

        public SqlGrantUserBuilder withUser(SystemUser user) {
            this.user = user.getName();
            this.host = user.getHost();
            return this;
        }

        public SqlGrantUserBuilder onSqlEnvironment(String database) {
            CheckValue.isNotBlank(database, "database");
            this.sqlEnvironment = String.format("%s.*", database);
            return this;
        }

        public SqlGrantUserBuilder onSqlEnvironment(String database, String table) {
            CheckValue.isNotBlank(database, "database");
            CheckValue.isNotBlank(table, "table");
            this.sqlEnvironment = String.format("%s.%s", database, table);
            return this;
        }

        public SqlGrantUser build() {
            CheckValue.isNotNull(privileges, "privileges");
            CheckValue.isNotBlank(user, "user");
            CheckValue.isNotBlank(host, "host");
            return new SqlGrantUser(privileges, String.format("'%s'@'%s'", user, host), sqlEnvironment);
        }

    }
}
