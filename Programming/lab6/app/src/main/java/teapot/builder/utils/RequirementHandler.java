package teapot.builder.utils;

import java.time.DateTimeException;
import java.time.ZonedDateTime;
import java.util.Objects;

import teapot.builder.utils.exceptions.NumberException;
import teapot.builder.utils.exceptions.EmptyStringException;
import teapot.builder.utils.exceptions.NonPositiveNumberException;

/**
 * Utility class for handling requirements and validations of various types of
 * values.
 *
 * @author Gleb Kiva
 */
public final class RequirementHandler {

    private RequirementHandler() {
        // Private constructor to prevent instantiation
    }

    /**
     * Requires that a numeric value is positive.
     *
     * @param value The value to be validated.
     * @param <T>   Type of the value, must be a subclass of Number.
     * @return The validated positive value.
     * @throws NonPositiveNumberException If the value is not positive.
     */
    public static <T extends Number> T requirePositive(T value)
            throws NonPositiveNumberException {
        if (value.doubleValue() <= 0) {
            throw new NonPositiveNumberException();
        }
        return value;
    }

    /**
     * Requires that a string value is not empty.
     *
     * @param value The string value to be validated.
     * @return The validated non-empty string value.
     * @throws EmptyStringException If the value is null or empty.
     */
    public static String requireNonEmptyString(String value)
            throws EmptyStringException {
        if (value == null || value.isEmpty()) {
            throw new EmptyStringException();
        }
        return value;
    }

    /**
     * Requires that a numeric value is greater than a specified boundary.
     *
     * @param value    The value to be validated.
     * @param boundary The boundary value to compare against.
     * @param <T>      Type of the value, must be a subclass of Number.
     * @return The validated value if greater than the boundary.
     * @throws NumberException If the value is not greater than the boundary.
     */
    public static <T extends Number> T requireGreaterThan(T value, T boundary)
            throws NumberException {
        if (value.doubleValue() < boundary.doubleValue()) {
            throw new NumberException();
        }
        return value;
    }

    /**
     * Requires that a numeric value is less than a specified boundary.
     *
     * @param value    The value to be validated.
     * @param boundary The boundary value to compare against.
     * @param <T>      Type of the value, must be a subclass of Number.
     * @return The validated value if less than the boundary.
     * @throws NumberException If the value is not less than the boundary.
     */
    public static <T extends Number> T requireLessThan(T value, T boundary)
            throws NumberException {
        if (value.doubleValue() > boundary.doubleValue()) {
            throw new NumberException();
        }
        return value;
    }

    /**
     * Requires that a string argument is parseable as a specified type.
     *
     * @param arg              The string argument to be parsed.
     * @param requiredTypeName The name of the required type (e.g., "Integer",
     *                         "Long", "Float", "Double", "ZonedDateTime",
     *                         "String").
     * @throws IllegalArgumentException If the argument cannot be parsed as the
     *                                  specified type.
     * @throws DateTimeException        If the argument cannot be parsed as a
     *                                  ZonedDateTime.
     */
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
