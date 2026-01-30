package darkknight;

import java.time.LocalDate;

public class Parser {
    public void parseCommand(String fullCommand, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "list":
            handleList(tasks,ui);
            break;
        case "todo":
            handleTodo(parts, tasks, ui, storage);
            break;
        case "deadline":
            handleDeadline(parts, tasks, ui, storage);
            break;
        case "event":
            handleEvent(parts, tasks, ui, storage);
            break;
        case "mark":
            handleMark(parts, tasks, ui, storage);
            break;
        case "unmark":
            handleUnmark(parts, tasks, ui, storage);
            break;
        case "delete":
            handleDelete(parts, tasks, ui, storage);
            break;
        default:
            throw new DarkKnightException("I can't perform this task, it's beyond my capability!");
        }
    }

    /**
     * Checks if list is empty when it's "list" command，
     * if not then prints
     */
    private void handleList(TaskList tasks, Ui ui) throws DarkKnightException {
        if (tasks.isEmpty()) {
            throw new DarkKnightException("There is nothing in your list!");
        }
        ui.printList(tasks.getAllTasks());
    }

    /**
     * Handles the "todo" command
     */
    private void handleTodo(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException{
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DarkKnightException("Missing description for the todo!");
        }
        String description = parts[1];
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        ui.showTaskAdded(todo, tasks.size());
        storage.save(tasks.getAllTasks());
    }

    /**
     * Handles "deadline" command
     */
    private void handleDeadline(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("Missing description and time for the deadline!");
        }

        String[] otherParts = parts[1].split(" /by ");
        if (otherParts.length != 2 || otherParts[0].trim().isEmpty()
                || otherParts[1].trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for deadline!");
        }
        String description = otherParts[0].trim();
        String by = otherParts[1].trim();

        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showTaskAdded(deadline, tasks.size());
        storage.save(tasks.getAllTasks());
    }

    /**
     * Handles the "event" command
     */
    private void handleEvent(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("Missing description and time for the event!");
        }

        String[] otherParts = parts[1].split("/from");

        if (otherParts.length != 2 || otherParts[0].trim().isEmpty()
                || otherParts[1].trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for event!");
        }

        String description = otherParts[0];
        String[] rest = otherParts[1].split(" /to ");
        if (rest.length != 2 || rest[0].trim().isEmpty()
                || rest[1].trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for event!");
        }

        String from = rest[0].trim();
        String to = rest[1].trim();

        Event event = new Event(description, from, to);
        tasks.addTask(event);
        ui.showTaskAdded(event, tasks.size());
        storage.save(tasks.getAllTasks());
    }

    /**
     * Handles the mark command
     */
    private void handleMark(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("I don't know which one to mark.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            tasks.markTask(index);
            Task task = tasks.getTask(index);
            ui.showTaskMarked(task);
            storage.save(tasks.getAllTasks());
        } catch (NumberFormatException e) {
            throw new DarkKnightException(parts[1] + " is not a valid number.");
        }
    }

    /**
     * Handles the unmark command.
     */
    private void handleUnmark(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("I don't know which one to unmark.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            tasks.unmarkTask(index);
            Task task = tasks.getTask(index);
            ui.showTaskUnmarked(task);
            storage.save(tasks.getAllTasks());
        } catch (NumberFormatException e) {
            throw new DarkKnightException(parts[1] + " is not a valid number.");
        }
    }

    /**
     * Handles the delete command
     */
    private void handleDelete(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("I don't know which one to delete.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.deleteTask(index);
            ui.showTaskDeleted(task, tasks.size());
            storage.save(tasks.getAllTasks());
        } catch (NumberFormatException e) {
            throw new DarkKnightException(parts[1] + " is not a valid number.");
        }
    }

}
