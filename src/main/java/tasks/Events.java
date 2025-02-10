package tasks;

import utility.DateTimeConversion;

/**
 * Creates event task.
 */
public class Events extends TasksDefault {

    private String fromString;
    private String toString;

    public Events(String description, String fromString, String toString) {
        super(description, "[E]");
        String[] fromStringArray = fromString.trim().split(" ");
        this.fromString = DateTimeConversion.getConvertedDate(fromStringArray[0]);
        if (fromStringArray.length > 1) {
            this.fromString += " " + DateTimeConversion.getConvertedTime(fromStringArray[1]);
        }
        String[] toStringArray = toString.trim().split(" ");
        this.toString = DateTimeConversion.getConvertedDate(toStringArray[0]);
        if (toStringArray.length > 1) {
            this.toString += " " + DateTimeConversion.getConvertedTime(toStringArray[1]);
        }
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
        return this.fromString + " - " + this.toString;
    }
}
