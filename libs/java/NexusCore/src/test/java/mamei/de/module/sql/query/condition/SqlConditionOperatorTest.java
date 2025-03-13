package mamei.de.module.sql.query.condition;

import mamei.de.core.exception.NexusCoreNullPointerException;
import mamei.de.module.sql.query.operator.ESqlLogicOperator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SqlConditionOperatorTest {


    @Test
    public void shouldTwoConditionWithAndOperator() {
        String condition = SqlConditionOperator.operator().withConditions(
                SqlCondition.condition().isNotNull("id"),
                SqlCondition.condition().between("age", 18, 21)
        ).as(ESqlLogicOperator.AND).getCondition();
        assertEquals("id IS NOT NULL AND age BETWEEN 18 AND 21", condition);
    }

    @Test
    public void shouldThreeConditionWithOperators() {
        String condition = SqlConditionOperator.operator().withConditions(
                SqlConditionOperator.operator().withConditions(
                        SqlCondition.condition().isNotNull("id"),
                        SqlCondition.condition().between("age", 18, 21)
                ).as(ESqlLogicOperator.AND),
                SqlCondition.condition().like("name", "a%")
        ).as(ESqlLogicOperator.OR).getCondition();
        assertEquals("id IS NOT NULL AND age BETWEEN 18 AND 21 OR name LIKE 'a%'", condition);
    }

    @Test
    public void shouldThrowNullPointerException() {
        Exception exception = assertThrows(NexusCoreNullPointerException.class, () ->
                SqlConditionOperator.operator().withConditions(null, null).as(ESqlLogicOperator.AND)
        );
        assertEquals("Param with the name firstCondition is null.", exception.getMessage());

        exception = assertThrows(NexusCoreNullPointerException.class, () ->
                SqlConditionOperator
                        .operator()
                        .withConditions(SqlCondition.condition().isNotNull("id"), null)
                        .as(ESqlLogicOperator.AND)
        );
        assertEquals("Param with the name secondCondition is null.", exception.getMessage());
    }
}
