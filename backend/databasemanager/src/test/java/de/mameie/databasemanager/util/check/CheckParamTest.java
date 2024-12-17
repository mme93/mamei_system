package de.mameie.databasemanager.util.check;

import de.mameie.databasemanager.util.check.exception.ParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for the CheckParam utility.
 */
@SpringBootTest
public class CheckParamTest {

    /**
     * Tests the isNotNull method to ensure it returns the object when it is not null.
     */
    @Test
    void isNotNull() {
        String test = "Test";
        Assertions.assertEquals(test, CheckParam.isNotNull(test, "test"));
    }

    /**
     * Tests the isNotNull method to ensure it throws a ParamException when the object is null.
     */
    @Test
    void throwParamException() {
        Assertions.assertThrows(
                ParamException.class,
                () -> CheckParam.isNotNull(null, "test"),
                "Param with the name test is null."
        );
    }
}
