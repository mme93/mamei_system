package de.mameie.databasemanager.sql.query.clause.show;

import de.mameie.databasemanager.sql.query.ISqlQuery;

public class SqlShow implements ISqlQuery {

    private String value;

    private SqlShow(String value) {
        this.value = value;
    }

    public static SqlShow showTables() {
        return new SqlShow("TABLES");
    }

    @Override
    public String toSql() {
        return String.format("SHOW %s", value);
    }

    @Override
    public String getAction() {
        return "SHOW";
    }
}
