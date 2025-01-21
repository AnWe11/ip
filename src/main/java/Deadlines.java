public class Deadlines extends Tasks {

    private String taskType = "[D]";
    private String deadlineDate;

    public Deadlines(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String getDescription() {
        StringBuilder str = new StringBuilder();
        str.append(this.taskType).append(super.getDescription()).append(" (by: ").append(deadlineDate).append(")");
        return str.toString();
    }

    public String getListDescription() {
        return super.getListDescription();
    }
}
