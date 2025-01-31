/*Tasks class to keep track of the different tasks
 *
 * */
package tasks;

/**
 * Adds task.
 */
public class TasksDefault {

    //Initialisation of description of task variable, taskID, and boolean done variables
    private String description;
    private int taskID;
    private boolean done;
    private String taskType;

    private static int id = 1;

    private static int totalTasks = 0;
    private static int totalTasksDone = 0;

    public TasksDefault(String description, String taskType) {
        this.description = description;
        this.taskID = id++;
        this.done = false;
        this.taskType = taskType;
        totalTasks++;
    }

    //method call to get task's description
    public String getDescription() {
        StringBuilder str = new StringBuilder();
        if (done) {
            str.append("[X] ");
        } else {
            str.append("[ ] ");
        }
        str.append(this.description);
        return str.toString();
    }

    //Method to mark task as done
    public void markAsDone() {
        if (!done) {
            totalTasksDone++;
        }
        this.done = true;
    }

    //Method to unmark task as undone
    public void unmark() {
        if (done) {
            totalTasksDone--;
        }
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getTaskDescription() {
        return this.description;
    }

    public String getDeadlineDate() {
        return "";
    }

}
