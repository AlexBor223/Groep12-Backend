package com.group12.backend.model;

import java.util.Objects;
import java.util.stream.Stream;

public class NullChecker {
    /**
     * Method to check if all objects are null
     * @param objects
     * @return boolean
     */
    public boolean areAllNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::isNull);
    }

    /**
     * Method to check if all objects are not null
     * @param objects
     * @return boolean
     */
    public boolean areAllNotNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::nonNull);
    }
}
