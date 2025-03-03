package tasks;

/**
 * Adds todo task.
 */
public class ToDo extends TasksDefault {

    public ToDo(String description) {
        super(description, "[T]");
    }

    @Override
    public String getDescription() {
        StringBuilder str = new StringBuilder();
        str.append("[T]").append(super.getDescription());
        return str.toString();
    }

    @Override
    public String getDeadlineDate() {
        return super.getDeadlineDate();
    }
}
