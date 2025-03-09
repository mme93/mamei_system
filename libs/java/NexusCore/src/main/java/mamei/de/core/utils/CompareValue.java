package mamei.de.core.utils;

public class CompareValue {

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNotNull(Object value) {
        return value != null;
    }

    public static boolean isBlank(String value) {
        return isNull(value) || value.isEmpty();
    }

}
