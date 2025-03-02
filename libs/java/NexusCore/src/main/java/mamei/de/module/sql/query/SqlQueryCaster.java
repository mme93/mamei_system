package mamei.de.module.sql.query;

import mamei.de.core.exception.NexusCoreIllegalArgumentException;
import mamei.de.module.sql.query.clause.alter.ISqlAlter;
import mamei.de.module.sql.query.column.ISqlColumn;

public class SqlQueryCaster {

    public static ISqlQuery cast(Object obj) {
        if (obj instanceof ISqlAlter) {
            ISqlAlter alter = (ISqlAlter) obj;
            return new CastSqlQuery(alter.toSql(), alter.getAction());
        } else if (obj instanceof ISqlColumn) {
            ISqlColumn column = (ISqlColumn) obj;
            return new CastSqlQuery(column.toSql(), column.getAction());
        } else if (obj instanceof ISqlAlter) {
            ISqlAlter alter = (ISqlAlter) obj;
            return new CastSqlQuery(alter.toSql(), alter.getAction());
        } else {
            throw new NexusCoreIllegalArgumentException(String.format("No instance found for %s", obj.getClass()));
        }
    }

    private static class CastSqlQuery implements ISqlQuery {

        private String sql;
        private String action;

        public CastSqlQuery(String sql, String action) {
            this.sql = sql;
            this.action = action;
        }

        @Override
        public String toSql() {
            return sql;
        }

        @Override
        public String getAction() {
            return action;
        }
    }

}
