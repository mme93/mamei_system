package mamei.de;

import mamei.de.core.NexusCoreTestSuite;
import mamei.de.module.NexusModuleSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NexusCoreTestSuite.class,
        NexusModuleSuite.class,
        NexusCoreSuiteIntegrityTest.class
})
public class NexusCoreAllTestSuite {
}
