package commands;

import exceptions.InvalidCommandException;
import tasks.TaskManager;
import tasks.TasksDefault;

import java.util.ArrayList;
import java.util.List;

/**
 * Method call if command word is 'find', prints the tasks in the task list that contains the matching keyword.
 */
public class FindCase implements DefaultCase {

    private TaskManager taskManager;
    private String input;

    public FindCase(String input, TaskManager taskManager) {
        this.taskManager = taskManager;
        this.input = input;
    }

    /**
     * Method call to print list of tasks that contains the keyword.
     * Checks if keyword is valid.
     * Prints the tasks in the current list that contains the keyword in their task description.
     */
    @Override
    public void action() throws InvalidCommandException {
        try {
            List<TasksDefault> findTasksList = new ArrayList<TasksDefault>();
            StringBuilder str = new StringBuilder();

            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("You did not specify a keyword to find :<");
            }

            String keyword = input.substring(firstSpaceIndex + 1);

            for (TasksDefault task : taskManager.getTasksList()) {
                if (task.getTaskDescription().contains(keyword)) {
                    findTasksList.add(task);
                }
            }

            System.out.println("_____________________________________________");
            if (!findTasksList.isEmpty()) {
                System.out.println("Here are the tasks matching the keyword " + keyword + ":");
                for (int i = 0; i < findTasksList.size(); i++) {
                    str.append(i+1).append(".").append(findTasksList.get(i).getDescription()).append("\n");
                }
            } else {
                str.append("No tasks matching the keyword '").append(keyword).append("' was found.").append("\n");
            }
            str.append("_____________________________________________\n");
            System.out.println(str);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }

    }
}