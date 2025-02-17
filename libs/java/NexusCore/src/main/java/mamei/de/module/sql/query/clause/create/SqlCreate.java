package mamei.de.module.sql.query.clause.create;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.ISqlQuery;

public class SqlCreate implements ISqlQuery {

    private String content;

    private SqlCreate(String content) {
        this.content = content;
    }

    @Override
    public String toSql() {
        return String.format("CREATE %s",content);
    }

    @Override
    public String getAction() {
        return "CREATE";
    }

    public static SqlCreateBuilder create() {
        return new SqlCreateBuilder();
    }

    public static class SqlCreateBuilder {

        private String content;

        public SqlCreateBuilder database(String databaseName) {
            content = String.format("DATABASE %s", databaseName);
            return this;
        }

        public SqlCreate build() {
            CheckValue.isNotBlank(content, "content");
            return new SqlCreate(content);
        }
    }

}
