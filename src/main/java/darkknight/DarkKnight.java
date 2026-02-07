package darkknight;

/**
 * Main class for the application
 */
public class DarkKnight {
    private static Storage storage = new Storage("data/dark_knight.txt");
    private static Ui ui;
    private static TaskList tasks;
    private static Parser parser = new Parser();

    /**
     * Constructs the chatbot
     */
    public DarkKnight(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DarkKnightException e) {
            tasks = new TaskList();
        }
    }

//    /**
//     * Controls the whole workflow
//     */
//    public void run() {
//        ui.showWelcome();
//
//        String content = ui.readCommand();
//        while (!content.equals("bye")) {
//            try {
//                parser.parseCommand(content, tasks, ui, storage);
//            } catch (DarkKnightException e) {
//                ui.showError(e.getMessage());
//            }
//            content = ui.readCommand();
//        }
//
//        ui.showGoodbye();
//    }

//    public static void main(String[] args) {
//        new DarkKnight("data/dark_knight.txt").run();
//    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String str = parser.parseCommand(input, tasks, ui, storage);
            return str;
        } catch (DarkKnightException e) {
            return ui.showError(e.getMessage());
        }
    }
}
