package mamei.de.module.sql.query.clause.create;

import static mamei.de.module.sql.model.DatabaseElements.EDatabaseElements.*;
import static mamei.de.module.sql.model.DatabaseElements.*;

import mamei.de.core.utils.CheckValue;
import mamei.de.core.utils.CompareValue;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.column.ISqlColumn;

import java.util.List;

public class SqlCreate implements ISqlQuery {

    private EDatabaseElements databaseElements;
    private String content;

    private SqlCreate(String content, EDatabaseElements databaseElements) {
        CheckValue.isNotNull(databaseElements, "databaseElements");
        this.databaseElements = databaseElements;
        this.content = content;
    }

    @Override
    public String toSql() {
        String databaseElement = databaseElements.name().toUpperCase();
        if (CompareValue.isBlank(content)) {
            return String.format("CREATE %s", databaseElement);
        }
        return String.format("CREATE %s %s", databaseElement, content);
    }

    @Override
    public String getAction() {
        return "CREATE";
    }

    public static SqlCreateBuilder create() {
        return new SqlCreateBuilder();
    }

    public static class SqlCreateBuilder {
        private EDatabaseElements databaseElements;
        private String content;

        public SqlCreateBuilder database(String databaseName) {
            content = databaseName;
            databaseElements = DATABASE;
            return this;
        }

        public SqlCreateBuilder table(String tableName, List<ISqlColumn> columnDefinitions) {
            List<String> columns = columnDefinitions.stream().map(ISqlColumn::toSql).toList();
            content = String.format("%s (%s)", tableName, String.join(",", columns));
            databaseElements = TABLE;
            return this;
        }

        public SqlCreate build() {
            CheckValue.isNotBlank(content, "content");
            return new SqlCreate(content, databaseElements);
        }

    }

}
