package de.mameie.databasemanager.util.check;

import de.mameie.databasemanager.util.check.exception.ParamException;
import de.mameie.databasemanager.util.check.exception.ValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for the CheckValue utility.
 */
@SpringBootTest
public class CheckValueTest {

    /**
     * Tests the isNotNull method to ensure it returns the object when it is not null.
     */
    @Test
    void isNotNull() {
        String test = "Test";
        Assertions.assertEquals(test, CheckValue.isNotNull(test, "test"));
    }

    /**
     * Tests the isNotNull method to ensure it throws a ValueException when the object is null.
     */
    @Test
    void throwParamException() {
        Assertions.assertThrows(
                ValueException.class,
                () -> CheckValue.isNotNull(null, "test"),
                "Value with the name test is null."
        );
    }
}
