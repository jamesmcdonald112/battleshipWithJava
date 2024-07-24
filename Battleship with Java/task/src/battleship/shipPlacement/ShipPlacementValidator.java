package battleship.shipPlacement;

import java.util.ArrayList;
import java.util.List;

public class ShipPlacementValidator {
    private static final List<Character> POSSIBLE_ROWS = createPossibleRowOptions();
    private static final List<Integer> POSSIBLE_COLUMNS = createPossibleColumnOptions();


    public static boolean isValidCoordinates(String start, String end, char[][] gameBoard ){
        // If the length of the coordinates given are incorrect, return
        if (!isValidLength(start, end)) {
            System.out.println("Error! Coordinates must be between 2-3 characters each");
            return false;
        }


        // Parse the start and end coordinates
        char startRow = start.charAt(0);
        // Gets the numbers that are double digits
        int startCol = Integer.parseInt(start.substring(1));
        char endRow = end.charAt(0);
        int endCol = Integer.parseInt(end.substring(1));

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
        return true;
    }

    /**
     * Creates possible row options from 'A' to 'J'
     *
     * @return A List<Character> of chars filled from 'A' to 'J'
     */
    private static List<Character> createPossibleRowOptions() {
        // Possible rows options. 'A' to 'J'
        List<Character> possibleRows = new ArrayList<>();
        for (char i = 'A'; i <= 'J'; i++) {
            possibleRows.add(i);
        }
        return possibleRows;
    }

    /**
     * Creates possible column options from '1' to '10'
     *
     * @return A List<Integer> of chars filled from '1' to '10'
     */
    private static List<Integer> createPossibleColumnOptions() {
        // Possible rows options. '1' to '10'
        List<Integer> possibleColumns = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            possibleColumns.add(i);
        }
        return possibleColumns;
    }

    /**
     * Makes sure the start and end coordinates are at least two characters in length and no more
     * than 3 characters in length
     *
     * @param start The starting coordinates to check
     * @param end The ending coordinates to check
     * @return True if the start and end are both at least length two but no greater than 3.
     */
    public static boolean isValidLength(String start, String end) {
        return (start != null) && (end != null) &&
                (start.length() >= 2) && (end.length() >= 2 &&
                (start.length() <= 3) && (end.length() <= 3));
    }

    /**
     * Checks if the row and col provided matches a possible row and column of the game.
     *
     * @param row The row to be checked
     * @param col The column to be checked
     * @return True if it is within the bounds of the game field; false otherwise.
     */
    private static boolean isWithinBounds(char row, int col) {
        return POSSIBLE_ROWS.contains(row) && POSSIBLE_COLUMNS.contains(col);
    }

    /**
     * Checks to make sure the ship placement is vertical
     *
     * @param startCol The starting col coordinates
     * @param endCol The ending col coordinates
     * @return True if the placement is vertical; false otherwise.
     */
    private static boolean isVertical(int startCol, int endCol) {
        return (startCol == endCol);

    }

    /**
     * Checks to make sure the ship placement is horizontal
     *
     * @param startRow The starting row coordinates
     * @param endRow The ending row coordinates
     * @return True if the placement is horizontal; false otherwise
     */
    private static boolean isHorizontal(char startRow, char endRow) {
        return (startRow == endRow);
    }

    /**
     * Checks to see if the boat placement is overlapping with another boat.
     *
     * @param startRow The start row coordinate
     * @param startCol The start col coordinate
     * @param endRow The end row coordinate
     * @param endCol The end col coordinate
     * @param gameBoard The game board to check
     * @return True if it is overlapping; false otherwise
     */
    private static boolean isOverlapping(char startRow, int startCol, char endRow, int endCol,
                                         char[][] gameBoard) {

        // Convert row characters to zero-based indices for array access
        int startRowIndex = startRow - 'A';
        int endRowIndex = endRow - 'A';

        // If the placement is horizontal
        if (isHorizontal(startRow, endRow)) {
            // Get the min and max coordinates to ensure the loop works correctly
            int min = Math.min(startCol, endCol);
            int max = Math.max(startCol, endCol);

            // Iterate through the columns in the specified range
            for (int col = min; col <= max; col++) {
                // Check if the cell is occupied
                if (gameBoard[startRowIndex][col -1] != '~') {
                    return true;
                }
            }
        } else { // Vertical Placement
            // Get the min and max coordinates to ensure the loop works correctly
            int min = Math.min(startRowIndex, endRowIndex);
            int max = Math.max(startRowIndex, endRowIndex);

            // Iterate through the rows in the specified range
            for (int row = min; row <= max; row++) {
                // Check if the cell is occupied
                if (gameBoard[row][startCol -1] != '~') {
                    return true;
                }
            }
        }
        return false; // False if no overlap is found
    }
}
