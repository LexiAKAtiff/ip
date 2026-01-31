package darkknight;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    private Todo todo;

    /**
     * Runs this before every Test
     */
    @BeforeEach
    public void setUp() {
        todo = new Todo("read book");
    }

    /**
     * Tests constructor and getName method
     */
    @Test
    public void testConstructor_success() {
        assertEquals("read book", todo.getName());
        assertFalse(todo.isDone());
    }

    /**
     * Tests mark method
     */
    @Test
    public void testMark_success() {
        assertFalse(todo.isDone());
        todo.mark();
        assertTrue(todo.isDone());
    }
}
