import java.lang.reflect.Array;
import java.util.ArrayList;

public class Deadline extends Task{
    String deadLine;

    public Deadline(String description, String deadLine) {
        super(description);
        this.deadLine = deadLine;
    }

    public String getTime() {
        return deadLine;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + deadLine + ")");
    }

}
