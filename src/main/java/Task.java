//this Task code is partly from iP document about extension: A-Classes
public class Task {
    boolean status;
    String name;

    public Task(String description) {
        this.name = description;
        this.status = false;
    }

    public void mark() {
        status = true;
    }
    public void unmark() {
        status = false;
    }

    public String getIcon() {
        return (status ? "X" : " ");
    }

    public String getName() {
        return name;
    }
}
