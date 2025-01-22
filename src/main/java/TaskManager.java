import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Tasks> tasksList;

    public TaskManager() {
        this.tasksList = new ArrayList<>();
    }

    public void listTasks() {
        StringBuilder str = new StringBuilder();
        str.append("\n_____________________________________________\n");
        for(int i = 0; i < tasksList.size(); i++) {
            str.append(i+1).append(". ").append(tasksList.get(i).getDescription()).append("\n");
        }
        str.append("_____________________________________________\n");
        System.out.println(str.toString());
    }

    public Tasks getTask(int taskID) throws InvalidCommandException {
        return tasksList.get(taskID - 1);
    }

    public void addTask(Tasks task) {
        tasksList.add(task);
    }

    public int getTotalTasks() {
        return tasksList.size();
    }

    public void removeTask(int taskID) {
        tasksList.remove(taskID - 1);
    }

    public void getRemainingTasks() {
        int remainingTasks = 0;
        StringBuilder str = new StringBuilder();

        for(Tasks entry : this.tasksList) {
            if (entry.isDone()) {
                remainingTasks++;
            }
        }
        remainingTasks = tasksList.size() - remainingTasks;
        if (remainingTasks == 0) {
            str.append("Congratulations! You have completed all your tasks! :)");
        } else {
            str.append("You have ").append(remainingTasks).append(" tasks left to complete!");
        }
        System.out.println(str.toString());
    }
}
