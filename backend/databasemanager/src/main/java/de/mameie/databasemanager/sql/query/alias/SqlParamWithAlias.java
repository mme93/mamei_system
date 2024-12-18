package de.mameie.databasemanager.sql.query.alias;

public class SqlParamWithAlias implements ISqlParamWithAlias {

    private String paramWithAlias;
    private String alias;

    public SqlParamWithAlias(String param, String alias) {
        this.alias = alias;
        paramWithAlias = String.format("%s AS %s", param, alias);
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String getParamWithAlias() {
        return paramWithAlias;
    }
}
