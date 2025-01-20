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
            bye, list, mark, unmark
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
        String goodbye = "\nGoodbye! Hope to see you again soon!"+
                "\n_____________________________________________";

        System.out.println(luigiLogo);

        System.out.println(greeting);

        do {
            //Reads input from user and decides what to do
            input = scanner.nextLine();
            //Splits input string
            String[] inputArray = input.split(" ");
            //Initialisation of taskID and currTask which will only be initialised if the command is mark or unmark
            int taskID = -1;
            Tasks currTask = null;
            /*
            Check if the first word of the input is mark or unmark, if it is, it means that there already exists a
            task. Then initialise the currTask to that task
            */
            if (inputArray[0].equalsIgnoreCase("mark") || inputArray[0].equalsIgnoreCase("unmark")) {
                try {
                    taskID = Integer.parseInt(inputArray[1]) - 1;
                    if (taskID >= 0 && taskID < tasksList.size()) {
                        currTask = tasksList.get(taskID);
                    } else {
                        System.out.println("Invalid task ID.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid taskID format.");
                }

                currTask = tasksList.get(taskID);
            }

            //Switch statements to handle the different commands from the enum table
            switch(inputArray[0].toLowerCase()) {
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
                    if (currTask != null) {
                        currTask.markAsDone();
                    }
                    System.out.println(currTask.getDescription());
                    System.out.println("\n_____________________________________________\n");
                    break;
                case "unmark":
                    System.out.println("\n_____________________________________________");
                    System.out.println("Ok , I've unmarked this task:");
                    if (currTask != null) {
                        currTask.unmark();
                    }
                    System.out.println(currTask.getDescription());
                    System.out.println("\n_____________________________________________\n");
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
