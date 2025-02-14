package mamei.de.module.sql.connection;

import mamei.de.module.sql.rule.MariaDBRule;
import org.junit.ClassRule;
import org.junit.Test;

public class SqlConnectionFactoryTest {

    @ClassRule
    public static MariaDBRule mariaDBRule = new MariaDBRule();

    @Test
    public void shouldCreateNewConnection() {

    }
}
