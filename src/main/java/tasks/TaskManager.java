package tasks;

import exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        return "Here are the tasks in your list: \n" +
                tasksList.stream().map(TasksDefault::getDescription)
                        .collect(Collectors.joining("\n"));
    }

    public TasksDefault getTask(int taskID) throws InvalidCommandException {
        return tasksList.get(taskID - 1);
    }

    public String addTask(TasksDefault task) {
        tasksList.add(task);
        StringBuilder str = new StringBuilder();
        str.append("I have added: ");
        str.append(task.getDescription()).append("to the list").append("\n").
                append("You currently have ").append(tasksList.size()).append(" task(s) in the list.");
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
    public String getRemainingTasks() {

        int tasksDone = (int) tasksList.stream()
                                       .filter(TasksDefault::isDone)
                                       .count();

        int remainingTasks = tasksList.size() - tasksDone;

        return remainingTasks == 0
                ? "Congratulations! You have completed all your tasks! :)"
                : "You have " + remainingTasks + " tasks left to complete!";

    }
}
