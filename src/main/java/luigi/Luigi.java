package luigi;

import commands.CommandsParser;
import exceptions.InvalidCommandException;
import storage.Data;
import tasks.TaskManager;
import ui.LuigiUI;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class that initiates the Luigi Chatbot.
 */
public class Luigi {

    public static void main(String[] args) throws InvalidCommandException, IOException {

        try {
            LuigiUI luigiUI = new LuigiUI();
            TaskManager taskManager = new TaskManager();
            Data data = new Data();
            data.loadData(taskManager);

            Scanner scanner = new Scanner(System.in);
            String input;

            luigiUI.printUI();

            while(true) {
                //Reads input from user and decides what to do
                input = scanner.nextLine();
                CommandsParser command = new CommandsParser(input, taskManager, data);
            }
        } catch (InvalidCommandException e) {
            System.out.println("Invalid Command Exception in main" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception in main" + e.getMessage());
        }

    }

}
