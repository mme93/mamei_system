package de.mameie.databasemanager.util.check;


import de.mameie.databasemanager.util.check.exception.ValueException;
import org.springframework.stereotype.Service;

/**
 * Service class for checking values.
 */
@Service
public class CheckValue {

    /**
     * Checks if the specified object is not null.
     *
     * @param obj       the object to check
     * @param valueName the name of the value, used in the exception message if the object is null
     * @return the original object if it is not null
     * @throws ValueException if the object is null
     */
    public static Object isNotNull(Object obj, String valueName) {
        if (obj == null) {
            throw new ValueException(String.format("No value set (null) with name %s.", valueName));
        }
        return obj;
    }

}
