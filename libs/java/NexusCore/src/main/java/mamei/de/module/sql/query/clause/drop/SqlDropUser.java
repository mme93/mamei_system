package mamei.de.module.sql.query.clause.drop;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.ISqlQuery;

import java.util.ArrayList;
import java.util.List;

public class SqlDropUser implements ISqlQuery {

    private List<String> users;

    private SqlDropUser(List<String> users) {
        CheckValue.isNotEmpty(users, "users");
        this.users = users;
    }

    @Override
    public String toSql() {
        return String.format("DROP USER %s", String.join(",", users));
    }

    @Override
    public String getAction() {
        return "DROP";
    }

    public static SqlDropUserBuilder builder() {
        return new SqlDropUserBuilder();
    }

    public static class SqlDropUserBuilder {

        private List<String> users = new ArrayList<>();

        public SqlDropUserBuilder dropUser(String user, String host) {
            users.add(String.format("'%s'@'%S'", user, host));
            return this;
        }

        public SqlDropUser build() {
            return new SqlDropUser(users);
        }
    }

}
