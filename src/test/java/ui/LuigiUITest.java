package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LuigiUITest {

    String luigiLogo = "_____________________________________________\n" +
            " _        _    _    _____    _____    _____  \n" +
            "| |      | |  | |  |_   _|  / ____|  |_   _| \n" +
            "| |      | |  | |    | |   | |   _     | |   \n" +
            "| |      | |  | |    | |   | |  |_|    | |   \n" +
            "| |____  | |__| |   _| |_  | |__| |   _| |_  \n" +
            "|______|  \\____/   |_____|  \\_____|  |_____| \n" +
            "_____________________________________________";

    //Greeting string of Chatbot
    String greeting = "Hello! I am Luigi!\nWhat can I do for you?\n" +
            "_____________________________________________";

    String expected = luigiLogo + "\n" + greeting;

//    @Test
//    public void luigiUITest() {
//        assertEquals(expected, new LuigiUI().printUI());
//    }
}
