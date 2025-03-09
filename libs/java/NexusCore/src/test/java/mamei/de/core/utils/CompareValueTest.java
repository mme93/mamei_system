package mamei.de.core.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompareValueTest {

    @Test
    public void shouldCompareValueIsNull() {
        assertTrue(CompareValue.isNull(null));
        assertFalse(CompareValue.isNull(2));
    }

    @Test
    public void shouldCompareValueIsNotNull() {
        assertTrue(CompareValue.isNotNull(2));
        assertFalse(CompareValue.isNotNull(null));
    }

    @Test
    public void shouldCompareValueIsBlank() {
        assertTrue(CompareValue.isBlank(null));
        assertTrue(CompareValue.isBlank(""));
        assertFalse(CompareValue.isBlank("Test"));
    }

}
