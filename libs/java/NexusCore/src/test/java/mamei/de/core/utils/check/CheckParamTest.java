package mamei.de.core.utils.check;

import mamei.de.core.exception.NexusCoreIsEmptyException;
import mamei.de.core.exception.NexusCoreNullPointerException;
import mamei.de.core.utils.CheckParam;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Unit tests for the {@link CheckParam} utility class.
 *
 * <p>This class ensures that parameter validation methods work as expected
 * by throwing appropriate exceptions for null, empty, or blank values.</p>
 *
 * <p>It uses JUnit for assertions and exception testing.</p>
 */
public class CheckParamTest {

    /**
     * Verifies that {@code isNotNull} throws a {@link NexusCoreNullPointerException}
     * when the provided value is {@code null}.
     */
    @Test
    public void shouldThrowExceptionIfNull() {
        Exception exception = assertThrows(NexusCoreNullPointerException.class, () -> {
            CheckParam.isNotNull(null, "test");
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
            CheckParam.isNotBlank("", "test");
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
            CheckParam.isNotEmpty(asList(), "test");
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
            CheckParam.isNotEmpty(new HashMap<>(), "map");
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
        assertEquals(test, CheckParam.isNotNull(test, "test"));
    }

    /**
     * Ensures that {@code isNotBlank} correctly returns the input when it is not blank.
     */
    @Test
    public void shouldNotBlank() {
        String test = "test";
        assertEquals(test, CheckParam.isNotBlank(test, "test"));
    }

    /**
     * Ensures that {@code isNotEmpty} correctly returns a non-empty list.
     */
    @Test
    public void shouldNotEmptyList() {
        String test = "test";
        List<String> result = (List<String>) CheckParam.isNotNull(asList(test), "test");
        assertEquals(asList(test), result);
    }

    /**
     * Ensures that {@code isNotEmpty} correctly returns a non-empty map.
     */
    @Test
    public void shouldNotEmptyMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Test", "Test");
        HashMap<String, String> result = (HashMap<String, String>) CheckParam.isNotNull(map, "map");
        assertEquals(map, result);
    }
}