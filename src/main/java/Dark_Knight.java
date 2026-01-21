import java.util.Scanner;
import java.util.ArrayList;

public class Dark_Knight {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void printLine() {
        System.out.println("_".repeat(50));
    }

    public static void printList() {
        printLine();
        System.out.println("Here are the tasks in your list: ");
        int l = tasks.size();
        for (int i = 0; i < l; i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s\n", i+1, task.toString());
        }
        printLine();
    }

    public static Task addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        return todo;
    }

    public static Task addDeadline(String description) {
        String[] parts = description.split(" /by ");
        String ddl= parts[1];
        Deadline deadline = new Deadline(parts[0], ddl);
        tasks.add(deadline);
        return deadline;
    }

    public static Task addEvent(String description) {
        String[] parts = description.split(" /from ");
        String taskName = parts[0];
        String[] otherParts = parts[1].split(" /to ");
        String from = otherParts[0];
        String to = otherParts[1];

        Event event = new Event(parts[0], from ,to);
        tasks.add(event);
        return event;
    }

    public static void addTaskPrint(Task task) {
        printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        printLine();
    }


    public static void readCommand(String taskDetail) {
        String[] parts = taskDetail.split(" ");
        String command = parts[0];
        if (command.equals("list")) {
            printList();
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            Task task;
            if (command.equals("todo")) {
                task = addTodo(taskDetail.substring(5));
            } else if (command.equals("deadline")) {
                task = addDeadline(taskDetail.substring(9));
            } else {
                task = addEvent(taskDetail.substring(6));
            }
            addTaskPrint(task);
        } else if (command.equals("mark")){
            int index = Integer.parseInt(parts[1])-1;
            Task task = tasks.get(index);
            task.mark();
            printLine();
            System.out.println("Nice! I've marked this task as done");
            System.out.println(task);
            printLine();
        } else if (command.equals("unmark")) {
            int index = Integer.parseInt(parts[1])-1;
            Task task = tasks.get(index);
            task.mark();
            printLine();
            System.out.println("OK, I've marked this task as not done yet");
            System.out.println(task);
            printLine();
        }
        else {
            System.out.println("Wrong command type!");
        }
    }

    public static void main(String[] args) {

        printLine();
        System.out.println("Hello! I'm Dark Knight");
        System.out.println("I'm here to guard you through the dark nights.");
        System.out.println("What can I do for you?");
        printLine();

        int counter = 0;

        String content = scanner.nextLine();

        while (!content.equals("bye") ) {
            readCommand(content);
            content = scanner.nextLine();
        }

        System.out.println("_".repeat(50));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_".repeat(50));

    }
}
