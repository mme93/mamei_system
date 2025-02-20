package mamei.de.module.sql.executor.database.table;

import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;

public class TableSqlExecutorTest {

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    private String databaseName="nexus_core";


}
