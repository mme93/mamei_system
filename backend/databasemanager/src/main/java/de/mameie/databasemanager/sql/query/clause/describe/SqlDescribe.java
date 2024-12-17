package de.mameie.databasemanager.sql.query.clause.describe;

import de.mameie.databasemanager.sql.query.ISqlQuery;

public class SqlDescribe implements ISqlQuery {

    private String tableName;

    private SqlDescribe() {
    }

    public static SqlDescribe builder() {
        return new SqlDescribe();
    }

    public SqlDescribe describe(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public ISqlQuery build() {
        return this;
    }

    @Override
    public String toSql() {
        return String.format("SHOW COLUMNS FROM %s",tableName);
    }

    @Override
    public String getAction() {
        return "DESCRIBE";
    }
}
