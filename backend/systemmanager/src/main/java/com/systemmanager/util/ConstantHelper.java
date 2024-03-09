package com.systemmanager.util;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A utility class for handling constants.
 */
@Service
public class ConstantHelper {

    /**
     * Converts a list of constants to a single string, separating each constant with a comma and space.
     *
     * @param constantList The list of constants to convert.
     * @return A string representation of the list of constants.
     * @throws RuntimeException if the constantList is empty.
     */
    public String arrayListToString(List<String> constantList) {
        if (constantList.isEmpty()) {
            throw new RuntimeException("No Constant in list");
        } else if (constantList.size() == 1) {
            return constantList.get(0);
        }
        StringBuilder formatBuilder = new StringBuilder();
        for (int i = 0; i < constantList.size(); i++) {
            formatBuilder.append(constantList.get(i));
            if (i < constantList.size() - 1) {
                formatBuilder.append(", ");
            }
        }
        return formatBuilder.toString();
    }
}
