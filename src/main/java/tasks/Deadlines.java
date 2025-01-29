package tasks;

import datetimeutility.DateTimeConversion;
import exceptions.InvalidDateException;

/**
 * Deadlines task which contains a task description, marked or unmarked state, and a deadline date.
 */
public class Deadlines extends TasksDefault {

    private String deadlineDate;

    /**
     * Deadlines instance.
     * @param description task description.
     * @param deadlineDate deadline date.
     * @throws InvalidDateException If there is no deadline date provided or it is in the wrong format.
     */
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
