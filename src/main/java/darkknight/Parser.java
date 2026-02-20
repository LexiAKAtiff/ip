package darkknight;

/**
 * Manages the process of understanding user input
 */
public class Parser {

    /**
     * Parses the user input and decides which method to call
     */
    public String parseCommand(String fullCommand, TaskList tasks, Ui ui,
            Storage storage, Archive archive) throws DarkKnightException {
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "list":
            return handleList(tasks, ui);
        case "todo":
            return handleTodo(parts, tasks, ui, storage);
        case "deadline":
            return handleDeadline(parts, tasks, ui, storage);
        case "event":
            return handleEvent(parts, tasks, ui, storage);
        case "mark":
            return handleMark(parts, tasks, ui, storage);
        case "unmark":
            return handleUnmark(parts, tasks, ui, storage);
        case "delete":
            return handleDelete(parts, tasks, ui, storage);
        case "find":
            return handleFind(parts, tasks, ui, storage);
        case "archive":
            return handleArchive(parts, tasks, ui, storage, archive);
        case "unarchive":
            return handleUnarchive(parts, tasks, ui, storage, archive);
        case "archivelist":
            return handleArchiveList(parts, tasks, ui, storage, archive);
        case "bye":
            return ui.showGoodbye();
        default:
            throw new DarkKnightException("I can't perform this task, it's beyond my capability!");
        }
    }

    /**
     * Checks if list is empty when it's "list" command,
     * if not then prints
     */
    private String handleList(TaskList tasks, Ui ui) throws DarkKnightException {
        if (tasks.isEmpty()) {
            throw new DarkKnightException("There is nothing in your list!");
        }
        return ui.printList(tasks.getAllTasks());
    }

    /**
     * Handles the "todo" command
     */
    private String handleTodo(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DarkKnightException("Missing description for the todo!");
        }
        String description = parts[1].trim();
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks.getAllTasks());
        return ui.showTaskAdded(todo, tasks.size());
    }

    /**
     * Handles "deadline" command
     */
    private String handleDeadline(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("Missing description and time for the deadline!");
        }

        String[] otherParts = parts[1].split(" /by ");

        // Checks if description or deadline time is missing
        if (otherParts.length != 2 || otherParts[0].trim().isEmpty()
                || otherParts[1].trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for deadline!");
        }
        String description = otherParts[0].trim();
        String by = otherParts[1].trim();

        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        storage.save(tasks.getAllTasks());
        return ui.showTaskAdded(deadline, tasks.size());
    }

    /**
     * Handles the "event" command
     */
    private String handleEvent(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("Missing description and time for the event!");
        }

        String[] otherParts = parts[1].split("/from", 2);

        if (otherParts.length != 2 || otherParts[0].trim().isEmpty()
                || otherParts[1].trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for event!");
        }

        String description = otherParts[0].trim();
        String[] rest = otherParts[1].split(" /to ");
        if (rest.length != 2 || rest[0].trim().isEmpty()
                || rest[1].trim().isEmpty()) {
            throw new DarkKnightException("Wrong format for event!");
        }

        String from = rest[0].trim();
        String to = rest[1].trim();

        Event event = new Event(description, from, to);
        tasks.addTask(event);
        storage.save(tasks.getAllTasks());
        return ui.showTaskAdded(event, tasks.size());
    }

    /**
     * Handles the mark command
     */
    private String handleMark(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("I don't know which one to mark.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            tasks.markTask(index);
            Task task = tasks.getTask(index);
            storage.save(tasks.getAllTasks());
            return ui.showTaskMarked(task);
        } catch (NumberFormatException e) {
            throw new DarkKnightException(parts[1] + " is not a valid number.");
        }
    }

    /**
     * Handles the unmark command.
     */
    private String handleUnmark(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("I don't know which one to unmark.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            tasks.unmarkTask(index);
            Task task = tasks.getTask(index);
            storage.save(tasks.getAllTasks());
            return ui.showTaskUnmarked(task);
        } catch (NumberFormatException e) {
            throw new DarkKnightException(parts[1] + " is not a valid number.");
        }
    }

    /**
     * Handles the delete command
     */
    private String handleDelete(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("I don't know which one to delete.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.deleteTask(index);
            storage.save(tasks.getAllTasks());
            return ui.showTaskDeleted(task, tasks.size());
        } catch (NumberFormatException e) {
            throw new DarkKnightException(parts[1] + " is not a valid number.");
        }
    }
    /**
     * Handle "find" command
     */
    public String handleFind(String[] parts, TaskList tasks, Ui ui, Storage storage)
            throws DarkKnightException {
        TaskList relevantTasks = new TaskList();
        if (parts.length < 2) {
            throw new DarkKnightException("Key word is missing for find command!");
        }

        String keyWord = parts[1].trim();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getName().contains(keyWord)) {
                relevantTasks.addTask(task);
            }
        }

        if (relevantTasks.isEmpty()) {
            throw new DarkKnightException("There isn't a task matching your keyword!");
        }

        return ui.showMatchingList(relevantTasks);

    }

    /**
     * Handles 'Archive' command
     */
    private String handleArchive(String[] parts, TaskList tasks, Ui ui, Storage storage, Archive archive)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("I don't know which one to archive.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.getTask(index);

            // Adds the task into archive file
            archive.addToArchive(task);

            //Deletes the task from the current tasklist
            tasks.deleteTask(index);
            // AI-assisted fix: persist removal from main task list to storage
            storage.save(tasks.getAllTasks());

            return ui.showTaskArchived(task);
        } catch (NumberFormatException e) {
            throw new DarkKnightException(parts[1] + " is not a valid number.");
        }
    }

    /**
     * Handles unarchive command
     */
    private String handleUnarchive(String[] parts, TaskList tasks, Ui ui, Storage storage, Archive archive)
            throws DarkKnightException {
        if (parts.length < 2) {
            throw new DarkKnightException("I don't know which one to move out of archive.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;

            Task task = archive.getTask(index);
            archive.unarchive(index);
            // AI-assisted fix: add task back to main list and persist it
            tasks.addTask(task);
            storage.save(tasks.getAllTasks());

            return ui.showTaskUnarchived(task);
        } catch (NumberFormatException e) {
            throw new DarkKnightException(parts[1] + " is not a valid number.");
        }
    }

    /**
     * Handles archivelist command
     */
    private String handleArchiveList(String[] parts, TaskList tasks, Ui ui, Storage storage, Archive archive)
            throws DarkKnightException {
        TaskList archiveTasks =  archive.getTasklist();
        if (archiveTasks.isEmpty()) {
            throw new DarkKnightException("There is nothing in your archive!");
        }
        return ui.printArchiveList(archiveTasks.getAllTasks());
    }
}
