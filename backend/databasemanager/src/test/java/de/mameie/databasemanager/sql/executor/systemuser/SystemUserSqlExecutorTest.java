package de.mameie.databasemanager.sql.executor.systemuser;

import de.mameie.databasemanager.sql.server.connection.H2ConnectionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class SystemUserSqlExecutorTest {

    private static Connection con;
    private static final String TEST = "TEST";
    private static final String serverName = "serverName";
    private static final String databaseName = "databaseName";

    private static SystemUserSqlExecutor systemUserSqlExecutor;

    @BeforeAll
    static void setUp() throws SQLException {
        systemUserSqlExecutor = null;

        systemUserSqlExecutor.changeStatus(TEST);
        con = H2ConnectionFactory.getInstance().getConnection();
    }


}
