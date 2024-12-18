package de.mameie.databasemanager.sql.query.clause.delete;

import de.mameie.databasemanager.sql.query.ISqlQuery;

public class SqlDelete implements ISqlQuery {

    private String tableName;
    private String condition;

    public SqlDelete from(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SqlDelete where(String condition) {
        this.condition = condition;
        return this;
    }

    public ISqlQuery build() {
      return this;
    }

    @Override
    public String toSql() {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(tableName);
        if (condition != null) {
            query.append(" WHERE ").append(condition);
        }
        return query.toString();
    }

    @Override
    public String getAction() {
        return null;
    }
}
