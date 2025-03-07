package mamei.de.module.sql.query.clause;

import mamei.de.module.sql.query.clause.alias.SqlColumnAliasTest;
import mamei.de.module.sql.query.clause.alter.SqlAlterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SqlColumnAliasTest.class,
        SqlAlterTest.class
})
public class NexusCoreQueryClauseSuite {
}
