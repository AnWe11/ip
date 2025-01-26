package tasks;

public class Events extends TasksDefault {

    private String fromString;
    private String toString;

    public Events(String description, String fromString, String toString) {
        super(description, "[E]");
        this.fromString = fromString;
        this.toString = toString;
    }

    @Override
    public String getDescription() {
        StringBuilder str = new StringBuilder();
        str.append("[E]").append(super.getDescription()).append(" (from: ").append(fromString)
                                                                .append(" - ").append(toString).append(")");
        return str.toString();
    }

    @Override
    public String getDeadlineDate() {
        return this.fromString + "-" + this.toString;
    }
}
