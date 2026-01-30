package darkknight;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

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

    public static Task addTodo(String description) throws DarkKnightException {
        if (description.trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for todo!");
        }
        Todo todo = new Todo(description);
        tasks.add(todo);
        return todo;
    }

    public static Task addDeadline(String description) throws DarkKnightException {
        String[] parts = description.split(" /by ");
        if (description.trim().isEmpty() || parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for deadline!");
        }
        String ddl= parts[1];
        Deadline deadline = new Deadline(parts[0], ddl);
        tasks.add(deadline);
        return deadline;
    }

    public static Task addEvent(String description) throws DarkKnightException {
        String[] parts = description.split(" /from ");
        if (description.trim().isEmpty() || parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for event!");
        }
        String[] otherParts = parts[1].split(" /to ");
        if (otherParts.length < 2 || otherParts[1].trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for event!");
        }
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


    public static void readCommand(String taskDetail) throws DarkKnightException {
        String[] parts = taskDetail.split(" ");
        String command = parts[0];
        if (command.equals("list")) {
            if (tasks.isEmpty()) {
                throw new DarkKnightException("There is nothing is your list!");
            }
            printList();
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            Task task;
            if (command.equals("todo")) {
                try {
                    task = addTodo(taskDetail.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DarkKnightException("Wrong format for todo!");
                }
            } else if (command.equals("deadline")) {
                try {
                    task = addDeadline(taskDetail.substring(9));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DarkKnightException("Wrong format for deadline!");
                }
            } else {
                try {
                    task = addEvent(taskDetail.substring(6));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DarkKnightException("Wrong format for event!");
                }
            }
            addTaskPrint(task);
        } else if (command.equals("mark")){
            if (parts.length < 2) {
                throw new DarkKnightException("I don't know which one to mark.");
            }
            try {
                int index = Integer.parseInt(parts[1])-1;
                if (index >= tasks.size()) {
                    throw new DarkKnightException("I can't find this task.");
                }
                Task task = tasks.get(index);
                task.mark();
                printLine();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task);
                printLine();
            } catch (NumberFormatException e) {
                throw new DarkKnightException(parts[1] + " is not a valid number.");
            }
        } else if (command.equals("unmark")) {
            if (parts.length < 2) {
                throw new DarkKnightException("I don't know which one to unmark.");
            }
            try {
                int index = Integer.parseInt(parts[1])-1;
                if (index >= tasks.size()) {
                    throw new DarkKnightException("I can't find this task.");
                }
                Task task = tasks.get(index);
                task.unmark();
                printLine();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(task);
                printLine();
            } catch(NumberFormatException e) {
                throw new DarkKnightException(parts[1] + " is not a valid number.");
            }
        } else if (command.equals("delete")){
            if (parts.length < 2) {
                throw new DarkKnightException("I don't know which one to delete.");
            }
            try {
                int index = Integer.parseInt(parts[1])-1;
                if (index >= tasks.size()) {
                    throw new DarkKnightException("I can't find this task.");
                }
                Task task = tasks.get(index);
                tasks.remove(index);
                printLine();
                System.out.println("Noted. I've removed this task: ");
                System.out.println(task);
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                printLine();
            } catch(NumberFormatException e) {
                throw new DarkKnightException(parts[1] + " is not a valid number.");
            }
        } else {
            throw new DarkKnightException("I can't perform this task, it's beyond my capability!");
        }
        updateFile();
    }

    public static void updateFile() {
        try {
            // 使用相对路径 "./data/dark_knight.txt"，OS独立
            Path dirPath = Paths.get("data");
            Path filePath = Paths.get("data", "dark_knight.txt");

            // 如果文件夹不存在，创建它
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // 写入文件
            try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                for (Task task : tasks) {
                    writer.write(task.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //Welcome!
        printLine();
        System.out.println("Hello! I'm Dark Knight");
        System.out.println("I'm here to guard you through the dark nights.");
        System.out.println("What can I do for you?");
        printLine();

        String content = scanner.nextLine();

        while (!content.equals("bye") ) {
            try {
                readCommand(content);
            } catch (DarkKnightException e) {
                printLine();
                System.out.println("Uh oh! " + e.getMessage());
                printLine();
            }
            content = scanner.nextLine();
        }

        //Say goodbye!
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
