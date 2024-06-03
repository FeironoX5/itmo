package teapot.utils;

import teapot.models.Response;
import teapot.utils.interfaces.Executable;

import java.time.DateTimeException;
import java.util.TreeMap;

public final class Command implements Executable {
    public final String description;
    public final TreeMap<String, Class<?>> requiredArgs;
    public final Executable action;

    public Command(final String description,
                   final TreeMap<String, Class<?>> requiredArgs,
                   final Executable action) {
        this.description = description;
        this.requiredArgs = requiredArgs;
        this.action = action;
    }

    public Command(final String description, final Executable action) {
        this(description, new TreeMap<>(), action);
    }

    public Command(final Executable action) {
        this("No description provided", action);
    }

    @Override
    public Response execute(String... args) throws IllegalArgumentException {
        // validating argument types
        int i = 0;
        for (var requiredArg : requiredArgs.values()) {
            var requiredTypeName = requiredArg.getSimpleName();
            try {
                var arg = args[i++];
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
        return action.execute(args);
    }
}
