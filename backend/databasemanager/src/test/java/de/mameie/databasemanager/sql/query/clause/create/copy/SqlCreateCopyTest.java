package de.mameie.databasemanager.sql.query.clause.create.copy;

import de.mameie.databasemanager.sql.query.constraints.SqlConstraint;
import de.mameie.databasemanager.sql.query.datatypes.SqlDatatype;
import de.mameie.databasemanager.sql.query.field.SqlFieldDefinition;
import de.mameie.databasemanager.sql.query.field.SqlFieldDefinitionWithConstrains;
import de.mameie.databasemanager.util.check.exception.ParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SqlCreateCopyTest {

    @Test
    public void testThrowParamExceptionIfCopyTableNameIsNull() {
        assertThrows(
                ParamException.class,
                SqlCreateCopy.create().withExistTableName("existTable").addAllColumns()::build,
                "Param with the name copyTableName is null."
        );
    }

    @Test
    public void testThrowParamExceptionIfExistTableNameIsNull() {
        assertThrows(
                ParamException.class,
                SqlCreateCopy.create().withCopyTableName("newTable").addAllColumns()::build,
                "Param with the name existTableName is null."
        );
    }

    @Test
    public void testThrowParamListExceptionIfColumnsIsEmpty() {
        assertThrows(
                ParamException.class,
                SqlCreateCopy.create().withCopyTableName("newTable").withExistTableName("existTable")::build,
                "Param list with the name iSqlFieldDefinitions is null."
        );
    }

    @Test
    public void testSqlCreateCopyWithOutConditionsAndSelectAsWildcard() {
        SqlCreateCopy sqlCreateCopy = SqlCreateCopy.create().withCopyTableName("newTable").withExistTableName("existTable").addAllColumns().build();
        String expectationQuery = "CREATE TABLE newTable AS SELECT * FROM existTable";
        Assertions.assertEquals(expectationQuery, sqlCreateCopy.toSql());
    }

    @Test
    public void testSqlCreateCopyWithOutConditions() {
        SqlCreateCopy sqlCreateCopy = SqlCreateCopy.create().withCopyTableName("newTable").withExistTableName("existTable").addColumns(
                asList(
                        SqlFieldDefinitionWithConstrains.set("name", SqlDatatype.VARCHAR(), SqlConstraint.set("255")),
                        SqlFieldDefinition.set("age", SqlDatatype.INT())
                )
        ).build();
        String expectationQuery = "CREATE TABLE newTable AS SELECT name VARCHAR(255), age INT FROM existTable";
        Assertions.assertEquals(expectationQuery, sqlCreateCopy.toSql());
    }

}
