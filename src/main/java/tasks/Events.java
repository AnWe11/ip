package tasks;

public class Events extends TasksDefault {

    private String fromString;
    private String toString;

    public Events(String description, String fromString, String toString) {
        super(description);
        this.fromString = fromString;
        this.toString = toString;
    }

    @Override
    public String getDescription() {
        StringBuilder str = new StringBuilder();
        String taskType = "[E]";
        str.append(taskType).append(super.getDescription()).append(" (from: ").append(fromString)
                                                                .append(" - ").append(toString).append(")");
        return str.toString();
    }

}
