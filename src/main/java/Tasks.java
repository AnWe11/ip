/*Tasks class to keep track of the different tasks
*
* */

public class Tasks {

    //description of task variable
    private String description;

    //Tasks class constructor
    public Tasks(String description) {
        this.description = description;
    }

    //method call to get task's description
    public String getDescription() {
        return description;
    }
}
