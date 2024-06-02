package teapot.builder.utils;

import java.time.DateTimeException;

import teapot.builder.utils.interfaces.Executable;

/**
 * Represents a command that can be executed with specified arguments.
 *
 * @author Gleb Kiva
 */
public final class Command implements Executable {

    /**
     * Description of the command.
     */
    public final String description;

    /**
     * Array of required argument types for the command.
     */
    public final Class<?>[] requiredArgs;

    /**
     * Action to be executed when the command is invoked.
     */
    public final Executable action;

    /**
     * Constructs a Command with a description, required argument types, and action.
     *
     * @param description  The description of the command.
     * @param requiredArgs The array of required argument types.
     * @param action       The action to be executed when the command is invoked.
     */
    public Command(final String description, final Class<?>[] requiredArgs, final Executable action) {
        this.description = description;
        this.requiredArgs = requiredArgs;
        this.action = action;
    }

    /**
     * Constructs a Command with a description and action.
     *
     * @param description The description of the command.
     * @param action      The action to be executed when the command is invoked.
     */
    public Command(final String description, final Executable action) {
        this(description, new Class<?>[0], action);
    }

    /**
     * Constructs a Command with a default description.
     *
     * @param action The action to be executed when the command is invoked.
     */
    public Command(final Executable action) {
        this("No description provided", action);
    }

    /**
     * Executes the command with the specified arguments.
     *
     * @param args The arguments to be passed to the command.
     * @throws IllegalArgumentException If any of the arguments are invalid or
     *                                  missing.
     */
    @Override
    public void execute(String... args) throws IllegalArgumentException {
        // validating argument types
        for (int i = 0; i < requiredArgs.length; i++) {
            var requiredArg = requiredArgs[i];
            var requiredTypeName = requiredArg.getSimpleName();
            try {
                var arg = args[i];
                RequirementHandler.requireParsable(arg, requiredTypeName);
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException(
                        String.format("Argument typed %s with index %s not provided. Caused by %s",
                                requiredTypeName, i + 1, e),
                        e); // TODO replace with custom exception
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        String.format("Argument with index %s of type %s expected. Caused by %s",
                                i + 1, requiredTypeName, e),
                        e);
            } catch (DateTimeException e) {
                throw new IllegalArgumentException(
                        String.format("Invalid datetime format with index %s. Caused by %s",
                                i + 1, e),
                        e);
            }
        }
        // if everything's alright, executing
        action.execute(args);
    }
}
