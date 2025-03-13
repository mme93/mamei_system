package mamei.de.module.sql.query.privileges;

import mamei.de.core.exception.NexusCoreIsEmptyException;
import mamei.de.core.exception.NexusCoreNullPointerException;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SqlPrivilegesTest {

    private final String ALL_PRIVILEGES = ESqlPrivilegesTyp.ALL_PRIVILEGES.getPrivilege();
    private final String SELECT_PRIVILEGES = ESqlPrivilegesTyp.SELECT.getPrivilege();
    private final String INSERT_PRIVILEGES = ESqlPrivilegesTyp.INSERT.getPrivilege();


    @Test
    public void shouldThrowNullPointerEx() {
        assertThrows(NexusCoreNullPointerException.class, () -> SqlPrivileges.builder().grantTyp((ESqlPrivilegesTyp) null).build());
    }

    @Test
    public void shouldBuildThrowIsEmptyEx() {
        assertThrows(NexusCoreIsEmptyException.class, () -> SqlPrivileges.builder().build());
    }

    @Test
    public void shouldGrantTypesThrowIsEmptyEx() {
        assertThrows(NexusCoreIsEmptyException.class, () -> SqlPrivileges.builder().build());
    }

    @Test
    public void shouldGenerateAllPrivilegesSql() {
        String sql = SqlPrivileges.builder().grantAll().build().toSql();
        assertEquals(sql, ALL_PRIVILEGES);
    }

    @Test
    public void shouldGenerateSelectAndInsertPrivilegesSql() {
        String sql = SqlPrivileges.builder().grantTyp(asList(ESqlPrivilegesTyp.SELECT,ESqlPrivilegesTyp.INSERT)).build().toSql();
        assertEquals(sql, String.format("%s,%s",SELECT_PRIVILEGES,INSERT_PRIVILEGES));
    }

    @Test
    public void shouldGenerateSelectPrivilegesSql() {
        String sql = SqlPrivileges.builder().grantTyp(ESqlPrivilegesTyp.SELECT).build().toSql();
        assertEquals(sql, SELECT_PRIVILEGES);
    }

    @Test
    public void shouldGetPrivilegesAsAction() {
        String action = SqlPrivileges.builder().grantAll().build().getAction();
        assertEquals("PRIVILEGES",action);
    }
}
