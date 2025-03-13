package mamei.de.module.sql.query.clause.alter;

import mamei.de.module.sql.query.privileges.ESqlPrivilegesTyp;

public class SqlAlterDrop implements ISqlAlter {

    private String tableName;
    private String columnName;

    private SqlAlterDrop(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    @Override
    public String toSql() {
        return String.format("ALTER TABLE %s DROP COLUMN %s",tableName,columnName);
    }

    @Override
    public String getAction() {
        return ESqlPrivilegesTyp.DROP.name();
    }

    public static SqlAlterDropBuilder drop() {
        return new SqlAlterDropBuilder();
    }

    public static class SqlAlterDropBuilder {

        private String tableName;
        private String columnName;

        public SqlAlterDropBuilder withTable(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public SqlAlterDropBuilder withColumn(String columnName) {
            this.columnName = columnName;
            return this;
        }

        public SqlAlterDrop build() {
            return new SqlAlterDrop(tableName, columnName);
        }
    }
}
