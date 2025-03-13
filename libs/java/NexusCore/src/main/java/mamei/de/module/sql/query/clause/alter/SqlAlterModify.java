package mamei.de.module.sql.query.clause.alter;

import mamei.de.module.sql.query.column.ISqlColumn;
import mamei.de.module.sql.query.privileges.ESqlPrivilegesTyp;

public class SqlAlterModify implements ISqlAlter {

    private String tableName;
    private ISqlColumn column;

    private SqlAlterModify(String tableName, ISqlColumn column) {
        this.tableName = tableName;
        this.column = column;
    }

    @Override
    public String toSql() {
        return String.format("ALTER TABLE %s MODIFY COLUMN %s", tableName, column.toSql());
    }

    @Override
    public String getAction() {
        return "MODIFY";
    }

    public static SqlAlterModifyBuilder modify() {
        return new SqlAlterModifyBuilder();
    }

    public static class SqlAlterModifyBuilder {

        private String tableName;
        private ISqlColumn column;

        public SqlAlterModifyBuilder withTable(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public SqlAlterModifyBuilder withColumn(ISqlColumn column) {
            this.column = column;
            return this;
        }

        public SqlAlterModify build() {
            return new SqlAlterModify(tableName, column);
        }
    }
}
