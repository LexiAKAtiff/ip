package darkknight;

import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Adds a task to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in the list
     */
    public Task deleteTask(int index) throws DarkKnightException{
        if (index < 0 || index >= tasks.size()) {
            throw new DarkKnightException("I can't find this task!");
        }
        return tasks.remove(index);
    }

    /**
     * Gets a task at the specified index
     */
    public Task getTask(int index) throws DarkKnightException {
        if (index < 0 || index >= tasks.size()) {
            throw new DarkKnightException("I can't find this task.");
        }
        return tasks.get(index);
    }

    /**
     * Marks a task as done
     */
    public void markTask(int index) throws DarkKnightException {
        Task task = getTask(index);
        task.mark();
    }

    /**
     * Unmarks a task
     */
    public void unmarkTask(int index) throws DarkKnightException {
        Task task = getTask(index);
        task.unmark();
    }

    /**
     * Gets all tasks in the list
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
