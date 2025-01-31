package tasks;

import datetimeutility.DateTimeConversion;
import exceptions.InvalidDateException;

/**
 * Creates deadline task.
 */
public class Deadlines extends TasksDefault {

    private String deadlineDate;

    public Deadlines(String description, String deadlineDate) throws InvalidDateException {
        super(description, "[D]");
        String[] deadlineDateArray = deadlineDate.trim().split(" ");
        this.deadlineDate = DateTimeConversion.getConvertedDate(deadlineDateArray[0]);
        if (deadlineDateArray.length > 1) {
            this.deadlineDate += " " + DateTimeConversion.getConvertedTime(deadlineDateArray[1]);
        }
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
