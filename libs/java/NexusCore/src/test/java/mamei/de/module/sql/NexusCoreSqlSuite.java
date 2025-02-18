package mamei.de.module.sql;

import mamei.de.module.sql.connection.NexusCoreConnectionSuite;
import mamei.de.module.sql.executor.NexusCoreExecutorSuite;
import mamei.de.module.sql.query.NexusCoreQuerySuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NexusCoreConnectionSuite.class,
        NexusCoreExecutorSuite.class,
        NexusCoreQuerySuite.class
})
public class NexusCoreSqlSuite {
}
