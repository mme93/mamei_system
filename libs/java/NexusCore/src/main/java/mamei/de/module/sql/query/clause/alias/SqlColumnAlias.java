package mamei.de.module.sql.query.clause.alias;

import mamei.de.core.utils.CheckValue;

public class SqlColumnAlias implements ISqlAlias {

    private String alias;
    private String param;

    public SqlColumnAlias(String param, String alias) {
        CheckValue.isNotBlank(param, "param");
        CheckValue.isNotBlank(alias, "alias");
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
