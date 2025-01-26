package commands;

import exceptions.InvalidCommandException;
import tasks.Events;
import tasks.TaskManager;

public class EventsCase implements DefaultCase {
    private TaskManager taskManager;
    private String input;

    public EventsCase(String input, TaskManager taskManager) {
        this.taskManager = taskManager;
        this.input = input;
    }

    @Override
    public void action() throws InvalidCommandException {
        String taskDescription;
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException(":((((((((( You did not include a task description");
            }
            taskDescription = input.substring(firstSpaceIndex + 1);

            if (!(taskDescription.contains("/from") && taskDescription.contains("/to"))) {
                throw new InvalidCommandException("The schedule timing is missing! ._.");
            }

            String[] parts = taskDescription.split("/from", 2);
            taskDescription = parts[0];
            String[] fromToString = parts[1].split("/to", 2);
            String fromString = fromToString[0];
            String toString = fromToString[1];
            Events eventsTask = new Events(taskDescription, fromString, toString);
            taskManager.addTask(eventsTask);
            System.out.println("_____________________________________________\n" + "Sure thing! I've added this task: ");
            System.out.println(eventsTask.getDescription());
            System.out.println("You currently have " + this.taskManager.getTotalTasks() + " task(s) in the list.");
            System.out.println("_____________________________________________");
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for events tasks should be\nevent <Task Description> /from <from date> /to <to date>");
            System.out.println("_____________________________________________");
            taskDescription = "";
        }
    }

}
