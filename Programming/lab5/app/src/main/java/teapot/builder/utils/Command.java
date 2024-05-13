package teapot.builder.utils;

import java.time.DateTimeException;

import teapot.builder.utils.interfaces.Executable;

public final class Command implements Executable {
    public final String description;
    public final Class<?>[] requiredArgs;
    public final Executable action;

    public Command(final String description,
            final Class<?>[] requiredArgs, final Executable action) {
        this.description = description;
        this.requiredArgs = requiredArgs;
        this.action = action;
    }

    public Command(final String description, final Executable action) {
        this(description, new Class<?>[0], action);
    }

    public Command(final Executable action) {
        this("No description provided", action);
    }

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
                throw new IllegalArgumentException( // TODO test this
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
        // TODO mb show how many args skipped
        action.execute(args);
    }

}
