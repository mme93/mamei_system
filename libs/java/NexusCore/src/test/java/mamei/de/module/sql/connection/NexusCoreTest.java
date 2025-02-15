package mamei.de.module.sql.connection;

import mamei.de.module.sql.executor.administration.AdministrationSqlExecutor;
import mamei.de.module.sql.model.SystemUser;
import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class NexusCoreTest {

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();


    @Test
    public void test() throws SQLException {
        AdministrationSqlExecutor executor = new AdministrationSqlExecutor(MariaDBRule.CONNECTION_CONTEXT);
        List<SystemUser> users = executor.getAllSystemUser();
        for(SystemUser user:users){
            System.err.println(user.getName());
        }
    }

}
