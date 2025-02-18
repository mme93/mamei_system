package mamei.de.core.exception;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NexusCoreNullPointerExceptionTest.class,
        NexusCoreMethodNotImplementedExceptionTest.class,
        NexusCoreIsEmptyExceptionTest.class,
        NexusCoreIllegalArgumentExceptionTest.class
})
public class NexusCoreExceptionSuite {
}
