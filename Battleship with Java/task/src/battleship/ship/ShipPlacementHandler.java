package battleship.ship;

import java.util.List;

public class ShipPlacementHandler {

    /**
     * Places the ship on the game screen using the character 'O'
     *
     * @param ship The ship to be placed
     * @param gameScreen The game screen to place the boat on.
     */
    public static void placeShip(Ship ship, char[][] gameScreen) {
        // For each position of the coordinate, add them to the map
        for (String position : ship.getPositions()) {
            int row = position.charAt(0) - 'A';
            int col = Integer.parseInt(position.substring(1)) - 1; // Adjusted for 0-based index

            // Add them to the game screen
            gameScreen[row][col] = 'O';
        }

    }


}
