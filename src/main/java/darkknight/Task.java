package darkknight;

//this darkknight Task code is partly from iP document about extension: A-Classes
public abstract class Task {
    private boolean isDone;
    private final String name;

    public Task(String description) {
        this.name = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks a task
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the icon specifying whether a task is done
     * "X" for done, blank space for not done
     */
    public String getIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task (the name of the task)
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a boolean specifying whether a task is done
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return ("[" + getIcon() + "] " + name);
    }
}
