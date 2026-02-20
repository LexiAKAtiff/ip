package darkknight;

/**
 * Main class for the application
 */
public class DarkKnight {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private final Parser parser;
    private final Archive archive;

    /**
     * Constructs the chatbot
     */
    public DarkKnight(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        archive = new Archive("data/archive.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DarkKnightException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the greeting message shown on startup.
     * AI-assisted: added by Claude to support greeting on app launch.
     */
    public String getGreeting() {
        return ui.showWelcome();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String str = parser.parseCommand(input, tasks, ui, storage, archive);
            return str;
        } catch (DarkKnightException e) {
            return ui.showError(e.getMessage());
        }
    }
}
