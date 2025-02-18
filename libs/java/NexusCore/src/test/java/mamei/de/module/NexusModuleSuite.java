package mamei.de.module;

import mamei.de.module.sql.NexusCoreSqlSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        NexusCoreSqlSuite.class
})
public class NexusModuleSuite {
}
