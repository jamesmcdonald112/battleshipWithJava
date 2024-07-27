package battleship.shooting;

import battleship.io.input.UserInputHandler;
import battleship.validation.BaseValidation;

public class ShootingValidator extends BaseValidation {

    /**
     * Handles the logic to check if a shot is valid.
     *
     * @param coordinate The coordinate to validate.
     * @param gameScreen The game screen to check.
     * @return True if it a valid shot; false otherwise.
     */
    public static boolean isValidShot(String coordinate, char[][] gameScreen) {
        if (!isValidCoordinate(coordinate)) {
            System.out.println("Error! Coordinate must be within the bounds of the map");
            return false;
        }

        if (isHit(coordinate, gameScreen)) {
            System.out.println("Error! Coordinate has previously been selected");
            return false;
        }
        return true;
    }

    /**
     * Validates the coordinate is the correct length and within the bounds of the game
     *
     * @param coordinate The coordinate to validate
     * @return True if it is a valid coordinate; false otherwise
     */
    private static boolean isValidCoordinate(String coordinate) {
        if (coordinate.length() > 3) {
            return false;
        }

        char row = coordinate.charAt(0);
        int col = UserInputHandler.parseInt(coordinate.substring(1));

        if (!isWithinBounds(row, col)) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the coordinate has previously been selected.
     *
     * @param coordinate The coordinate to check
     * @param gameScreen The game screen to check
     * @return True if there is a 'X' or an 'M' at the coordinate; false otherwise
     */
    private static boolean isHit(String coordinate, char[][] gameScreen) {
        int row = coordinate.charAt(0) - 'A';
        int col = Integer.parseInt(coordinate.substring(1)) - 1;

        // X is a hit and M is a miss
        return gameScreen[row][col] == 'X' || gameScreen[row][col] == 'M';
    }
}
