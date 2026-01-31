package darkknight;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;

/**
 * JUnit test class for TaskList.
 */
public class TaskListTest {
    private TaskList taskList;
    private Todo todo;
    private Deadline deadline;

    /**
     * Runs before each test to set up the initials
     * (I got this idea from AI)
     */
    @BeforeEach
    public void setup() {
        taskList = new TaskList();
        todo = new Todo("read a book");
        deadline = new Deadline("return book", LocalDate.of(2026, 2, 1));
    }

    /**
     * Tests adding new task
     */
    @Test
    public void testAddTask_success() {
        taskList.addTask(todo);
        assertEquals(1, taskList.size());
    }

    @Test
    public void testDeleteTask_success() throws DarkKnightException {
        taskList.addTask(todo);
        taskList.addTask(deadline);

        assertEquals(todo, taskList.deleteTask(0));
        assertEquals(1, taskList.size());
    }
}
