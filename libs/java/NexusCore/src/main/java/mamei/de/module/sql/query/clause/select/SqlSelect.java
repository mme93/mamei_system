package mamei.de.module.sql.query.clause.select;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.clause.alias.ISqlAlias;
import mamei.de.module.sql.query.condition.ISqlCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlSelect implements ISqlQuery {

    private final String WILDCARD = "*";
    private List<String> columns;
    private String tableName;
    private ISqlCondition condition;

    private SqlSelect(List<String> columns, String tableName) {
        CheckValue.isNotEmpty(columns, "columns");
        CheckValue.isNotBlank(tableName, "tableName");
        this.columns = columns;
        this.tableName = tableName;
    }

    private SqlSelect(List<String> columns, String tableName, ISqlCondition condition) {
        CheckValue.isNotEmpty(columns, "columns");
        CheckValue.isNotBlank(tableName, "tableName");
        CheckValue.isNotNull(condition, "condition");
        this.columns = columns;
        this.tableName = tableName;
        this.condition = condition;
    }

    @Override
    public String toSql() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append(columns.isEmpty() ? WILDCARD : String.join(",", columns));
        query.append(" FROM " + tableName);
        if (condition != null) {
            query.append(" WHERE " + condition.getCondition());
        }
        return query.toString();
    }

    @Override
    public String getAction() {
        return "SELECT";
    }

    public static SqlSelectBuilder builder() {
        return new SqlSelectBuilder();
    }

    public static class SqlSelectBuilder {

        private List<String> columns = new ArrayList<>();
        private String tableName;
        private ISqlCondition condition;

        public SqlSelectBuilder select(String... column) {
            columns.addAll(Arrays.stream(column).toList());
            return this;
        }

        public SqlSelectBuilder select(ISqlAlias... alias) {
            Arrays.stream(alias).toList().forEach(sqlAlias -> columns.add(sqlAlias.getParamWithAlias()));
            return this;
        }

        public SqlSelectBuilder selectAll() {
            columns.clear();
            columns.add("*");
            return this;
        }

        public SqlSelectBuilder from(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public SqlSelectBuilder where(ISqlCondition condition) {
            this.condition = condition;
            return this;
        }

        public SqlSelect build() {
            if (condition != null) {
                return new SqlSelect(columns, tableName, condition);
            }
            return new SqlSelect(columns, tableName);
        }
    }
}
