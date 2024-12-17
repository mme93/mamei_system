package de.mameie.databasemanager.sql.executor.database;

import de.mameie.databasemanager.sql.server.connection.DBServerSettings;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatabaseSqlExecutorTest {

    static DatabaseSqlExecutor executor;

    @BeforeAll
    static void setUp() {
        executor = new DatabaseSqlExecutor(DBServerSettings.TEST);
    }

}
