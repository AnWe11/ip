package tasks;

public class Deadlines extends TasksDefault {

    private String deadlineDate;

    public Deadlines(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String getDescription() {
        StringBuilder str = new StringBuilder();
        String taskType = "[D]";
        str.append(taskType).append(super.getDescription()).append(" (by: ").append(deadlineDate).append(")");
        return str.toString();
    }

}
