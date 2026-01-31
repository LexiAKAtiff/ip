package darkknight;

//this darkknight.Task code is partly from iP document about extension: A-Classes
public abstract class Task {
    private boolean isDone;
    private final String name;

    public Task(String description) {
        this.name = description;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getIcon() {
        return (isDone ? "X" : " ");
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return ("[" + getIcon() + "] " + name);
    }
}
