package CommandsPackage;
import TaskPackage.TaskManager;

public class ListCase implements DefaultCase {

    TaskManager taskManager;

    public ListCase(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void action() {
       this.taskManager.listTasks();
    }
}
