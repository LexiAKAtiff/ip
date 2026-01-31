package darkknight;

/**
 * Main class for the application
 */
public class Dark_Knight {
    static Storage storage = new Storage("data/dark_knight.txt");
    static Ui ui;
    static TaskList tasks;
    static Parser parser = new Parser();

    /**
     * Constructs the chatbot
     */
    public Dark_Knight(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch(DarkKnightException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Controls the whole workflow
     */
    public void run() {
        ui.showWelcome();

        String content = ui.readCommand();
        while (!content.equals("bye")) {
            try {
                parser.parseCommand(content, tasks, ui, storage);
            } catch (DarkKnightException e) {
                ui.showError(e.getMessage());
            }
            content = ui.readCommand();
        }

        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Dark_Knight("data/dark_knight.txt").run();
    }
}
