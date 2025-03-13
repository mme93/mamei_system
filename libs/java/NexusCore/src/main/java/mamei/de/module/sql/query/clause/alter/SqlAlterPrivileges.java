package mamei.de.module.sql.query.clause.alter;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.query.column.ISqlColumn;
import mamei.de.module.sql.query.privileges.ESqlPrivilegesTyp;

public class SqlAlterPrivileges implements ISqlAlter {


    private String name;
    private ISqlColumn column;
    private ESqlPrivilegesTyp sqlPrivilegesTyp;

    private SqlAlterPrivileges(String name, ISqlColumn column, ESqlPrivilegesTyp sqlPrivilegesTyp) {
        CheckValue.isNotBlank(name, "name");
        CheckValue.isNotNull(column, "column");
        CheckValue.isNotNull(sqlPrivilegesTyp, "sqlPrivilegesTyp");
        this.name = name;
        this.column = column;
        this.sqlPrivilegesTyp = sqlPrivilegesTyp;
    }

    @Override
    public String toSql() {
        return String.format("ALTER TABLE %s %s %s",
                name,
                column.toSql(),
                sqlPrivilegesTyp.name()
        );
    }

    @Override
    public String getAction() {
        return String.format("ALTER with privileges %s", sqlPrivilegesTyp.name());
    }

    public static SqlAlterPrivilegesBuilder alter() {
        return new SqlAlterPrivilegesBuilder();
    }

    public static class SqlAlterPrivilegesBuilder {

        private String name;
        private ISqlColumn column;
        private ESqlPrivilegesTyp sqlPrivilegesTyp;

        public SqlAlterPrivilegesBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SqlAlterPrivilegesBuilder withColumn(ISqlColumn column) {
            this.column = column;
            return this;
        }

        public SqlAlterPrivilegesBuilder withPrivileges(ESqlPrivilegesTyp sqlPrivilegesTyp) {
            this.sqlPrivilegesTyp = sqlPrivilegesTyp;
            return this;
        }

        public SqlAlterPrivileges build() {
            return new SqlAlterPrivileges(name, column, sqlPrivilegesTyp);
        }
    }
}
