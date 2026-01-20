import java.util.Scanner;

public class Dark_Knight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("_".repeat(50));
        System.out.println("Hello! I'm Dark Knight");
        System.out.println("I'm here to guard you through the dark nights.");
        System.out.println("What can I do for you?");
        System.out.println("_".repeat(50));

        String[] todoList = new String[100];
        int counter = 0;

        String content = scanner.nextLine();

        while (!content.equals("bye")) {
            System.out.println("_".repeat(50));

            todoList[counter] = content;
            counter += 1;

            System.out.println("added: " + content);
            System.out.println("_".repeat(50));
            content = scanner.nextLine();

            if (content.equals("list")) {
                System.out.println("_".repeat(50));
                for (int i=0; i < counter; i++) {
                    System.out.printf("%d. %s\n", i+1, todoList[i]);
                }
                System.out.println("_".repeat(50));
                content = scanner.nextLine();
            }

        }

        System.out.println("_".repeat(50));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_".repeat(50));

    }
}
