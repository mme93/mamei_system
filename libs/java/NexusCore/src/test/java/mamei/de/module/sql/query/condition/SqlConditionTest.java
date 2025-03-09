package mamei.de.module.sql.query.condition;

import org.junit.Test;

import static java.util.Arrays.asList;
import static mamei.de.module.sql.query.operator.SqlComparisonOperator.GREATER;
import static org.junit.Assert.assertEquals;

public class SqlConditionTest {

    @Test
    public void shouldConditionWithLike() {
        String result = SqlCondition.condition().like("name", "%und").getCondition();
        assertEquals("name LIKE '%und'", result);
    }

    @Test
    public void shouldConditionWithIn() {
        String result = SqlCondition.condition().in("age", asList("5", "6")).getCondition();
        assertEquals(result, String.format("age IN ('5','6')"));
    }

    @Test
    public void shouldConditionWithBetween() {
        String result = SqlCondition.condition().between("age", 18, 21).getCondition();
        assertEquals("age BETWEEN 18 AND 21", result);
    }

    @Test
    public void shouldConditionWithIsNotNull() {
        String result = SqlCondition.condition().isNotNull("age").getCondition();
        assertEquals("age IS NOT NULL", result);
    }

    @Test
    public void shouldConditionWithIsNull() {
        String result = SqlCondition.condition().isNull("age").getCondition();
        assertEquals("age IS NULL", result);
    }

    @Test
    public void shouldConditionWithRegEx() {
        String result = SqlCondition.condition().regEx("name", "^[A-M]").getCondition();
        assertEquals("name REGEXP '^[A-M]'", result);
    }

    @Test
    public void shouldConditionWithComparison() {
        String result = SqlCondition.condition().comparison("age", 18, GREATER).getCondition();
        assertEquals("age > 18", result);
    }


}
