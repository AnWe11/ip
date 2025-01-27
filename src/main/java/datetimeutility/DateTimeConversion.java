package datetimeutility;

import exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConversion {
    private static final DateTimeFormatter TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("h:mm a");
    private static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter LOAD_DATA_INPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
    private static final DateTimeFormatter LOAD_DATA_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");


    public static String getConvertedTime(String time24Hour) throws InvalidDateException {
        try {
            LocalTime time = LocalTime.parse(time24Hour.trim(), TIME_INPUT_FORMAT);
            return time.format(TIME_OUTPUT_FORMAT);
        } catch (Exception e) {
            throw new InvalidDateException("Invalid time format: " + time24Hour, time24Hour);
        }

    }

    public static String getConvertedDate(String inputDate) throws InvalidDateException {
        try {
            LocalDate date = LocalDate.parse(inputDate.trim(), DATE_INPUT_FORMAT);
            return date.format(DATE_OUTPUT_FORMAT);
        } catch (Exception e) {
            throw new InvalidDateException("Invalid date format: " + inputDate, inputDate);
        }

    }

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
