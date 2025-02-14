package mamei.de.module.sql.query.clause.create;

import mamei.de.module.sql.query.ISqlQuery;

public class SqlCreate implements ISqlQuery {

    private SqlCreate() {
    }

    @Override
    public String toSql() {
        return "";
    }

    @Override
    public String getAction() {
        return "";
    }

    public static SqlCreateBuilder build() {
        return new SqlCreateBuilder();
    }

    public static class SqlCreateBuilder {


        public SqlCreate build() {
            return new SqlCreate();
        }
    }

}
