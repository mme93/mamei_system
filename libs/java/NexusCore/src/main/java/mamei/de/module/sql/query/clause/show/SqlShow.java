package mamei.de.module.sql.query.clause.show;

import mamei.de.module.sql.query.ISqlQuery;

public class SqlShow implements ISqlQuery {

    private SqlShow() {

    }

    @Override
    public String toSql() {
        return "";
    }

    @Override
    public String getAction() {
        return "";
    }

    public static SqlShowBuilder builder() {
        return new SqlShowBuilder();
    }

    public static class SqlShowBuilder {


        public SqlShowBuilder showUserGrant() {

            return this;
        }

        public SqlShow build() {
            return new SqlShow();
        }
    }
}
