package commands;

import exceptions.InvalidCommandException;
import tasks.TaskManager;
import tasks.TasksDefault;
import utility.DateTimeConversion;
import utility.StringChecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        Map<String, String> parsedInput = parseInput(StringChecker.checkString(input));

        List<TasksDefault> filteredList = taskManager.getTasksList();

        if (parsedInput.containsKey("keyword")) {
            filteredList = filterTasksByKeyword(filteredList, parsedInput.get("keyword"));
        }

        if (parsedInput.containsKey("type")) {
            filteredList = filterTasksByType(filteredList, parsedInput.get("type"));
        }

        if (parsedInput.containsKey("month")) {
            filteredList = filterTasksByMonth(filteredList, parsedInput.get("month"));
        }

        return printList(filteredList);
    }

    public List<TasksDefault> filterTasksByType(List<TasksDefault> taskList, String typeOfTask) {
        switch (typeOfTask) {
        case "todo" :
            return getTasksListByType(taskList, "[T]");
        case "deadline" :
            return getTasksListByType(taskList,"[D]");
        case "event" :
            return getTasksListByType(taskList, "[E]");
        default :
            return taskList;
        }
    }

    public List<TasksDefault> getTasksListByType(List<TasksDefault> taskList, String taskType) {
        return taskList.stream().filter(task -> task.getTaskType().equals(taskType)).toList();
    }

    public List<TasksDefault> filterTasksByKeyword(List<TasksDefault> taskList, String keyword) {
        return taskList.stream().filter(task -> task.getTaskDescription().contains(keyword)).toList();
    }

    public List<TasksDefault> filterTasksByMonth(List<TasksDefault> taskList, String date) {

        String shortMonth = DateTimeConversion.getShortMonth(date);
        System.out.println(shortMonth);

        return taskList.stream().filter(task -> task.getDeadlineDate() != null &&
                        task.getDeadlineDate().contains(shortMonth)).toList();
    }

    public Map<String, String> parseInput(String input) throws InvalidCommandException {
        Map<String, String> criteria = new HashMap<String, String>();

        String[] parts = input.split("/");

        for (String part : parts) {
            if (!part.isEmpty()) {
                int firstSpace = part.indexOf(' ');
                if (firstSpace != -1) {
                    String key = part.substring(0, firstSpace).toLowerCase().trim();
                    String value = part.substring(firstSpace + 1).trim();
                    if (key.equals("keyword") || key.equals("type") || key.equals("month")) {
                        criteria.put(key, value);
                    } else {
                        throw new InvalidCommandException("Invalid input format, /keyword <>, /type <> , /month <> allowed only.");
                    }
                } else {
                    throw new InvalidCommandException("Invalid input format, description expected after '/'.");
                }
            }
        }

        return criteria;

    }

    public String printList(List<TasksDefault> taskList) {
        if (!taskList.isEmpty()) {
            return "Here are the tasks found, \n" +
                    IntStream.range(0, taskList.size())
                            .mapToObj(i -> (i+1) + ". " + taskList.get(i).getDescription())
                            .collect(Collectors.joining("\n"));
        } else {
            return "No tasks were found.";
        }
    }
}



















