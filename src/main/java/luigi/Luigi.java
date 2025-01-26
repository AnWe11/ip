package luigi;

import commands.CommandsParser;
import exceptions.InvalidCommandException;
import tasks.TaskManager;

import java.util.Scanner;

public class Luigi {
    public static void main(String[] args) throws InvalidCommandException {

        //Initialisation of Scanner to read inputs from user
        Scanner scanner = new Scanner(System.in);

        //Initialisation of input from user
        String input;

        TaskManager taskManager = new TaskManager();

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

        System.out.println(luigiLogo);
        System.out.println(greeting);

        do {
            //Reads input from user and decides what to do
            input = scanner.nextLine();
            CommandsParser command = new CommandsParser(input, taskManager);
        } while (!input.equalsIgnoreCase("bye"));
    }

}
