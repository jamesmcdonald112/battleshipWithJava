package battleship.io.input;

import java.util.Scanner;

public class UserInputHandler {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Gets the input from the user as a String
     * @return The users input as a string.
     */
    public static String getUserString() {
        return SCANNER.nextLine();
    }

}
