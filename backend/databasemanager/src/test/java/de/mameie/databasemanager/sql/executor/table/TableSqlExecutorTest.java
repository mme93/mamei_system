package de.mameie.databasemanager.sql.executor.table;

import de.mameie.databasemanager.sql.server.connection.H2ConnectionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
public class TableSqlExecutorTest {

    private static TableSqlExecutor executorWithTableName;
    private static TableSqlExecutor executor;

    private static final String TEST = "TEST";
    private static final String tableName = "test_table";
    private static final String serverName = "serverName";
    private static final String databaseName = "databaseName";
    private static Connection con;

    @BeforeAll
    static void setUp() throws SQLException {
        executorWithTableName = new TableSqlExecutor(serverName,databaseName,tableName);


        executor = new TableSqlExecutor(serverName,databaseName);

        executorWithTableName.changeStatus(TEST);
        executor.changeStatus(TEST);
        con = H2ConnectionFactory.getInstance().getConnection();
        if(executor.exist(tableName)){
            executor.drop(tableName);
        }
    }

    @Test
    void testThatTableExistAndThenDrop() throws SQLException {
        Assertions.assertFalse(executor.exist(tableName));
        createDummyTable();
        Assertions.assertTrue(executor.exist(tableName));
        executor.drop(tableName);
        Assertions.assertFalse(executor.exist(tableName));
    }


    @Test
    void testThatTableIsCreated() throws SQLException {
        PreparedStatement statement = con.prepareStatement(String.format("CREATE TABLE %s", tableName));
        statement.execute();
        statement.close();
        //executor
        //executor.createTable();
    }

    private void createDummyTable() throws SQLException {
        PreparedStatement statement = con.prepareStatement(String.format("CREATE TABLE %s", tableName));
        statement.execute();
        statement.close();
    }
}
