package teapot_rocket.utils;

import java.util.LinkedList;

import teapot_rocket.utils.exceptions.EmptyArrayException;
import teapot_rocket.utils.exceptions.EmptyStringException;
import teapot_rocket.utils.exceptions.NonPositiveNumberException;

/**
 * The {@code RequirementHandler} class provides utility methods for checking
 * requirements on various types of input values commonly used in rocket
 * component definitions.
 * It includes methods to enforce positivity of numbers, non-emptiness of
 * strings, and non-emptiness of arrays (specifically LinkedLists).
 * 
 * @author Gleb Kiva
 * @see teapot_rocket.utils.exceptions.EmptyStringException
 * @see teapot_rocket.utils.exceptions.EmptyArrayException
 * @see teapot_rocket.utils.exceptions.NonPositiveNumberException
 */
public final class RequirementHandler { // TODO reconsider constructor
    /**
     * Initializes empty RequirementHandler.
     */
    private RequirementHandler() {
    }

    /**
     * Ensures that a numeric value is positive.
     * 
     * @param value the numeric value to check
     * @return the same value if it is positive
     * @throws NonPositiveNumberException if the value is not positive
     */
    public static double requirePositive(double value)
            throws NonPositiveNumberException {
        if (value <= 0) {
            throw new NonPositiveNumberException();
        }
        return value;
    }

    /**
     * Ensures that a string is not empty.
     * 
     * @param value the string to check
     * @return the same string if it is not empty
     * @throws EmptyStringException if the string is empty or null
     */
    public static String requireNonEmptyString(String value)
            throws EmptyStringException {
        if (value == null || value.isEmpty()) {
            throw new EmptyStringException();
        }
        return value;
    }

    /**
     * Ensures that a LinkedList is not empty.
     * 
     * @param value the LinkedList to check
     * @param <T>   the type of elements in the LinkedList
     * @return the same LinkedList if it is not empty
     * @throws EmptyArrayException if the LinkedList is empty or null
     */
    public static <T> LinkedList<T> requireNonEmptyArray(final LinkedList<T> value)
            throws EmptyArrayException {
        if (value == null || value.isEmpty()) {
            throw new EmptyArrayException();
        }
        return value;
    }
}
