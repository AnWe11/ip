import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Luigi {
    public static void main(String[] args) {

        //Initialisation of command words in the form of enum
        enum Keywords {
            bye, list, mark, unmark
        }

        //Initialisation of Scanner to read inputs from user
        Scanner scanner = new Scanner(System.in);

        //Initialisation of input from user
        String input;

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
        String goodbye = "\nGoodbye! Hope to see you again soon!"+
                "\n_____________________________________________";

        System.out.println(luigiLogo);

        System.out.println(greeting);

        do {
            //Reads input from user and decides what to do
            input = scanner.nextLine();
            Commands command = new Commands(input, tasksList);

            //Switch statements to handle the different commands from the enum table
            switch(command.getCommandWord()) {
                case "bye":
                    System.out.println(goodbye);
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    listTasks(tasksList);
                    break;
                case "mark":
                    System.out.println("\n_____________________________________________");
                    System.out.println("Nice work! I've marked this task as done:");
                    command.getCurrTask().markAsDone();
                    System.out.println(command.getCurrTask().getDescription());
                    System.out.println(" ");
                    System.out.println(command.getCurrTask().getRemainingTasks());
                    System.out.println("_____________________________________________\n");
                    break;
                case "unmark":
                    System.out.println("\n_____________________________________________");
                    System.out.println("Ok , I've unmarked this task:");
                    command.getCurrTask().unmark();
                    System.out.println(command.getCurrTask().getDescription());
                    System.out.println(" ");
                    System.out.println(command.getCurrTask().getRemainingTasks());
                    System.out.println("_____________________________________________\n");
                    break;
                default:
                    Tasks task = new Tasks(input);
                    printDescription(task);
                    tasksList.add(task);
            }
        } while (!input.equalsIgnoreCase("bye"));
    }

    //Method to print task description
    public static void printDescription(Tasks task) {
        System.out.println("_____________________________________________\n" + "added: ");
        System.out.println(task.getDescription());
        System.out.println("\n_____________________________________________\n");
    }

    //Method to display items in List in sequential order
    public static void listTasks(List<Tasks> tasksList) {
        StringBuilder str = new StringBuilder();
        str.append("\n_____________________________________________\n");
        for(Tasks entry : tasksList) {
            str.append(entry.getListDescription());
        }
        str.append("_____________________________________________\n");
        System.out.println(str.toString());
    }
}
