package darkknight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task {
    LocalDate from;
    LocalDate to;

    // to parse input format
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // output format
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);

    public Event(String description, String from, String to) throws DarkKnightException {
        super(description);
        try {
            this.from = LocalDate.parse(from, INPUT_FORMATTER);
            this.to = LocalDate.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DarkKnightException("Invalid date format! Please use yyyy-MM-dd format (e.g., 2019-12-02)");
        }
    }

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFromString() {
        return this.from.format(OUTPUT_FORMATTER);
    }

    public String getToString() {
        return this.to.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + getFromString() + " to: " + getToString() + ")");
    }

}
