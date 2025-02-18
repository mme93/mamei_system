package mamei.de.module.sql.connection;

import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionManagerTest {

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    @Test
    public void shouldCloseConnection() throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.getInstance(MariaDBRule.CONNECTION_CREDENTIALS);
        Connection con = connectionManager.getConnection();
        assertFalse(con.isClosed());
        connectionManager.closeConnection();
        assertTrue(con.isClosed());
    }


}
