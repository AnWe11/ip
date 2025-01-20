import java.util.Scanner;

public class Luigi {
    public static void main(String[] args) {

        //Initialisation of Scanner to read inputs from user
        Scanner scanner = new Scanner(System.in);

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

        //Goodbye string of Chatbot
        String goodbye = "Goodbye! Hope to see you again soon!"+
                "\n_____________________________________________";

        System.out.println(luigiLogo);

        System.out.println(greeting);

        //Reads input from user and decides what to do
        while(true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("goodbye") || input.equalsIgnoreCase("bye")) {
                System.out.println(goodbye);
                break;
            }

            Tasks task = new Tasks(input);
            printDescription(task.getDescription());
        }
    }

    //Print task description
    public static void printDescription(String description) {
        System.out.println(
                "\n_____________________________________________\n"
                + description + "\n"
                + "_____________________________________________");
    }
}
