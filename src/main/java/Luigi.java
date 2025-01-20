import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Luigi {
    public static void main(String[] args) {

        //Initialisation of Scanner to read inputs from user
        Scanner scanner = new Scanner(System.in);

        //Initialisation of input from user
        String input;

        //Initialisation of command words in the form of enum
        enum Commands {
            bye, list
        }

        //Initialisation of List array to store tasks elements
        List<Tasks> tasksList = new ArrayList<Tasks>();

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
        do {
            input = scanner.nextLine();

            switch(input) {
                case "bye":
                    System.out.println(goodbye);
                    break;
                case "list":
                    listTasks(tasksList);
                    break;
                default:
                    Tasks task = new Tasks(input);
                    printDescription(task.getDescription());
                    tasksList.add(task);
            }
        } while (!input.equalsIgnoreCase("bye"));
    }

    //Method to print task description
    public static void printDescription(String description) {
        System.out.println(
                "\n_____________________________________________\n"
                + "added: " + description + "\n"
                + "_____________________________________________");
    }

    //Method to display items in List in sequential order
    public static void listTasks(List<Tasks> tasksList) {
        int count = 0;
        StringBuilder str = new StringBuilder();
        str.append("\n_____________________________________________\n");

        for(Tasks entry : tasksList) {
            count++;
            str.append(count).append(". ").append(entry.getDescription()).append("\n");
        }
        str.append("_____________________________________________\n");
        System.out.println(str.toString());
    }
}
