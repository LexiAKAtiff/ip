package darkknight;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "_".repeat(50);
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows all available commands.
     * AI-assisted: added to support the 'help' command.
     */
    public String showHelp() {
        return """
                Here are the commands you can use:
                  list                              - show all tasks
                  todo <desc>                       - add a todo task
                  deadline <desc> /by <date>        - add a deadline task
                  event <desc> /from <date> /to <date> - add an event task
                  mark <index>                      - mark a task as done
                  unmark <index>                    - mark a task as not done
                  delete <index>                    - delete a task
                  find <keyword>                    - search tasks by keyword
                  archive <index>                   - move a task to archive
                  unarchive <index>                 - move a task out of archive
                  archivelist                       - show all archived tasks
                  bye                               - exit the app
                  help                              - show this help message
                """;
    }

    /**
     * Shows welcome message
     */
    public String showWelcome() {
        return """
                Hello! I'm Dark Knight
                I'm here to guard you through the dark nights.
                What can I do for you?
                Type 'help' for commands supported!
                """;
    }

    /**
     * Shows goodbye message
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads a command from the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows an error message
     */
    public String showError(String message) {
        return "Uh oh!" + message;
    }

    /**
     * Prints the task list
     */
    public String printList(ArrayList<Task> tasks) {
        String str = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            str = str + (index) + "." + tasks.get(i).toString() + "\n";
        }
        return str;
    }

    /**
     * Shows message when a task is added
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Shows message when a task is deleted
     */
    public String showTaskDeleted(Task task, int taskCount) {
        return "Noted. I've removed this task: \n"
                + "  " + task.toString() + "\n"
                + "Now you have " + taskCount + " tasks in the list.";
    }

    /**
     * Shows message when a task is marked as done
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done: \n"
                + task.toString();
    }

    /**
     * Shows message when a task is unmarked
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet: \n"
                + task.toString();
    }

    /**
     * Shows the list of tasks matching the keyword
     */
    public String showMatchingList(TaskList tasks) throws DarkKnightException {
        String str = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            str = str + index + "." + tasks.getTask(i).toString() + "\n";
        }
        return str;
    }

    /**
     * Shows message when a task is archived
     */
    public String showTaskArchived(Task task) {
        return "OK, I have archived this task for you: \n"
                + task.toString();
    }

    /**
     * Shows message when a task is unarchived
     */
    public String showTaskUnarchived(Task task) {
        return "OK, I have moved this task out of archive for you: \n"
                + task.toString();
    }
    /**
     * Prints an archive list
     */
    public String printArchiveList(ArrayList<Task> tasks) {
        String str = "Here are the tasks in your archive: \n";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            str = str + (index) + "." + tasks.get(i).toString() + "\n";
        }
        return str;
    }
}
