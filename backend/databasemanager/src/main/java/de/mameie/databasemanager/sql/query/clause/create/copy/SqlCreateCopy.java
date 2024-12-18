package de.mameie.databasemanager.sql.query.clause.create.copy;

import de.mameie.databasemanager.sql.query.ISqlQuery;
import de.mameie.databasemanager.sql.query.condition.ISqlCondition;
import de.mameie.databasemanager.sql.query.field.ISqlFieldDefinition;
import de.mameie.databasemanager.util.check.CheckParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an SQL query for creating a copy of an existing table.
 */
public class SqlCreateCopy implements ISqlQuery {

    private final String existTableName;
    private final String copyTableName;
    private final boolean isFiltered;
    private List<String> iSqlFieldDefinitions = new ArrayList<>();
    private List<String> iSqlCondition = new ArrayList<>();

    /**
     * Constructs an instance of SqlCreateCopy.
     *
     * @param existTableName       the name of the existing table
     * @param copyTableName        the name of the new table to create
     * @param isFiltered           indicates whether the copy should be filtered
     * @param iSqlFieldDefinitions the field definitions to include in the copy
     * @param iSqlCondition        the conditions to apply to the copy
     */
    private SqlCreateCopy(String existTableName, String copyTableName, boolean isFiltered, List<String> iSqlFieldDefinitions, List<String> iSqlCondition) {
        CheckParam.isNotNull(existTableName, "existTableName");
        CheckParam.isNotNull(copyTableName, "copyTableName");
        CheckParam.isNotEmpty(iSqlFieldDefinitions, "iSqlFieldDefinitions");
        if (isFiltered) {
            CheckParam.isNotEmpty(iSqlCondition, "iSqlCondition");
        }
        this.existTableName = existTableName;
        this.copyTableName = copyTableName;
        this.isFiltered = isFiltered;
        this.iSqlFieldDefinitions = iSqlFieldDefinitions;
        this.iSqlCondition = iSqlCondition;
    }

    /**
     * Converts the SQL query to a string.
     *
     * @return the SQL query string
     */
    @Override
    public String toSql() {
        if (isFiltered) {
            return String.format("CREATE TABLE %s AS SELECT %s FROM %s WHERE %s"
                    , copyTableName
                    , String.join(", ", iSqlFieldDefinitions)
                    , existTableName
                    , String.join(" AND ", iSqlCondition));
        }
        return String.format("CREATE TABLE %s AS SELECT %s FROM %s"
                , copyTableName
                , String.join(", ", iSqlFieldDefinitions)
                , existTableName);
    }

    /**
     * Returns the SQL query as a string representation.
     *
     * @return the SQL query string
     */
    @Override
    public String toString() {
        return toSql();
    }

    /**
     * Gets the action represented by this query.
     *
     * @return the action string
     */
    @Override
    public String getAction() {
        return "CREATE TABLE";
    }

    /**
     * Creates a new builder for SqlCreateCopy.
     *
     * @return a new SqlCreateCopyTableBuilder
     */
    public static SqlCreateCopyTableBuilder create() {
        return new SqlCreateCopyTableBuilder();
    }

    /**
     * Builder class for constructing instances of SqlCreateCopy.
     */
    public static class SqlCreateCopyTableBuilder {
        private String existTableName;
        private String copyTableName;
        private boolean isFiltered;
        private final List<String> iSqlFieldDefinitions = new ArrayList<>();
        private final List<String> iSqlCondition = new ArrayList<>();

        /**
         * Sets the name of the existing table.
         *
         * @param existTableName the name of the existing table
         * @return the builder instance
         */
        public SqlCreateCopyTableBuilder withExistTableName(String existTableName) {
            this.existTableName = existTableName;
            return this;
        }

        /**
         * Sets the name of the new table to create.
         *
         * @param copyTableName the name of the new table
         * @return the builder instance
         */
        public SqlCreateCopyTableBuilder withCopyTableName(String copyTableName) {
            this.copyTableName = copyTableName;
            return this;
        }

        /**
         * Adds all columns to the copy.
         *
         * @return the builder instance
         */
        public SqlCreateCopyTableBuilder addAllColumns() {
            this.iSqlFieldDefinitions.clear();
            this.iSqlFieldDefinitions.add("*");
            return this;
        }

        /**
         * Adds a column to the copy.
         *
         * @param iSqlFieldDefinition the field definition to add
         * @return the builder instance
         */
        public SqlCreateCopyTableBuilder addColumn(ISqlFieldDefinition iSqlFieldDefinition) {
            this.iSqlFieldDefinitions.add(iSqlFieldDefinition.getFieldDefinition());
            return this;
        }

        /**
         * Adds multiple columns to the copy.
         *
         * @param iSqlFieldDefinitions the field definitions to add
         * @return the builder instance
         */
        public SqlCreateCopyTableBuilder addColumns(List<ISqlFieldDefinition> iSqlFieldDefinitions) {
            iSqlFieldDefinitions.forEach(iSqlFieldDefinition ->
                    this.iSqlFieldDefinitions.add(iSqlFieldDefinition.getFieldDefinition())
            );
            return this;
        }

        /**
         * Adds a condition to the copy.
         *
         * @param iSqlCondition the condition to add
         * @return the builder instance
         */
        public SqlCreateCopyTableBuilder addCondition(ISqlCondition iSqlCondition) {
            this.iSqlCondition.add(iSqlCondition.getCondition());
            return this;
        }

        /**
         * Indicates that the copy should be filtered.
         *
         * @return the builder instance
         */
        public SqlCreateCopyTableBuilder hasFilter() {
            isFiltered = true;
            return this;
        }

        /**
         * Builds the SqlCreateCopy instance.
         *
         * @return the built SqlCreateCopy instance
         */
        public SqlCreateCopy build() {
            return new SqlCreateCopy(existTableName, copyTableName, isFiltered, iSqlFieldDefinitions, iSqlCondition);
        }
    }
}
