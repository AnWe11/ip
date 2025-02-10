package tasks;

import exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;

/**
 * Prints list of tasks.
 * Adds / removes tasks from list of tasks.
 */
public class  TaskManager {
    private List<TasksDefault> tasksList;

    public TaskManager() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Lists all the tasks in the list by iterating through the list.
     */
    public String listTasks() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:");
        for(int i = 0; i < tasksList.size(); i++) {
            str.append(i+1).append(". ").append(tasksList.get(i).getDescription()).append("\n");
        }
        return str.toString();
    }

    public TasksDefault getTask(int taskID) throws InvalidCommandException {
        return tasksList.get(taskID - 1);
    }

    public String addTask(TasksDefault task) {
        tasksList.add(task);
        StringBuilder str = new StringBuilder();
        str.append(task.getDescription()).append("\n").append("You currently have ").append(tasksList.size())
                .append(" task(s) in the list.");
        return str.toString();
    }

    public void loadTask(TasksDefault task) {
        tasksList.add(task);
    }

    public int getTotalTasks() {
        return tasksList.size();
    }

    public String removeTask(int taskID) throws InvalidCommandException {
        String str = "Ok , I've deleted this task:" + getTask(taskID - 1).getDescription();
        tasksList.remove(taskID - 1);
        return str;
    }

    public List<TasksDefault> getTasksList() {
        return tasksList;
    }

    /**
     * Get the remaining number of tasks by iterating through the list and finding out which tasks are still unmarked.
     */
    public void getRemainingTasks() {
        int remainingTasks = 0;
        StringBuilder str = new StringBuilder();

        for(TasksDefault entry : this.tasksList) {
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
