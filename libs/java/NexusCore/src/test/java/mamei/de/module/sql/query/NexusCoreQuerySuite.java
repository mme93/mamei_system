package mamei.de.module.sql.query;

import mamei.de.module.sql.query.clause.NexusCoreQueryClauseSuite;
import mamei.de.module.sql.query.condition.SqlConditionOperatorTest;
import mamei.de.module.sql.query.condition.SqlConditionTest;
import mamei.de.module.sql.query.privileges.SqlPrivilegesTest;
import mamei.de.module.sql.query.user.SqlUserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SqlPrivilegesTest.class,
        SqlUserTest.class,
        SqlConditionTest.class,
        SqlConditionOperatorTest.class,
        NexusCoreQueryClauseSuite.class
})
public class NexusCoreQuerySuite {
}
