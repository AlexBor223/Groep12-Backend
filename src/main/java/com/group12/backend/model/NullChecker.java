package com.group12.backend.model;

import java.util.Objects;
import java.util.stream.Stream;

public class NullChecker {
    public boolean areAllNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::isNull);
    }

    public boolean areAllNotNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::nonNull);
    }
}
