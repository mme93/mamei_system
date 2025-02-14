package mamei.de.module.sql.query.clause.alias;

public class SqlColumnAlias implements ISqlAlias{

    private String alias;
    private String param;

    public SqlColumnAlias(String alias, String param) {
        this.alias = alias;
        this.param = param;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getParamWithAlias() {
        return String.format("%s AS %s", param, alias);
    }
}
