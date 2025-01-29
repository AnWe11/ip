package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void todoGetDescriptionTest() {
        assertEquals("[T][ ] return books", new ToDo("return books").getDescription());
    }

    @Test
    public void todoGetDescriptionMarkedTest() {
        ToDo temp = new ToDo("return books");
        temp.markAsDone();
        assertEquals("[T][X] return books", temp.getDescription());
    }
}
