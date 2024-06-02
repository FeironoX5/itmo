public final class Console {
    public static final Console instance = new Console();

    private final Scanner in = new Scanner(System.in);
    private final HashMap<String, Command> commandByAbbreviationMap;
    private final LinkedList<String> history;
    private boolean running;

    private Console() {
        commandByAbbreviationMap = new HashMap<>();
        history = new LinkedList<>();
        running = true;
    }

    public void listen() {
        while (running) {
            System.out.print("\nType any command: ");
            String[] userCommand = in.nextLine().toLowerCase().split("\\s+");
            runCommand(userCommand[0], Arrays.copyOfRange(userCommand, 1, userCommand.length));
        }
    }

    public void runCommand(String abbreviation, String... args) {
        Command c = commandByAbbreviationMap.getOrDefault(abbreviation,
                new Command((String... emptyArgs) -> System.err.println("No such command")));
        try {
            c.execute(args);
            history.addLast(abbreviation);
            if (history.size() > 15) {
                history.removeFirst();
            }
        } catch (IllegalArgumentException e) {
            System.err.println(String.format("Illegal argument found: %s", e.getMessage()));
        }
    }

    public void addCommand(String abbreviation, Command command) {
        commandByAbbreviationMap.put(abbreviation, command);
    }

    public void addCommands(HashMap<String, Command> commands) {
        commandByAbbreviationMap.putAll(commands);
    }

    public HashMap<String, Command> getCommands() {
        return commandByAbbreviationMap;
    }

    public LinkedList<String> getHistory() {
        return history;
    }

    public void close() {
        running = false;
    }
}
