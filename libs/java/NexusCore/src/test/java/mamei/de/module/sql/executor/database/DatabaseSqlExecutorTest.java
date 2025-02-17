package mamei.de.module.sql.executor.database;

import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class DatabaseSqlExecutorTest {

    private String databaseName="nexus_core";

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    @Test
    public void shouldCreateAndDeleteDatabase() throws SQLException {
        DatabaseSqlExecutor executor = new DatabaseSqlExecutor(MariaDBRule.CONNECTION_CONTEXT);
        List<String> databaseList= executor.show();
        Assert.assertFalse(databaseList.contains(databaseName));
        executor.create(databaseName);
        databaseList= executor.show();
        Assert.assertTrue(databaseList.contains(databaseName));
        executor.drop(databaseName);
        databaseList= executor.show();
        Assert.assertFalse(databaseList.contains(databaseName));
    }

}
