package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void deadlinesGetDescriptionTest() {
        assertEquals("[D][ ] return books (by: Jan 29 2025)", new Deadlines("return books",
                                                                        "2025-01-29").getDescription());
    }

    @Test
    public void deadlinesGetDescriptionMarkedTest() {
        Deadlines temp = new Deadlines("return books", "2025-01-29");
        temp.markAsDone();
        assertEquals("[D][X] return books (by: Jan 29 2025)", temp.getDescription());
    }
}
