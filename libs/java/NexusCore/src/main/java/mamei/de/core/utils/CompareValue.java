package mamei.de.core.utils;

public class CompareValue {

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNotNull(Object value) {
        return value != null;
    }

    public static boolean isBlank(String value) {
        return !isNotNull(value) && value.isEmpty();
    }

    public static boolean isNotBlank(String value) {
        return isNotNull(value) && value.isEmpty();
    }

}
