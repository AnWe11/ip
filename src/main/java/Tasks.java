/*Tasks class to keep track of the different tasks
*
* */

public class Tasks {

    //Initialisation of description of task variable, taskID, and boolean done variables
    private String description;
    private int taskID;
    private boolean done;

    //Initialisation of static int id
    private static int id = 1;

    private static int totalTasks = 0;
    private static int totalTasksDone = 0;

    //Tasks class constructor
    public Tasks(String description) {
        this.description = description;
        this.taskID = id++;
        this.done = false;
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

    //Method to get Task ID
    public int getTaskID() {
        return this.taskID;
    }

    //Method to retrieve the description in the format of a list descriptor
    public String getListDescription() {
        StringBuilder str = new StringBuilder();
        str.append(this.taskID).append(". ").append(this.getDescription()).append("\n");
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

    public String getRemainingTasks() {
        int remainingTasks = totalTasks - totalTasksDone;
        StringBuilder str = new StringBuilder();
        if (remainingTasks == 0) {
            return str.append("Congratulations! You have completed all your tasks! :)").toString();
        } else {
            return str.append("You have ").append(remainingTasks).append(" tasks left to complete!").toString();
        }
    }

    public int getTotalTasks() {
        return totalTasks;
    }

}
