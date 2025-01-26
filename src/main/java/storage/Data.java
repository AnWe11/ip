package storage;
import tasks.TasksDefault;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Data {

    public Data() {
        String fileName = "taskData.txt";
        File file = new File(fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("First time using Luigi, data storage created");
            } else {
                System.out.println("Welcome back! Your previous data has already been loaded\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }
    }

    public void saveData(TasksDefault task) throws IOException {

        String textToAppend;

        textToAppend = task.getTaskType() + " | " + (task.isDone() ? "1" : "0") + " | " + task.getTaskDescription();
        if (task.getTaskType().equals("[D]") || task.getTaskType().equals("[E]")) {
            textToAppend += " | " + task.getDeadlineDate();
        }

        try {
            FileWriter fw = new FileWriter("taskData.txt", true);
            fw.write(textToAppend + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

}
