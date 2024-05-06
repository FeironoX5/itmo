package utils;

import java.util.LinkedList;

import utils.exceptions.EmptyArrayException;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;

public final class RequirementHandler {
    public static double requirePositive(double value)
            throws NonPositiveNumberException {
        if (value <= 0) {
            throw new NonPositiveNumberException();
        }
        return value;
    }

    public static String requireNonEmptyString(String value)
            throws EmptyStringException {
        if (value != null && value.isEmpty()) {
            throw new EmptyStringException();
        }
        return value;
    }

    public static <T> LinkedList<T> requireNonEmptyArray(final LinkedList<T> value)
            throws EmptyArrayException {
        if (value != null && value.size() == 0) {
            throw new EmptyArrayException();
        }
        return value;
    }
}