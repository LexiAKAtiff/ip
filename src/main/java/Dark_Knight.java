import java.util.Scanner;
import java.util.ArrayList;

public class Dark_Knight {

    public static void printList(ArrayList<Task> todoList) {
        int l = todoList.size();
        for (int i = 0; i < l; i++) {
            Task task = todoList.get(i);
            System.out.printf("%d.[%s] %s\n", i+1, task.getIcon(), task.getName());
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("_".repeat(50));
        System.out.println("Hello! I'm Dark Knight");
        System.out.println("I'm here to guard you through the dark nights.");
        System.out.println("What can I do for you?");
        System.out.println("_".repeat(50));

        ArrayList<Task> todoList = new ArrayList<>();
        int counter = 0;

        String content = scanner.nextLine();

        while (!content.equals("bye") && !content.equals("list")) {

            todoList.add(new Task(content));
            System.out.println("_".repeat(50));
            System.out.println("added: " + content);
            System.out.println("_".repeat(50));
            counter += 1;
            content = scanner.nextLine();

        }

        if (content.equals("list")) {
            System.out.println("_".repeat(50));
            System.out.println("Here are the tasks in your list: ");
            Dark_Knight.printList(todoList);
            System.out.println("_".repeat(50));
            content = scanner.nextLine();
        }

        while(!content.equals("bye")) {
            String[] parts = content.split(" ");
            if (parts[0].equals("mark")) {
                int index = Integer.parseInt(parts[1])-1;
                Task task = todoList.get(index);
                task.mark();
                System.out.println("_".repeat(50));
                System.out.println("Nice! I've marked this task as done");
                System.out.println("[X] " + task.getName());
                System.out.println("_".repeat(50));
            } else if (parts[0].equals("unmark")) {
                int index = Integer.parseInt(parts[1])-1;
                Task task = todoList.get(index);
                task.mark();
                System.out.println("_".repeat(50));
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println("[ ] " + task.getName());
                System.out.println("_".repeat(50));
            } else {
                continue;
            }
            content = scanner.nextLine();
        }

        System.out.println("_".repeat(50));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_".repeat(50));

    }
}
