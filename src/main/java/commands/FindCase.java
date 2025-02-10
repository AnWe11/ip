package commands;

import exceptions.InvalidCommandException;
import tasks.TaskManager;
import tasks.TasksDefault;
import utility.StringChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Returns a task list with the matching keyword.
 */
public class FindCase implements DefaultCase {

    private TaskManager taskManager;
    private String input;

    public FindCase(String input, TaskManager taskManager) {
        this.taskManager = taskManager;
        this.input = input;
    }

    /**
     * Returns a list of tasks that contains the keyword.
     * If keyword is empty, throw exception.
     * @throws InvalidCommandException If keyword is not provided.
     */
    @Override
    public String action() throws InvalidCommandException {
        StringBuilder str = new StringBuilder();
        try {
            List<TasksDefault> findTasksList = new ArrayList<TasksDefault>();

            String keyword = StringChecker.checkString(input);

            for (TasksDefault task : taskManager.getTasksList()) {
                if (task.getTaskDescription().contains(keyword)) {
                    findTasksList.add(task);
                }
            }

            if (!findTasksList.isEmpty()) {
                str.append("Here are the tasks matching the keyword ").append(keyword).append(":").append("\n");
                //System.out.println("Here are the tasks matching the keyword " + keyword + ":");
                for (int i = 0; i < findTasksList.size(); i++) {
                    str.append(i+1).append(".").append(findTasksList.get(i).getDescription()).append("\n");
                }
            } else {
                str.append("No tasks matching the keyword '").append(keyword).append("' was found.").append("\n");
            }

        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            str.append(e.getMessage());
        }
        return str.toString();
    }
}