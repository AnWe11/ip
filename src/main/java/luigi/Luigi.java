package luigi;

import commands.CommandsParser;
import exceptions.InvalidCommandException;
import storage.Data;
import tasks.TaskManager;
import ui.LuigiUI;

import java.io.IOException;
import java.util.Scanner;

/**
 * Initiates the Luigi Chatbot.
 */
public class Luigi {

    private static TaskManager taskManager;
    private static Data data;
    private static LuigiUI luigiUI;

    public Luigi() throws IOException {

        this.taskManager = new TaskManager();
        this.data = new Data();
        this.luigiUI = new LuigiUI();
        data.loadData(taskManager);
    }

    public static String getLuigiWelcomeUI() throws IOException {
        return luigiUI.printUI();
    }

    public static String getResponse(String userInput) throws InvalidCommandException, IOException {
        String responseString = "";
        
        try {
            CommandsParser command = new CommandsParser(userInput, taskManager, data);
            responseString = command.getResponseString();
        } catch (InvalidCommandException e) {
            System.out.println("Invalid Command Exception in main" + e.getMessage());
            responseString = "Invalid Command Exception in main" + e.getMessage();
        }
        return responseString;
    }

}
