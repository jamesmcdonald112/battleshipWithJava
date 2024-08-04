package battleship.io.input;

import javax.swing.plaf.synth.SynthColorChooserUI;
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

    /**
     * Ensures the input is an int
     * @param input The String to be parsed to an int
     * @return The int if it can be parsed or -1.
     */
    public static int parseInt(String input) {
        int integer = -1;
        try {
            integer = Integer.parseInt(input);
        }catch (NumberFormatException e) {
            System.out.println("Must be a number from 1-10");
        }
        return integer;
    }

    /**
     * Prompts the user to press enter to move to the next player
     */
    public static void promptForEnter() {
        System.out.println("Press Enter and pass the move to another player");
        SCANNER.nextLine();
    }
}
