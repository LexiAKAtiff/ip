package darkknight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Deadline task class
 */
public class Deadline extends Task {

    //for input format
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //for output format
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy",
            Locale.ENGLISH);

    private LocalDate by;

    /**
     * Deadline constructor
     * @param by is the deadline of this task
     */
    public Deadline(String description, String by) throws DarkKnightException {
        super(description);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DarkKnightException("Invalid date format! Please use yyyy-MM-dd format (e.g., 2019-12-02)");
        }
    }

    /**
     * Used to construct deadline when read from file
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline time
     */
    public LocalDate getTime() {
        return by;
    }


    /**
     * Returns the deadline time in certain output format
     */
    public String getByString() {
        return this.by.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + getByString() + ")");
    }

}
