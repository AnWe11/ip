package storage;
import datetimeutility.DateTimeConversion;
import exceptions.InvalidDateException;
import tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Data {

    File file;

    public Data() {
        String fileName = "taskData.txt";
        this.file = new File(fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("\nFirst time using Luigi, data storage created\n");
            } else {
                System.out.println("\nWelcome back! Your previous data has already been loaded\n" +
                                        "Type 'list' to view your saved tasks\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }
    }

    public void saveData(TaskManager taskManager) throws IOException {

        try {
            StringBuilder textToAppend = new StringBuilder();
            for (TasksDefault entry : taskManager.getTasksList()) {

                String taskLine = entry.getTaskType() + " | " + (entry.isDone() ? "1" : "0") + " | " + entry.getTaskDescription();
                if (entry.getTaskType().equals("[D]") || entry.getTaskType().equals("[E]")) {
                    taskLine += " | " + entry.getDeadlineDate();
                }
                taskLine += "\n";
                textToAppend.append(taskLine);
            }
            FileWriter fw = new FileWriter("taskData.txt");
            fw.write(textToAppend.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public void loadData(TaskManager taskManager) throws IOException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] taskLineArray = s.nextLine().split(" \\| ");
                String taskType = taskLineArray[0];
                boolean taskDone = taskLineArray[1].equals("1");
                String taskDescription = taskLineArray[2];

                if (taskType.equals("[T]")) {
                    ToDo todoTask = new ToDo(taskDescription);
                    if (taskDone) {
                        todoTask.markAsDone();
                    }
                    taskManager.loadTask(todoTask);
                } else if (taskType.equals("[D]")) {
                    String deadlineDate = DateTimeConversion.loadDateTime(taskLineArray[3]);
                    Deadlines deadlineTask = new Deadlines(taskDescription, deadlineDate);
                    if (taskDone) {
                        deadlineTask.markAsDone();
                    }
                    taskManager.loadTask(deadlineTask);
                } else if (taskType.equals("[E]")) {
                    String deadlineDate =  taskLineArray[3];
                    String[] fromToArray = deadlineDate.split(" - ");
                    String from = DateTimeConversion.loadDateTime(fromToArray[0]);
                    String to = DateTimeConversion.loadDateTime(fromToArray[1]);
                    Events eventsTask = new Events(taskDescription, from, to);
                    if (taskDone) {
                        eventsTask.markAsDone();
                    }
                    taskManager.loadTask(eventsTask);
                } else {
                    throw new IOException(taskType + " is not a valid task");
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (InvalidDateException e) {
            System.out.println("Invalid date / date format " + e.getMessage());
        }
    }

}
