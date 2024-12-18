package de.mameie.databasemanager.sql.query;

/**
 * Interface representing an SQL query.
 * Implementing classes are expected to provide the SQL query as a string
 * and specify the action of the query (e.g., SELECT, INSERT, UPDATE, DELETE).
 */
public interface ISqlQuery {

    /**
     * Converts the query to its SQL string representation.
     *
     * @return the SQL query as a string
     */
    String toSql();

    /**
     * Gets the action of the query (e.g., SELECT, INSERT, UPDATE, DELETE).
     *
     * @return the action of the query
     */
    String getAction();
}
