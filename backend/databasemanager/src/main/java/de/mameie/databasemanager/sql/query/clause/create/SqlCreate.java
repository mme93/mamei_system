package de.mameie.databasemanager.sql.query.clause.create;

import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.query.field.ISqlFieldDefinition;

import java.util.ArrayList;
import java.util.List;

public class SqlCreate implements ISqlQuery {

    private String tableName;
    private List<String> iSqlFieldDefinitions = new ArrayList<>();

    private SqlCreate() {
    }

    public static SqlCreateTableBuilder create() {
        return new SqlCreateTableBuilder();
    }

    public String getTableName() {
        return tableName;
    }

    public List<String> getColumns() {
        return iSqlFieldDefinitions;
    }

    @Override
    public String toSql() {
        return String.format("CREATE TABLE %s (%s);", tableName, String.join(", ", iSqlFieldDefinitions));
    }

    @Override
    public String getAction() {
        return "CREATE";
    }

    @Override
    public String toString() {
        return String.format("CREATE TABLE %s (%s);", tableName, String.join(", ", iSqlFieldDefinitions));
    }

    public static class SqlCreateTableBuilder {
        private SqlCreate sqlCreate = new SqlCreate();

        public SqlCreateTableBuilder tableName(String tableName) {
            sqlCreate.tableName = tableName;
            return this;
        }

        public SqlCreateTableBuilder addColumn(ISqlFieldDefinition iSqlFieldDefinition) {
            sqlCreate.iSqlFieldDefinitions.add(iSqlFieldDefinition.getFieldDefinition());
            return this;
        }

        public SqlCreateTableBuilder addColumns(List<ISqlFieldDefinition> iSqlFieldDefinitions) {
            iSqlFieldDefinitions.stream().forEach(iSqlFieldDefinition ->
                    sqlCreate.iSqlFieldDefinitions.add(iSqlFieldDefinition.getFieldDefinition())
                    );
            return this;
        }

        public SqlCreate build() {
            return sqlCreate;
        }
    }
}
