package ui;

/**
 * UI design of the Luigi Chatbot.
 */
public class LuigiUI {

    //ASCII Art of Chatbot's name
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

    public LuigiUI() {
    }

    public void printUI() {
        System.out.println(luigiLogo + "\n" + greeting + "\n");
    }
}
