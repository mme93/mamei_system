package mamei.de.module.sql.query;

import mamei.de.module.sql.query.privileges.SqlPrivilegesTest;
import mamei.de.module.sql.query.user.SqlUserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SqlPrivilegesTest.class,
        SqlUserTest.class
})
public class NexusCoreQuerySuite {
}
