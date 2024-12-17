package de.mameie.databasemanager.util.check;

import de.mameie.databasemanager.util.check.exception.ParamException;

import java.util.List;

/**
 * Utility class for parameter validation.
 */
public class CheckParam {

    /**
     * Checks if the specified object is not null.
     *
     * @param obj     the object to check
     * @param objName the name of the object, used in the exception message if the object is null
     * @return the original object if it is not null
     * @throws ParamException if the object is null
     */
    public static Object isNotNull(Object obj, String objName) {
        if (obj == null) {
            throw new ParamException(String.format("Param with the name %s is null.", objName));
        }
        return obj;
    }

    /**
     * Validates that the provided object is not null and, if it is a String, not blank.
     *
     * @param obj     The object to check. Can be of any type, but commonly a String.
     * @param objName The name of the object being checked, used in exception messages.
     * @return The original object if it passes the validation checks.
     * @throws ParamException if the object is null or, if a String, is empty or blank.
     */
    public static Object isNotBlank(Object obj, String objName) {
        if (obj == null) {
            throw new ParamException(String.format("Param with the name %s is null.", objName));
        }
        if (obj instanceof String) {
            if (obj.toString().trim().length() == 0) {
                throw new ParamException(String.format("Param with the name %s is empty.", objName));
            }
        }
        return obj;
    }

    /**
     * Checks if the specified list is not empty.
     *
     * @param list    the list to check
     * @param objName the name of the object, used in the exception message if the list is empty
     * @return the original list if it is not empty
     * @throws ParamException if the list is empty
     */
    public static List<?> isNotEmpty(List<?> list, String objName) {
        if (list.isEmpty()) {
            throw new ParamException(String.format("Param list with the name %s is null.", objName));
        }
        return list;
    }


}
