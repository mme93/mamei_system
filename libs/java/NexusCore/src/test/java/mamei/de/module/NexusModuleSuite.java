package mamei.de.module;

import mamei.de.module.sql.connection.ConnectionCredentialsTest;
import mamei.de.module.sql.connection.SqlConnectionFactoryTest;
import mamei.de.module.sql.query.NexusCoreQuerySuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConnectionCredentialsTest.class,
        SqlConnectionFactoryTest.class,
        NexusCoreQuerySuite.class
})
public class NexusModuleSuite {
}
