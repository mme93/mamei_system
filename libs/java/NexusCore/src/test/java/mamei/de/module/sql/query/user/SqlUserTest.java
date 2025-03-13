package mamei.de.module.sql.query.user;

import mamei.de.core.exception.NexusCoreIsEmptyException;
import mamei.de.core.exception.NexusCoreNullPointerException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SqlUserTest {

    private final String HOST = "host";
    private final String USER = "user";
    private final String PASSWORD = "password";
    private final String VALID_SQL = String.format("CREATE USER '%s'@'%s' IDENTIFIED BY '%s'",
            USER, HOST, PASSWORD);

    @Test
    public void shouldThrowNullPointerEx() {
        assertThrows(NexusCoreNullPointerException.class, () -> SqlUser.builder().withUser(null).withHost(HOST).withPassword(PASSWORD).build());
        assertThrows(NexusCoreNullPointerException.class, () -> SqlUser.builder().withUser(USER).withHost(null).withPassword(PASSWORD).build());
        assertThrows(NexusCoreNullPointerException.class, () -> SqlUser.builder().withUser(USER).withHost(HOST).withPassword(null).build());
    }

    @Test
    public void shouldThrowIsEmptyEx() {
        assertThrows(NexusCoreIsEmptyException.class, () -> SqlUser.builder().withUser("").withHost(HOST).withPassword(PASSWORD).build());
        assertThrows(NexusCoreIsEmptyException.class, () -> SqlUser.builder().withUser(USER).withHost("").withPassword(PASSWORD).build());
        assertThrows(NexusCoreIsEmptyException.class, () -> SqlUser.builder().withUser(USER).withHost(HOST).withPassword("").build());
    }

    @Test
    public void shouldGenerateValidSql() {
        String sql = SqlUser.builder().withUser(USER).withHost(HOST).withPassword(PASSWORD).build().toSql();
        assertEquals(sql, VALID_SQL);
    }

    @Test
    public void shouldGetCreateAsAction() {
        String action = SqlUser.builder().withUser(USER).withHost(HOST).withPassword(PASSWORD).build().getAction();
        assertEquals("CREATE", action);
    }

}
