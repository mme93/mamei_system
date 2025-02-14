package mamei.de.module.sql.connection;

import org.junit.Test;

public class SqlConnectionFactoryTest {

    private SqlConnectionContext context = new SqlConnectionContext("jdbc:h2:file:./data/mydb", "sa", "test", "test");

    @Test
    public void shouldCreateNewConnection() {

    }
}
