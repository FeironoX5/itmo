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
        for (var requiredArg : requiredArgs.entrySet()) {
            var requiredTypeName = requiredArg.getValue().getSimpleName();
            try {
                var arg = args[i++];
                RequirementHandler.requireParsable(arg, requiredTypeName);
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException(
                        String.format("Field %s with index %s typed %s is not provided.",
                                requiredArg.getKey(), i, requiredTypeName),
                        e); // TODO replace with custom exception
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        String.format("Cannot parse argument %s with index %s typed %s.",
                                requiredArg.getKey(), i, requiredTypeName),
                        e);
            } catch (DateTimeException e) {
                throw new IllegalArgumentException(
                        String.format("Invalid datetime format with index %s. Caused by %s", i, e),
                        e);
            }
        }
        // if everything's alright, executing
        return action.execute(args);
    }
}
