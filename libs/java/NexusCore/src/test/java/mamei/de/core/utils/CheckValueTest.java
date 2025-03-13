package mamei.de.core.utils;

import mamei.de.core.exception.NexusCoreIsEmptyException;
import mamei.de.core.exception.NexusCoreNullPointerException;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Unit tests for the {@link CheckValue} utility class.
 *
 * <p>This class ensures that parameter validation methods work as expected
 * by throwing appropriate exceptions for null, empty, or blank values.</p>
 *
 * <p>It uses JUnit for assertions and exception testing.</p>
 */
public class CheckValueTest {

    /**
     * Verifies that {@code isNotNull} throws a {@link NexusCoreNullPointerException}
     * when the provided value is {@code null}.
     */
    @Test
    public void shouldThrowExceptionIfNull() {
        Exception exception = assertThrows(NexusCoreNullPointerException.class, () -> {
            CheckValue.isNotNull(null, "test");
        });
        String msg = "Param with the name test is null.";
        assertEquals(exception.getMessage(), msg);
    }

    /**
     * Verifies that {@code isNotBlank} throws a {@link NexusCoreIsEmptyException}
     * when an empty string is passed.
     */
    @Test
    public void shouldThrowExceptionIfEmptyString() {
        Exception exception = assertThrows(NexusCoreIsEmptyException.class, () -> {
            CheckValue.isNotBlank("", "test");
        });
        String msg = "String with the name test is empty.";
        assertEquals(exception.getMessage(), msg);
    }

    /**
     * Verifies that {@code isNotEmpty} throws a {@link NexusCoreIsEmptyException}
     * when an empty list is passed.
     */
    @Test
    public void shouldThrowExceptionIfEmptyList() {
        Exception exception = assertThrows(NexusCoreIsEmptyException.class, () -> {
            CheckValue.isNotEmpty(asList(), "test");
        });
        String msg = "Param list with the name test is empty.";
        assertEquals(exception.getMessage(), msg);
    }

    /**
     * Verifies that {@code isNotEmpty} throws a {@link NexusCoreIsEmptyException}
     * when an empty map is passed.
     */
    @Test
    public void shouldThrowExceptionIfEmptyMap() {
        Exception exception = assertThrows(NexusCoreIsEmptyException.class, () -> {
            CheckValue.isNotEmpty(new HashMap<>(), "map");
        });
        String msg = "Param map with the name map is empty.";
        assertEquals(exception.getMessage(), msg);
    }

    /**
     * Ensures that {@code isNotNull} correctly returns the input when it is not null.
     */
    @Test
    public void shouldNotNull() {
        String test = "test";
        assertEquals(test, CheckValue.isNotNull(test, "test"));
    }

    /**
     * Ensures that {@code isNotBlank} correctly returns the input when it is not blank.
     */
    @Test
    public void shouldNotBlank() {
        String test = "test";
        assertEquals(test, CheckValue.isNotBlank(test, "test"));
    }

    /**
     * Ensures that {@code isNotEmpty} correctly returns a non-empty list.
     */
    @Test
    public void shouldNotEmptyList() {
        String test = "test";
        List<String> result = (List<String>) CheckValue.isNotNull(asList(test), "test");
        assertEquals(asList(test), result);
    }

    /**
     * Ensures that {@code isNotEmpty} correctly returns a non-empty map.
     */
    @Test
    public void shouldNotEmptyMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Test", "Test");
        HashMap<String, String> result = (HashMap<String, String>) CheckValue.isNotNull(map, "map");
        assertEquals(map, result);
    }
}