package mamei.de.module.sql.query.clause.create;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.ISqlQuery;

public class SqlCreateUser implements ISqlQuery {

    private String host;
    private String user;
    private String password;

    private SqlCreateUser(String host, String user, String password) {
        CheckValue.isNotBlank(host, "host");
        CheckValue.isNotBlank(user, "user");
        CheckValue.isNotBlank(password, "password");
        this.host = host;
        this.user = user;
        this.password = password;
    }

    @Override
    public String toSql() {
        return String.format("CREATE USER '%s'@'%s' IDENTIFIED BY '%s'", user, host, password);
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

        public SqlCreateUserBuilder createUser(String host, String user) {
            this.host = host;
            this.user = user;
            return this;
        }

        public SqlCreateUserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public SqlCreateUser build() {
            return new SqlCreateUser(host, user, password);
        }
    }
}
