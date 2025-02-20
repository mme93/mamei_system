package mamei.de.module.sql.executor;

import mamei.de.module.sql.executor.administration.AdministrationSqlExecutorTest;
import mamei.de.module.sql.executor.database.DatabaseSqlExecutorTest;
import mamei.de.module.sql.executor.database.table.TableSqlExecutorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdministrationSqlExecutorTest.class,
        DatabaseSqlExecutorTest.class,
        TableSqlExecutorTest.class
})
public class NexusCoreExecutorSuite {
}
