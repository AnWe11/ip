package tasks;

public class Deadlines extends TasksDefault {

    private String deadlineDate;

    public Deadlines(String description, String deadlineDate) {
        super(description, "[D]");
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String getDescription() {
        StringBuilder str = new StringBuilder();
        str.append("[D]").append(super.getDescription()).append(" (by: ").append(deadlineDate).append(")");
        return str.toString();
    }

    @Override
    public String getDeadlineDate() {
        return deadlineDate;
    }

}
