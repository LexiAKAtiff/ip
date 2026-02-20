package darkknight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    private final Path path;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy", java.util.Locale.ENGLISH);

    public Storage(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null");
        }

        try {
            path = Paths.get(filePath); // validate path at construction time
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Invalid file path: " + filePath);
        }

        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file
     * @return tasks list
     */
    public ArrayList<Task> load() throws DarkKnightException {
        ArrayList<Task> tasks = new ArrayList<>();

        // If dir does not exist then return an empty list
        if (!Files.exists(path)) {
            return tasks;
        }

        // read from the file
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromFile(line);
                tasks.add(task);
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
        Path dirPath = path.getParent();

        // write to the file
        try {
            // make sure the dir exists
            if (dirPath != null && !Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

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
     * Parses the date
     */
    public LocalDate parseDate(String dateStr) throws DarkKnightException {
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new DarkKnightException("Invalid date in file: " + dateStr);
        }
    }


    /**
     * Parses a todo task
     */
    public Task parseTodo(String rest) {
        return new Todo(rest);
    }

    /**
     * Parses a deadline task
     */
    public Task parseDeadline(String rest) throws DarkKnightException {
        // AI-assisted fix: catch StringIndexOutOfBoundsException from malformed lines
        try {
            int byIndex = rest.indexOf("(by: ");
            if (byIndex == -1) {
                throw new DarkKnightException("Corrupted deadline format: " + rest);
            }
            String description = rest.substring(0, byIndex).trim();
            String dateStr = rest.substring(byIndex + 5, rest.length() - 1);

            LocalDate by = parseDate(dateStr);

            return new Deadline(description, by);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DarkKnightException("Corrupted deadline entry: " + rest);
        }
    }

    /**
     * Parses an event task
     */
    public Task parseEvent(String rest) throws DarkKnightException {
        // Format: [E][ ] meeting (from: Feb 01 2026 to: Feb 05 2026)
        // AI-assisted fix: catch StringIndexOutOfBoundsException from malformed lines
        try {
            int fromIndex = rest.indexOf("(from: ");
            int toIndex = rest.indexOf(" to: ");
            if (fromIndex == -1 || toIndex == -1) {
                throw new DarkKnightException("Corrupted event format: " + rest);
            }
            String eventDesc = rest.substring(0, fromIndex).trim();

            String fromStr = rest.substring(fromIndex + 7, toIndex);
            String toStr = rest.substring(toIndex + 5, rest.length() - 1);

            LocalDate from = parseDate(fromStr);
            LocalDate to = parseDate(toStr);

            return new Event(eventDesc, from, to);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DarkKnightException("Corrupted event entry: " + rest);
        }
    }

    /**
     * Parses a task from file format string.
     * Format examples:
     * [T][ ] read book
     * [E][X] meeting (from: Feb 01 2026 to: Feb 05 2026)
     */
    public Task parseTaskFromFile(String line) throws DarkKnightException {
        if (line.trim().isEmpty()) {
            throw new DarkKnightException("Empty line");
        }

        // AI-assisted fix: guard against lines too short to parse
        if (line.length() < 7) {
            throw new DarkKnightException("Corrupted task line: " + line);
        }
        String type = line.substring(1, 2);
        String status = line.substring(4, 5);
        boolean isDone = status.equals("X");

        // Extract the rest of the line (description + date info)
        String rest = line.substring(7);

        Task task;
        switch (type) {
        case "T":
            task = parseTodo(rest);
            break;

        case "D":
            task = parseDeadline(rest);
            break;

        case "E":
            task = parseEvent(rest);
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
