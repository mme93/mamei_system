package de.mameie.databasemanager.sql.executor;

import de.mameie.databasemanager.util.check.exception.ParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for {@link AbstractSqlExecutor}.
 */
@SpringBootTest
public class AbstractSqlExecutorTest {

    /**
     * Tests if a {@link ParamException} is thrown when the server name is null in the constructor.
     */
    @Test
    void testIfExceptionThrowByNullValueInServerConstructor(){
        Assertions.assertThrows(
                ParamException.class,
                ()-> new ConcreteAbstractSqlExecutor(null),
                "Param with the name serverName is null."
        );
    }

    /**
     * Tests if a {@link ParamException} is thrown when either the server name or database name is null in the constructor.
     */
    @Test
    void testIfExceptionThrowByNullValueInDatabaseConstructor(){
        Assertions.assertThrows(
                ParamException.class,
                ()-> new ConcreteAbstractSqlExecutor("serverName",null),
                "Param with the name serverName is null."
        );
        Assertions.assertThrows(
                ParamException.class,
                ()-> new ConcreteAbstractSqlExecutor(null,"databaseName"),
                "Param with the name databaseName is null."
        );
    }

    /**
     * Tests if a {@link ParamException} is thrown when any of the server name, database name, or table name is null in the constructor.
     */
    @Test
    void testIfExceptionThrowByNullValueInTableConstructor(){
        Assertions.assertThrows(
                ParamException.class,
                ()-> new ConcreteAbstractSqlExecutor(null,"databaseName","tableName"),
                "Param with the name serverName is null."
        );
        Assertions.assertThrows(
                ParamException.class,
                ()-> new ConcreteAbstractSqlExecutor("serverName",null,"tableName"),
                "Param with the name databaseName is null."
        );
        Assertions.assertThrows(
                ParamException.class,
                ()-> new ConcreteAbstractSqlExecutor("serverName","databaseName",null),
                "Param with the name tableName is null."
        );
    }

    /**
     * A concrete implementation of {@link AbstractSqlExecutor} for testing purposes.
     */
    private class ConcreteAbstractSqlExecutor extends AbstractSqlExecutor {

        /**
         * Constructor that accepts a server name.
         *
         * @param serverName the name of the server
         * @throws ParamException if the server name is null
         */
        public ConcreteAbstractSqlExecutor(String serverName) {
            super(serverName);
        }

        /**
         * Constructor that accepts a server name and a database name.
         *
         * @param serverName the name of the server
         * @param databaseName the name of the database
         * @throws ParamException if either the server name or database name is null
         */
        public ConcreteAbstractSqlExecutor(String serverName, String databaseName) {
            super(serverName, databaseName);
        }

        /**
         * Constructor that accepts a server name, database name, and table name.
         *
         * @param serverName the name of the server
         * @param databaseName the name of the database
         * @param tableName the name of the table
         * @throws ParamException if any of the server name, database name, or table name is null
         */
        public ConcreteAbstractSqlExecutor(String serverName, String databaseName, String tableName) {
            super(serverName, databaseName, tableName);
        }
    }
}
