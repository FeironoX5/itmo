package teapot.builder.utils.enums;

public enum CommandType {
    EXECUTE_SCRIPT("exec",
            "Read and execute a script from the specified file. The script contains commands as the user enters them interactively."),
    INFO("info",
            "Print information about the collection (type, initialization date, number of elements, etc.) to the standard output stream"),
    HELP("help", "Print help on available commands."),
    SHOW("show", "Output all elements of the collection in string representation to the standard output stream."),
    SHOW_DESCENDING_DISTANCES("showd", "Print the distance field values of all items in descending order."),
    SEARCH_BY_NAME("search", "Print the elements whose name field value starts with the given substring."),
    SAVE("save", "Save the collection to a file."),
    CLEAR("clear", "Clear the collection."),
    HISTORY("hist", "Print the last 15 commands (without their arguments)"),
    EXIT("exit", "End the program (without saving to a file)."),
    ADD("add", "Add a new item to the collection."),
    REMOVE("rm", "Remove an item from the collection by its id."),
    UPDATE("upd", "Update the value of the element in the collection whose id is equal to the given one."),
    REMOVE_GREATER("rmg", "Remove all elements from the collection that are greater than the given one."),
    REMOVE_LOWER("rml", "Remove from the collection all elements smaller than the given one."),
    REMOVE_BY_DISTANCE("rmd",
            "Remove from the collection all items whose distance field value is equivalent to the given one.");

    private final String abbreviation;
    private final String description;

    CommandType(final String abbreviation, final String description) {
        this.abbreviation = abbreviation;
        this.description = description;
    }

    public static CommandType getType(@NotNull final String abbreviation) {
        for (var commandType : values()) {
            if (commandType.getAbbreviation().equals(abbreviation)) {
                return commandType;
            }
        }
        return null;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getDescription() {
        return description;
    }
}
