package mamei.de.module.sql.connection;

import mamei.de.module.sql.executor.administration.AdministrationSqlExecutor;
import org.junit.Test;

import java.sql.SQLException;


public class NexusCoreTest {

    @Test
    public void test() throws SQLException {
        AdministrationSqlExecutor executor = new AdministrationSqlExecutor(
                new SqlConnectionContext("jdbc:mysql://212.227.165.166:3306/", "remote_user", "!xyz123456", "XXX"));
        executor.getAllSystemUser().forEach(systemUser -> System.err.println(
                String.format("Host: %s, Name:%s AND Grant:%s",systemUser.getHost(),systemUser.getName(),systemUser.getGrant())
        ));
    }
}
