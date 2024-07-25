package battleship.ship;

import battleship.io.input.UserInputHandler;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    // INSTANCE VARIABLES
    private int length;
    private List<String> positions;

    // GETTERS AND SETTERS
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    // CONSTRUCTOR
    public Ship(String start, String end) {
        this.positions = new ArrayList<>();
        calculatePositions(start, end);
        this.length = positions.size();
    }


    /**
     * Uses the coordinates provided to calculate all the positions taken up by the boat.
     *
     * @param start The starting coordinates for the ship
     * @param end The ending coordinates for the ship
     */
    private void calculatePositions(String start, String end) {
        // Separate the coordinates.
        char startRow = start.charAt(0);
        int startCol = UserInputHandler.parseInt(start.substring(1));
        char endRow = end.charAt(0);
        int endCol = UserInputHandler.parseInt(end.substring(1));

        // Check Orientation
        if (startRow == endRow) { // Horizontal placement
            // Find the min and max coordinates to the loop runs correctly
            int min = Math.min(startCol, endCol);
            int max = Math.max(startCol, endCol);

            for (int col = min; col <= max; col++) {
                // Add empty string to concatenate
                positions.add("" + startRow + col);
            }
        } else { // Vertical placement
            // Find the min and max coordinates to the loop runs correctly
            char min = (char) Math.min(startRow, endRow);
            char max = (char) Math.max(startRow, endRow);

            for (char row = min; row <= max; row++) {
                positions.add("" + row + startCol);
            }
        }
    }


}
