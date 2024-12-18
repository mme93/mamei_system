package de.mameie.databasemanager.sql.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an SQL clause for database operations such as CREATE, DROP, and SHOW.
 */
@Getter
@Setter
public class SqlDatabaseClause implements ISqlQuery {

    private String action;
    private String destination;
    private String destinationName;

    /**
     * Private constructor to initialize the SQL clause with an action.
     *
     * @param action the action to be performed (e.g., CREATE, DROP, SHOW)
     */
    private SqlDatabaseClause(String action) {
        this.action = action;
    }

    /**
     * Creates a builder for a DROP SQL clause.
     *
     * @return a new SqlClauseBuilder for the DROP action
     */
    public static SqlClauseBuilder drop() {
        return new SqlClauseBuilder("DROP");
    }

    /**
     * Creates a builder for a CREATE SQL clause.
     *
     * @return a new SqlClauseBuilder for the CREATE action
     */
    public static SqlClauseBuilder create() {
        return new SqlClauseBuilder("CREATE");
    }

    /**
     * Creates a builder for a SHOW SQL clause.
     *
     * @return a new SqlClauseBuilder for the SHOW action
     */
    public static SqlClauseBuilder show() {
        return new SqlClauseBuilder("SHOW");
    }

    /**
     * Converts the SQL clause to its string representation.
     *
     * @return the SQL clause as a string
     */
    @Override
    public String toSql() {
        if (destinationName != null) {
            return String.format("%s %s %s", action, destination, destinationName);
        }
        return String.format("%s %s", action, destination);
    }

    /**
     * Gets the action of the SQL clause.
     *
     * @return the action of the SQL clause
     */
    @Override
    public String getAction() {
        return action;
    }

    /**
     * Builder class for constructing SQL database clauses.
     */
    public static class SqlClauseBuilder {

        private SqlDatabaseClause sqlDatabaseClause;

        /**
         * Initializes the builder with a specified action.
         *
         * @param action the action to be performed (e.g., CREATE, DROP, SHOW)
         */
        public SqlClauseBuilder(String action) {
            sqlDatabaseClause = new SqlDatabaseClause(action);
        }

        /**
         * Sets the destination of the SQL clause to DATABASE.
         *
         * @return the current SqlClauseBuilder instance
         */
        public SqlClauseBuilder database() {
            sqlDatabaseClause.setDestination("DATABASE");
            return this;
        }

        /**
         * Sets the destination of the SQL clause to TABLE.
         *
         * @return the current SqlClauseBuilder instance
         */
        public SqlClauseBuilder table() {
            sqlDatabaseClause.setDestination("TABLE");
            return this;
        }

        /**
         * Sets the destination of the SQL clause to DATABASES.
         *
         * @return the current SqlClauseBuilder instance
         */
        public SqlClauseBuilder databases() {
            sqlDatabaseClause.setDestination("DATABASES");
            return this;
        }

        /**
         * Sets the destination of the SQL clause to TABLES.
         *
         * @return the current SqlClauseBuilder instance
         */
        public SqlClauseBuilder tables() {
            sqlDatabaseClause.setDestination("TABLES");
            return this;
        }

        /**
         * Sets the name for the SQL clause's destination.
         *
         * @param name the name of the destination
         * @return the current SqlClauseBuilder instance
         */
        public SqlClauseBuilder name(String name) {
            sqlDatabaseClause.setDestinationName(name);
            return this;
        }

        /**
         * Builds the SQL clause.
         *
         * @return the constructed ISqlQuery instance
         */
        public ISqlQuery build() {
            return sqlDatabaseClause;
        }
    }
}
