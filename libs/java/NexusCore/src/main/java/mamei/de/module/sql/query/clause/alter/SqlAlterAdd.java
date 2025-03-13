package mamei.de.module.sql.query.clause.alter;

import mamei.de.module.sql.query.column.ISqlColumn;
import mamei.de.module.sql.query.privileges.ESqlPrivilegesTyp;

public class SqlAlterAdd implements ISqlAlter {

    private String tableName;
    private ISqlColumn column;

    private SqlAlterAdd(String tableName, ISqlColumn column) {
        this.tableName = tableName;
        this.column = column;
    }

    @Override
    public String toSql() {
        return String.format("ALTER TABLE %s ADD COLUMN %s", tableName, column.toSql());
    }

    @Override
    public String getAction() {
        return ESqlPrivilegesTyp.DROP.name();
    }

    public static SqlAlterAddBuilder add() {
        return new SqlAlterAddBuilder();
    }

    public static class SqlAlterAddBuilder {

        private String tableName;
        private ISqlColumn column;

        public SqlAlterAddBuilder withTable(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public SqlAlterAddBuilder withColumn(ISqlColumn column) {
            this.column = column;
            return this;
        }

        public SqlAlterAdd build() {
            return new SqlAlterAdd(tableName, column);
        }
    }
}
