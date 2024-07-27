package battleship.validation;

import java.util.ArrayList;
import java.util.List;

public class BaseValidation {
    private static final List<Character> POSSIBLE_ROWS = createPossibleRowOptions();
    private static final List<Integer> POSSIBLE_COLUMNS = createPossibleColumnOptions();


    /**
     * Makes sure the start and end coordinates are at least two characters in length and no more
     * than 3 characters in length
     *
     * @param start The starting coordinates to check
     * @param end   The ending coordinates to check
     * @return True if the start and end are both at least length two but no greater than 3.
     */
    protected static boolean isValidLength(String start, String end) {
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
    protected static boolean isWithinBounds(char row, int col) {
        return POSSIBLE_ROWS.contains(row) && POSSIBLE_COLUMNS.contains(col);
    }

    /**
     * Checks to make sure the ship placement is vertical
     *
     * @param startCol The starting col coordinates
     * @param endCol   The ending col coordinates
     * @return True if the placement is vertical; false otherwise.
     */
    protected static boolean isVertical(int startCol, int endCol) {
        return (startCol == endCol);

    }

    /**
     * Checks to make sure the ship placement is horizontal
     *
     * @param startRow The starting row coordinates
     * @param endRow   The ending row coordinates
     * @return True if the placement is horizontal; false otherwise
     */
    protected static boolean isHorizontal(char startRow, char endRow) {
        return (startRow == endRow);
    }

    /**
     * Checks to see if the boat placement is overlapping with another boat.
     *
     * @param startRow  The start row coordinate
     * @param startCol  The start col coordinate
     * @param endRow    The end row coordinate
     * @param endCol    The end col coordinate
     * @param gameBoard The game board to check
     * @return True if it is overlapping; false otherwise
     */
    protected static boolean isOverlapping(char startRow, int startCol, char endRow, int endCol,
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
                if (gameBoard[startRowIndex][col - 1] != '~') {
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
                if (gameBoard[row][startCol - 1] != '~') {
                    return true;
                }
            }
        }
        return false; // False if no overlap is found
    }

    /**Checks if the boat placement would be touching another boat.
     *
     * @param startRow  The start row coordinate
     * @param startCol  The start col coordinate
     * @param endRow    The end row coordinate
     * @param endCol    The end col coordinate
     * @param gameBoard The game board to check
     * @return True if the boat placement is touching another boat; false otherwise.
     */
    protected static boolean isTouching(char startRow, int startCol, char endRow, int endCol,
                                      char[][] gameBoard) {
        int startRowIndex = startRow - 'A';
        int endRowIndex = endRow - 'A';

        // Plus 1 and minus 1 to include the row or column just before the ship
        int minRow = Math.min(startRowIndex, endRowIndex) - 1;
        int maxRow = Math.max(startRowIndex, endRowIndex) + 1;
        int minCol = Math.min(startCol, endCol) - 1;
        int maxCol = Math.max(startCol, endCol) + 1;

        // Iterate over the surrounding cells
        for (int row = Math.max(0, minRow); row <= Math.min(9, maxRow); row++) {
            for (int col = Math.max(0, minCol); col <= Math.min(9, maxCol); col++) {
                if (gameBoard[row][col] == 'O') {
                    return true;
                }
            }
        }

        return false; // False if no touching if found
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
}