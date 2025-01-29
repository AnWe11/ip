package commands;
import tasks.TaskManager;

/**
 * Method call if command word is 'list', calls the listTask() method from TaskManager class.
 */
public class ListCase implements DefaultCase {

    TaskManager taskManager;

    /**
     * ListCase instance.
     * @param taskManager TaskManager class instance.
     */
    public ListCase(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Calls the listTasks method from TaskManager class.
     */
    @Override
    public void action() {
       this.taskManager.listTasks();
    }
}
