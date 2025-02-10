package utility;

import exceptions.InvalidCommandException;

public class StringChecker {

    public static String checkString(String input) throws InvalidCommandException {
        int firstSpaceIndex = input.indexOf(" ");
        //Return everything after first space or empty string if no space
        if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
            throw new InvalidCommandException("Invalid task description / format!");
        }
        return input.substring(firstSpaceIndex + 1);
    }

}
