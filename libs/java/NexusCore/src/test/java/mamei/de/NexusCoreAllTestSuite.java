package mamei.de;

import mamei.de.core.utils.check.NexusCoreTestSuite;
import mamei.de.module.NexusCoreModuleSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NexusCoreTestSuite.class,
        NexusCoreModuleSuite.class
})
public class NexusCoreAllTestSuite {
}
