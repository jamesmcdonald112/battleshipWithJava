package battleship.io.input;

public class UserInputValidator {

    /**
     * Checks if the input is null or empty
     *
     * @param input The input to be checked
     * @return True if it is null or empty; false otherwise
     */
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.equals("");
    }
}
