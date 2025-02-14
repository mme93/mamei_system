package mamei.de.module;

import mamei.de.module.sql.connection.H2ConnectionFactoryTest;
import mamei.de.module.sql.connection.SqlConnectionContextTest;
import mamei.de.module.sql.connection.SqlConnectionFactoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        H2ConnectionFactoryTest.class,
        SqlConnectionContextTest.class,
        SqlConnectionFactoryTest.class
})
public class NexusCoreModuleSuite {
}
