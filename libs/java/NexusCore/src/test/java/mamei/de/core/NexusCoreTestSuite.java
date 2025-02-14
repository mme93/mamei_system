package mamei.de.core;

import mamei.de.core.exception.*;
import mamei.de.core.utils.check.CheckParamTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CheckParamTest.class,
        //Exceptions
        NexusCoreNullPointerExceptionTest.class,
        NexusCoreMethodNotImplementedExceptionTest.class,
        NexusCoreIsEmptyExceptionTest.class,
        NexusCoreIllegalArgumentExceptionTest.class
})
public class NexusCoreTestSuite {
}
