package darkknight;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file
     * @return tasks list
     */
    public ArrayList<Task> load() throws DarkKnightException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path path = Paths.get(filePath);

            // If dir does not exist then return an empty list
            if (!Files.exists(path)) {
                return tasks;
            }

            // read from the file
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        Task task = parseTaskFromFile(line);
                        if (task != null) {
                            tasks.add(task);
                        }
                    } catch (Exception e) {
                        // skip lines that are corrupted
                        System.out.println("Warning: Skipped corrupted line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            throw new DarkKnightException("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves tasks list to the file.
     */
    public void save(ArrayList<Task> tasks) throws DarkKnightException {
        try {
            Path path = Paths.get(filePath);
            Path dirPath = path.getParent();

            // make sure the dir exists
            if (dirPath != null && !Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // write to the file
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for (Task task : tasks) {
                    writer.write(task.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new DarkKnightException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a task from file format string.
     * Format examples:
     * [T][ ] read book
     * [D][X] essay (by: Feb 03 2026)
     * [E][ ] meeting (from: Feb 01 2026 to: Feb 05 2026)
     */
    public Task parseTaskFromFile(String line) throws DarkKnightException{
        if (line.trim().isEmpty()) {
            throw new DarkKnightException("Empty line");
        }

        String type = line.substring(1, 2);
        String status = line.substring(4, 5);
        boolean isDone = status.equals("X");

        // Extract the rest of the line (description + date info)
        String rest = line.substring(7);

        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(rest);
            break;

        case "D":
            int byIndex = rest.indexOf("(by: ");
            String description = rest.substring(0, byIndex).trim();
            String dateStr = rest.substring(byIndex + 5, rest.length() - 1);

            java.time.format.DateTimeFormatter formatter =
                    java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy", java.util.Locale.ENGLISH);
            LocalDate by = LocalDate.parse(dateStr, formatter);

            task = new Deadline(description, by);
            break;

        case "E":
            // Format: [E][ ] meeting (from: Feb 01 2026 to: Feb 05 2026)
            int fromIndex = rest.indexOf("(from: ");
            String eventDesc = rest.substring(0, fromIndex).trim();

            int toIndex = rest.indexOf(" to: ");

            String fromStr = rest.substring(fromIndex + 7, toIndex);
            String toStr = rest.substring(toIndex + 5, rest.length() - 1);

            // Parse dates in "MMM dd yyyy" format
            java.time.format.DateTimeFormatter eventFormatter =
                    java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy", java.util.Locale.ENGLISH);
            LocalDate from = LocalDate.parse(fromStr, eventFormatter);
            LocalDate to = LocalDate.parse(toStr, eventFormatter);

            task = new Event(eventDesc, from, to);
            break;

        default:
            throw new DarkKnightException("What is this type: " + type);
        }

        if (isDone) {
           task.mark();
        }
        return task;
    }

}
