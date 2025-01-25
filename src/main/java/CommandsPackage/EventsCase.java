package CommandsPackage;

import Exceptions.InvalidCommandException;
import TaskPackage.Events;
import TaskPackage.TaskManager;

public class EventsCase implements DefaultCase {
    private String taskDescription;
    private TaskManager taskManager;
    private String input;
    private String fromString;
    private String toString;

    public EventsCase(String input, TaskManager taskManager) {
        this.taskManager = taskManager;
        this.input = input;
    }

    @Override
    public void action() throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException(":((((((((( You did not include a task description");
            }
            this.taskDescription = input.substring(firstSpaceIndex + 1);

            if (!(this.taskDescription.contains("/from") && this.taskDescription.contains("/to"))) {
                throw new InvalidCommandException("The schedule timing is missing! ._.");
            }

            String[] parts = this.taskDescription.split("/from", 2);
            this.taskDescription = parts[0];
            String[] fromToString = parts[1].split("/to", 2);
            fromString = fromToString[0];
            toString = fromToString[1];
            Events eventsTask = new Events(this.taskDescription, this.fromString, this.toString);
            taskManager.addTask(eventsTask);
            System.out.println("_____________________________________________\n" + "Sure thing! I've added this task: ");
            System.out.println(eventsTask.getDescription());
            System.out.println("You currently have " + this.taskManager.getTotalTasks() + " task(s) in the list.");
            System.out.println("\n_____________________________________________\n");
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for events tasks should be\nevent <Task Description> /from <from date> /to <to date>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
        }
    }

}
