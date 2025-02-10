package utility;

import exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Returns date time in different formats.
 */
public class DateTimeConversion {
    private static final DateTimeFormatter TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("h:mm a");
    private static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter LOAD_DATA_INPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
    private static final DateTimeFormatter LOAD_DATA_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Returns 12-hour format with AM/PM, 6:00 PM, from 24 hour time with format 1800.
     * @param time24Hour 24-hour String format time
     * @return 12-hour format time with AM/PM String
     * @throws InvalidDateException If given input time is in the invalid format.
     */
    public static String getConvertedTime(String time24Hour) throws InvalidDateException {
        try {
            LocalTime time = LocalTime.parse(time24Hour.trim(), TIME_INPUT_FORMAT);
            return time.format(TIME_OUTPUT_FORMAT);
        } catch (Exception e) {
            throw new InvalidDateException("Invalid time format: " + time24Hour, time24Hour);
        }

    }

    /**
     * Converts date with format 2025-01-29 to date with format Jan 29 2025.
     * @param inputDate date String with format 2025-01-29.
     * @return date String with format Jan 29 2025.
     * @throws InvalidDateException If given input date is in invalid format.
     */
    public static String getConvertedDate(String inputDate) throws InvalidDateException {
        try {
            LocalDate date = LocalDate.parse(inputDate.trim(), DATE_INPUT_FORMAT);
            return date.format(DATE_OUTPUT_FORMAT);
        } catch (Exception e) {
            throw new InvalidDateException("Invalid date format: " + inputDate, inputDate);
        }

    }

    /**
     * Converts date and time with format Jan 29 2025 6:00 PM to date and time with format 2025-01-29 1800.
     * Can also convert date if no time is given.
     * @param inputDate dateTime input String with format Jan 29 2025 6:00 PM or Jan 29 2025.
     * @return dateTime String with format 2025-01-29 1800 or 2025-01-29.
     * @throws InvalidDateException
     */
    public static String loadDateTime(String inputDate) throws InvalidDateException {
        inputDate = inputDate.trim();
        try {
            if (inputDate.contains(":") && inputDate.contains("AM") || inputDate.contains("PM")) {
                LocalDateTime dateTime = LocalDateTime.parse(inputDate, LOAD_DATA_INPUT_FORMAT);
                return dateTime.format(LOAD_DATA_OUTPUT_FORMAT);
            } else {
                LocalDate date = LocalDate.parse(inputDate, DATE_OUTPUT_FORMAT);
                return date.format(DATE_INPUT_FORMAT);
            }
        } catch (Exception e) {
            throw new InvalidDateException("Invalid date format: " + inputDate, inputDate);
        }
    }
}
