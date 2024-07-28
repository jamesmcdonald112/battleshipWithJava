package battleship.ship;

import battleship.io.input.UserInputHandler;
import battleship.validation.BaseValidation;

import java.util.ArrayList;
import java.util.List;

public class ShipPlacementValidator extends BaseValidation {

    /**
     * Holds the logic to make sure the ship placement follows the rules of the game. The
     * coordinates are checked to make sure they are within the bounds of the game. These
     * coordinates are then checked to ensure the boat placement is either vertical or
     * horizontal, it is then checked that the boat would not be touching or overlapping with
     * another boat. Finally, the boat is checked to make sure it is the required length.
     *
     * @param start The starting coordinate
     * @param end The ending coordinate
     * @param shipType The ship type to be placed
     * @param gameBoard The game board to be referenced.
     * @return True if it is a valid placement; false otherwise.
     */
    public static boolean isValidCoordinates(String start, String end,
                                             ShipType shipType, char[][] gameBoard) {
        // If the length of the coordinates given are incorrect, return
        if (!isValidLength(start, end)) {
            System.out.println("Error! Coordinates must be between 2-3 characters each");
            return false;
        }

        // Parse the start and end coordinates
        char startRow = start.charAt(0);
        // Gets the numbers that are double digits
        int startCol = UserInputHandler.parseInt(start.substring(1));
        char endRow = end.charAt(0);
        int endCol = UserInputHandler.parseInt(end.substring(1));

        // Make sure they are letters from 'A' to 'J' and numbers from 1-10
        if ((!isWithinBounds(startRow, startCol)) || (!isWithinBounds(endRow, endCol))) {
            System.out.println("Error! Coordinates must be within the bounds of the game. 'A' to " +
                    " 'J' and 1-10");
            return false;
        }

        // Make sure the direction is vertical or horizontal
        if ((!isHorizontal(startRow, endRow) && (!isVertical(startCol, endCol)))) {
            System.out.println("Error! Placement cannot be diagonal");
            return false;
        }

        // Does not overlap with another boat
        if (isOverlapping(startRow, startCol, endRow, endCol, gameBoard)) {
            System.out.println("Error! Ship placement cannot overlap with another ship");
            return false;
        }

        // Does not touch another boat
        if (isTouching(startRow, startCol, endRow, endCol, gameBoard)) {
            System.out.println("Error! Ship placement cannot touch another ship");
            return false;
        }

        // Is the required ship length
        if (!isCorrectLength(startRow, startCol, endRow, endCol, shipType)) {
            System.out.println("Error! Ship must be of length " + shipType.getLength());
            return false;
        }
        return true;
    }

    /**
     * Checks to make sure the length of the coordinates is the same as the required length fot
     * the current ship.
     *
     * @param startRow The starting coordinate for the row
     * @param startCol The starting coordinate for the col
     * @param endRow   The ending coordinate for the row
     * @param endCol   The ending coordinate for the col
     * @param shipType The ship to be compared to.
     * @return True of the lengths match; false otherwise
     */
    private static boolean isCorrectLength(char startRow, int startCol, char endRow, int endCol,
                                           ShipType shipType) {
        // Required length for ship placement
        int requiredLength = shipType.getLength();

        // Length of coordinates
        int coordinateLength = -1;

        // Get orientation
        if (startRow == endRow) { // Horizontal placement
            coordinateLength = Math.abs(startCol - endCol) + 1; // Plus one to include all the spaces
        } else { // Vertical placement
            coordinateLength = Math.abs(startRow - endRow) + 1; // Plus one to include all the
            // spaces
        }
        return requiredLength == coordinateLength;
    }

}
