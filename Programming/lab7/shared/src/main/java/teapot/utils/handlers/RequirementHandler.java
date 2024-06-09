package teapot.utils.handlers;


import teapot.utils.exceptions.EmptyArrayException;
import teapot.utils.exceptions.EmptyStringException;
import teapot.utils.exceptions.NonPositiveNumberException;
import teapot.utils.exceptions.NumberException;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Objects;

public final class RequirementHandler {

    private RequirementHandler() {
        // Private constructor to prevent instantiation
    }

    public static int requireInteger(String value)
            throws IllegalArgumentException {
        try {
            return Integer.parseInt(requireNonEmptyString(value));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot convert argument to integer");
        }
    }

    public static <T extends Number> T requirePositive(T value)
            throws NonPositiveNumberException {
        if (value.doubleValue() <= 0) {
            throw new NonPositiveNumberException();
        }
        return value;
    }

    public static String requireNonEmptyString(String value)
            throws EmptyStringException {
        if (value == null || value.isEmpty()) {
            throw new EmptyStringException();
        }
        return value;
    }

    public static <T extends Number> T requireGreaterThan(T value, T boundary)
            throws NumberException {
        if (value.doubleValue() < boundary.doubleValue()) {
            throw new NumberException();
        }
        return value;
    }

    public static <T extends Number> T requireLessThan(T value, T boundary)
            throws NumberException {
        if (value.doubleValue() > boundary.doubleValue()) {
            throw new NumberException();
        }
        return value;
    }

    public static void requireParsable(String arg, String requiredTypeName)
            throws IllegalArgumentException, DateTimeException {
        switch (requiredTypeName) {
            case "Integer":
                Integer.parseInt(arg);
                break;
            case "Long":
                Long.parseLong(arg);
                break;
            case "Float":
                Float.parseFloat(arg);
                break;
            case "Double":
                Double.parseDouble(arg);
                break;
            case "ZonedDateTime":
                ZonedDateTime.parse(arg);
                break;
            case "String":
                Objects.requireNonNull(arg);
                break;
            default:
                throw new IllegalArgumentException("No such type");
        }
    }

    public static <T> LinkedList<T> requireNonEmptyArray(final LinkedList<T> value)
            throws EmptyArrayException {
        if (value == null || value.isEmpty()) {
            throw new EmptyArrayException();
        }
        return value;
    }
}
