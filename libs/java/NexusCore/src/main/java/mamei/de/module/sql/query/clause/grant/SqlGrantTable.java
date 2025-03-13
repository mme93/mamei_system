package mamei.de.module.sql.query.clause.grant;
import mamei.de.module.sql.query.ISqlQuery;

/**
 * The SqlGrantTable class builds a SQL query to show grants for a specific user and host.
 * This class implements the ISqlQuery interface.
 */
public class SqlGrantTable implements ISqlQuery {

    private String user;

    private String host;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private SqlGrantTable() {
    }

    /**
     * Builds the SQL query to show grants for the specified user and host.
     *
     * @return the SQL query string.
     */
    @Override
    public String toSql() {
        return String.format("SHOW GRANTS FOR '%s'@'%s';", user, host);
    }

    /**
     * Gets the action type of this query, which is "GRANT".
     *
     * @return the action type as a string.
     */
    @Override
    public String getAction() {
        return "GRANT";
    }

    /**
     * Creates a new builder instance for SqlGrantTable.
     *
     * @return a new SqlGrantTable instance.
     */
    public static SqlGrantTable builder() {
        return new SqlGrantTable();
    }

    /**
     * Sets the user for the SQL grant query.
     *
     * @param user the username for whom the grants are to be shown.
     * @return the current SqlGrantTable instance for chaining.
     */
    public SqlGrantTable withUser(String user) {
        this.user = user;
        return this;
    }

    /**
     * Sets the host for the SQL grant query.
     *
     * @param host the host from which the user connects.
     * @return the current SqlGrantTable instance for chaining.
     */
    public SqlGrantTable withHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * Builds the final ISqlQuery instance.
     *
     * @return the constructed ISqlQuery instance.
     */
    public ISqlQuery build() {
        return this;
    }
}
