package mamei.de.module.sql.executor.administration;

import mamei.de.module.sql.model.SystemUser;
import mamei.de.module.sql.query.privileges.SqlPrivileges;
import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link AdministrationSqlExecutor}.
 * This class tests the creation, deletion, and privilege management of system users in the database.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdministrationSqlExecutorTest {

    /**
     * JUnit rule for setting up a MariaDB database instance.
     * This provides the necessary database environment for testing.
     */
    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    /**
     * Test user used for database operations.
     */
    private SystemUser testUser = new SystemUser("testUser", "localhost", null);

    /**
     * Default password for the test user.
     */
    private String password = "123";

    /**
     * Tests the creation and deletion of a system user.
     * It verifies that:
     * <ul>
     *     <li>The number of users increases after creation</li>
     *     <li>The new user exists in the user list</li>
     *     <li>The user can be successfully deleted</li>
     * </ul>
     *
     * @throws SQLException if an error occurs while interacting with the database
     */
    @Test
    public void shouldCreateAndDeleteUser() throws SQLException {
        AdministrationSqlExecutor executor = new AdministrationSqlExecutor(MariaDBRule.CONNECTION_CREDENTIALS);
        List<SystemUser> users = executor.getAllSystemUser();
        executor.createSystemUser(testUser, password);
        List<SystemUser> compareUsers = executor.getAllSystemUser();
        assertEquals(users.size() + 1, compareUsers.size());
        List<String> nameList = compareUsers.stream().map(SystemUser::getName).toList();
        assertTrue(nameList.contains("testUser"));
        int result = executor.deleteSystemUser(testUser);
        assertEquals(result, 0);
    }

    /**
     * Tests the creation of a system user with all privileges.
     * It verifies that:
     * <ul>
     *     <li>The user is created with default privileges</li>
     *     <li>All privileges can be granted to the user</li>
     *     <li>The user has exactly 38 privileges after granting</li>
     * </ul>
     *
     * @throws SQLException if an error occurs while interacting with the database
     */
    @Test
    public void shouldCreateUserWithAllPrivileges() throws SQLException {
        String database = "*";
        AdministrationSqlExecutor executor = new AdministrationSqlExecutor(MariaDBRule.CONNECTION_CREDENTIALS);
        executor.createSystemUser(testUser, password);
        List<String> userPrivileges = executor.getGrantFromSystemUser(testUser)
                .get(String.format("'%s'@'%s'", testUser.getName(), testUser.getHost()));
        assertEquals(userPrivileges.size(), 1);
        assertEquals(userPrivileges.get(0), "USAGE");
        executor.grantSystemUserToDatabase(SqlPrivileges.builder().grantAll().build(), testUser, database);
        userPrivileges = executor.getGrantFromSystemUser(testUser)
                .get(String.format("'%s'@'%s'", testUser.getName(), testUser.getHost()));
        assertEquals(userPrivileges.size(), 38);
    }
}
