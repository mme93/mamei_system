package com.systemmanager.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the ConstantHelper class.
 */
@SpringBootTest(classes = {ConstantHelper.class})
public class ConstantHelperTest {

    @Autowired
    @InjectMocks
    private ConstantHelper constantHelper;

    /**
     * Initializes Mockito annotations before each test method.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Tests the arrayListToString method when provided with one constant.
     */
    @Test
    public void testArrayListToStringWithOneConstant() {
        Assertions.assertEquals(constantHelper.arrayListToString(Arrays.asList("Test")), "Test");
    }

    /**
     * Tests the arrayListToString method when provided with multiple constants.
     */
    @Test
    public void testArrayListToStringWithMultiConstant() {
        Assertions.assertEquals(constantHelper.arrayListToString(Arrays.asList("Test1", "Test2", "Test3", "Test4")), "Test1, Test2, Test3, Test4");
    }

    /**
     * Tests if arrayListToString method throws RuntimeException when provided with an empty list.
     */
    @Test
    public void testArrayListToStringThrowExceptionWithEmptyList() {
        RuntimeException thrown = Assertions.assertThrows(
                RuntimeException.class,
                () -> constantHelper.arrayListToString(Arrays.asList()),
                "No Constant in list"
        );
        assertTrue(thrown.getMessage().contains("No Constant in list"));
    }
}
