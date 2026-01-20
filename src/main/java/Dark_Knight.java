import java.util.Scanner;

public class Dark_Knight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("_".repeat(50));
        System.out.println("Hello! I'm Dark Knight");
        System.out.println("I'm here to guard you through the dark nights.");
        System.out.println("What can I do for you?");

        String content = scanner.nextLine();

        while (!content.equals("bye")) {
            System.out.println("_".repeat(50));
            System.out.println(content);
            System.out.println("_".repeat(50));
            content = scanner.nextLine();
        }

        System.out.println("_".repeat(50));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_".repeat(50));
    }
}
