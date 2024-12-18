package de.mameie.databasemanager.sql.query;

/**
 * Enumeration of SQL operators used in SQL queries.
 */
public enum SqlOperator {

    EQUALS("="),
    NOT_EQUALS("<>"),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL("<="),
    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE"),
    IN("IN"),
    NOT_IN("NOT IN"),
    BETWEEN("BETWEEN"),
    NOT_BETWEEN("NOT BETWEEN"),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL");

    private final String operator;

    /**
     * Constructor for SqlOperator enum.
     *
     * @param operator the SQL operator as a string
     */
    SqlOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Gets the SQL operator as a string.
     *
     * @return the SQL operator
     */
    public String getOperator() {
        return operator;
    }
}
