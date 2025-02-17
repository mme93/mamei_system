package mamei.de.core.utils;

import mamei.de.core.exception.NexusCoreIsEmptyException;
import mamei.de.core.exception.NexusCoreNullPointerException;

import java.util.List;
import java.util.Map;

public class CheckParam {

    public static Object isNotNull(Object param, String valueName) {
        if (param == null) {
            throw new NexusCoreNullPointerException(String.format("Param with the name %s is null.", valueName));
        }
        return param;
    }

    public static Object isNotBlank(Object param, String valueName) {
        if (param == null) {
            throw new NexusCoreNullPointerException(String.format("Param with the name %s is null.", valueName));
        }

        if (param instanceof String) {
            if (param.toString().trim().length() == 0) {
                throw new NexusCoreIsEmptyException(String.format("String with the name %s is empty.", valueName));
            }
        }
        return param;
    }

    public static List<?> isNotEmpty(List<?> list, String listName) {
        if (list.isEmpty()) {
            throw new NexusCoreIsEmptyException(String.format("Param list with the name %s is empty.", listName));
        }
        return list;
    }

    public static Map<?,?> isNotEmpty(Map<?,?> map, String mapName) {
        if (map.isEmpty()) {
            throw new NexusCoreIsEmptyException(String.format("Param map with the name %s is empty.", mapName));
        }
        return map;
    }


}
