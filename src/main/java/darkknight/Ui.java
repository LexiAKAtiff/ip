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
     * Shows welcome message
     */
    public String showWelcome() {
        return """
                Hello! I'm Dark Knight
                I'm here to guard you through the dark nights.
                What can I do for you?
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
}
