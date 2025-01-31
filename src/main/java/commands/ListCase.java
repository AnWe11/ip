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
    public void action() {
       this.taskManager.listTasks();
    }
}
