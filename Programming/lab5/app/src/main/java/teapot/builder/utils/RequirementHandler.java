package teapot.builder.utils;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.util.Objects;

import teapot.builder.utils.exceptions.NumberException;
import teapot.rocket.utils.exceptions.EmptyStringException;
import teapot.rocket.utils.exceptions.NonPositiveNumberException;

public final class RequirementHandler {
    private RequirementHandler() {
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
}
