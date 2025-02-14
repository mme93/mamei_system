package mamei.de.module.sql.executor.administration;

import mamei.de.module.sql.connection.SqlConnectionContext;
import mamei.de.module.sql.query.clause.create.SqlCreateUser;
import mamei.de.module.sql.query.clause.drop.SqlDropUser;
import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdministrationSqlExecutorTest {

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    private SystemUser testUser = new SystemUser("testUser", "localhost", null);
    private String password = "123";

    @Test
    public void createUser() throws SQLException {
        AdministrationSqlExecutor executor = new AdministrationSqlExecutor(MariaDBRule.CONNECTION_CONTEXT);
        List<SystemUser> users = executor.getAllSystemUser();
        executor.createSystemUser(testUser, password);
        List<SystemUser> compareUsers = executor.getAllSystemUser();
        assertEquals(users.size()+1,compareUsers.size());
        List<String>nameList=compareUsers.stream().map(SystemUser::getName).toList();
        assertTrue(nameList.contains("testUser"));
        int result=executor.deleteSystemUser(testUser);
        assertEquals(result,0);
    }


}
