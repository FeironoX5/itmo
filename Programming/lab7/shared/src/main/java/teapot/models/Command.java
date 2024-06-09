package teapot.models;

import teapot.utils.handlers.RequirementHandler;
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
//        Server.instance.logger.info(String.format("Validating argument types before executing command, %s args received", args.length));
        int i = 0;
        for (var requiredArg : requiredArgs.entrySet()) {
            var requiredTypeName = requiredArg.getValue().getSimpleName();
            try {
                var arg = args[i++];
                RequirementHandler.requireParsable(arg, requiredTypeName);
//                Server.instance.logger.info(String.format("Argument %s checked", i));
            } catch (IndexOutOfBoundsException e) {
                String message = String.format("Field %s with index %s typed %s is not provided.",
                        requiredArg.getKey(), i, requiredTypeName);
//                Server.instance.logger.error(message);
                throw new IllegalArgumentException(message, e); // TODO replace with custom exception
            } catch (IllegalArgumentException e) {
                String message = String.format("Cannot parse argument %s with index %s typed %s.",
                        requiredArg.getKey(), i, requiredTypeName);
//                Server.instance.logger.error(message);
                throw new IllegalArgumentException(message, e);
            } catch (DateTimeException e) {
                String message = String.format("Invalid datetime format with index %s. Caused by %s", i, e);
//                Server.instance.logger.error(message);
                throw new IllegalArgumentException(
                        message, e);
            }
        }
        // if everything's alright, executing
//        Server.instance.logger.info("Args checked successfully, executing action");
        return action.execute(args);
    }
}
