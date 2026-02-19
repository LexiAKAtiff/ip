package darkknight;

import java.util.ArrayList;

/**
 * Implements the archive feature
 */
public class Archive {
    private Storage storage;
    private TaskList tasks;

    public Archive(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DarkKnightException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Moves a task to archive
     */
    public void addToArchive(Task task) throws DarkKnightException {
        tasks.addTask(task);
        storage.save(tasks.getAllTasks());
    }

    /**
     * Moves a task out of archive
     */
    public void unarchive(int index) throws DarkKnightException{
        tasks.deleteTask(index);
        storage.save(tasks.getAllTasks());
    }

    /**
     * Gets a task with index
     */
    public Task getTask(int index) throws DarkKnightException {
        return tasks.getTask(index);
    }

    /**
     * Gets all the tasks from the archive
     */
    public TaskList getTasklist() {
        return tasks;
    }

}
