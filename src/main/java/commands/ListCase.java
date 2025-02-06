package commands;
import tasks.TaskManager;

/**
 * Prints the list of tasks.
 */
public class ListCase implements DefaultCase {

    TaskManager taskManager;

    public ListCase(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Prints the list of tasks.
     */
    @Override
    public String action() {
       return this.taskManager.listTasks();
    }
}
