package CommandsPackage;

import Exceptions.InvalidCommandException;
import TaskPackage.TaskManager;
import TaskPackage.TasksDefault;

public class DeleteCase implements DefaultCase {
    private String taskDescription;
    private TaskManager taskManager;
    private String input;
    private int taskID;
    private TasksDefault currTask;

    public DeleteCase(String input, TaskManager taskManager) {
        this.taskManager = taskManager;
        this.input = input;
    }

    @Override
    public void action() throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("o.O You did not specify which task you would like to delete");
            }

            this.taskID = Integer.parseInt(input.substring(firstSpaceIndex + 1));
            this.currTask = taskManager.getTask(this.taskID);
            System.out.println("\n_____________________________________________");
            System.out.println("Ok , I've deleted this task:");
            System.out.println(this.currTask.getDescription());
            System.out.println("_____________________________________________\n");
            taskManager.removeTask(this.taskID);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format to delete task should be \ndelete <task ID>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
        }
    }
}
