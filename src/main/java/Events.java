public class Events extends Tasks {

    private String taskType = "[E]";
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
        str.append(this.taskType).append(super.getDescription()).append(" (from: ").append(fromString)
                                                                .append(" - ").append(toString).append(")");
        return str.toString();
    }

}
