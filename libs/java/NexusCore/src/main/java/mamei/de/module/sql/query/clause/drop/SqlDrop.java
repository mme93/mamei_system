package mamei.de.module.sql.query.clause.drop;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.model.DatabaseElements;
import mamei.de.module.sql.query.ISqlQuery;

public class SqlDrop implements ISqlQuery {

    private String content;
    private DatabaseElements.EDatabaseElements databaseElements;

    private SqlDrop(String content, DatabaseElements.EDatabaseElements databaseElements) {
        CheckValue.isNotBlank(content, "content");
        CheckValue.isNotNull(databaseElements, "databaseElements");
        this.content = content;
        this.databaseElements = databaseElements;
    }

    @Override
    public String toSql() {
        return String.format("DROP %s %s", databaseElements.name(), content);
    }

    @Override
    public String getAction() {
        return "DROP";
    }

    public static SqlDropBuilder drop() {
        return new SqlDropBuilder();
    }

    public static class SqlDropBuilder {

        private String content;
        private DatabaseElements.EDatabaseElements databaseElements;

        public SqlDropBuilder database(String databaseName) {
            databaseElements = DatabaseElements.EDatabaseElements.DATABASE;
            content = databaseName;
            return this;
        }

        public SqlDrop build() {
            return new SqlDrop(content, databaseElements);
        }
    }
}
