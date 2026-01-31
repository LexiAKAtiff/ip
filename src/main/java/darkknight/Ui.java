package darkknight;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Ui {
    private final Scanner scanner;
    private static final String LINE = "_".repeat(50);

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a horizontal line
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Shows welcome message
     */
    public void showWelcome() {
        printLine();
        System.out.println("Hello! I'm Dark Knight");
        System.out.println("I'm here to guard you through the dark nights.");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Shows goodbye message
     */
    public void showGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
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
    public void showError(String message) {
        printLine();
        System.out.println("Uh oh! " + message);
        printLine();
    }

    /**
     * Prints the task list
     */
    public void printList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i).toString());
        }
        printLine();
    }

    /**
     * Shows message when a task is added
     */
    public void showTaskAdded(Task task, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        printLine();
    }

    /**
     * Shows message when a task is deleted
     */
    public void showTaskDeleted(Task task, int taskCount) {
        printLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        printLine();
    }

    /**
     * Shows message when a task is marked as done
     */
    public void showTaskMarked(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
        printLine();
    }

    /**
     * Shows message when a task is unmarked
     */
    public void showTaskUnmarked(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(task);
        printLine();
    }
}
