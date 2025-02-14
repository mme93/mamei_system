package mamei.de.module.sql.query.clause.select;

import mamei.de.module.sql.query.ISqlQuery;
import mamei.de.module.sql.query.clause.alias.ISqlAlias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlSelect implements ISqlQuery {

    private final String WILDCARD = "*";
    private List<String> columns;
    private String tableName;

    private SqlSelect(List<String> columns, String tableName) {
        this.columns = columns;
        this.tableName = tableName;
    }


    @Override
    public String toSql() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append(columns.isEmpty() ? WILDCARD : String.join(",", columns));
        query.append(" FROM " + tableName);
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

        public SqlSelectBuilder select(String... column) {
            columns.addAll(Arrays.stream(column).toList());
            return this;
        }

        public SqlSelectBuilder select(ISqlAlias... alias) {
            Arrays.stream(alias).toList().forEach(sqlAlias -> columns.add(sqlAlias.getParamWithAlias()));
            return this;
        }

        public SqlSelectBuilder selectAll() {
            return this;
        }

        public SqlSelectBuilder from(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public SqlSelect build() {
            return new SqlSelect(columns, tableName);
        }
    }
}
