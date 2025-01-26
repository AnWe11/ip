package CommandsPackage;

import Exceptions.InvalidCommandException;
import TaskPackage.TaskManager;
import TaskPackage.ToDo;

public class ToDoCase implements DefaultCase {
    private TaskManager taskManager;
    private String input;

    public ToDoCase(String input, TaskManager taskManager) {
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
                throw new InvalidCommandException("Oops! The task description of todo cannot be empty :(");
            }

            taskDescription = input.substring(firstSpaceIndex + 1);
            ToDo todoTask = new ToDo(taskDescription);
            taskManager.addTask(todoTask);
            System.out.println("_____________________________________________\n" + "Sure thing! I've added this task: ");
            System.out.println(todoTask.getDescription());
            System.out.println("You currently have " + this.taskManager.getTotalTasks() + " task(s) in the list.");
            System.out.println("_____________________________________________");
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for todo tasks should be\ntodo <Task Description>");
            System.out.println("_____________________________________________");
            taskDescription = "";
        }
    }
}
