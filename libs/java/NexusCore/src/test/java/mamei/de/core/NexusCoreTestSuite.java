package mamei.de.core;

import mamei.de.core.exception.*;
import mamei.de.core.utils.CheckValueTest;
import mamei.de.core.utils.CompareValueTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CheckValueTest.class,
        CompareValueTest.class,
        NexusCoreExceptionSuite.class
})
public class NexusCoreTestSuite {
}
