package teapot.models;

import teapot.utils.handlers.RequirementHandler;

public record CommandRequest(String abbreviation, String... args) {
    public CommandRequest(final String abbreviation, final String... args) {
        this.abbreviation = RequirementHandler.requireNonEmptyString(abbreviation);
        this.args = args;
    }

}
