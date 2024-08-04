package battleship.ship;

import battleship.gameScreen.CreateGameScreen;
import battleship.io.input.UserInputHandler;

import java.util.List;

public class ShipSunkHandler {

    /**
     * Finds the ship that was hit based on the given coordinate.
     *
     * @param coordinate  The coordinate of the hit.
     * @param listOfShips The list of ships to check against.
     * @return The ship that was hit, or null if no ship was hit.
     */
    public static Ship findHitShip(String coordinate, List<Ship> listOfShips) {
        for (Ship ship : listOfShips) {
            if (ship.getPositions().contains(coordinate)) {
                return ship;
            }
        }
        return null;
    }

    /**
     * Checks if a given ship is sunk by verifying if all its positions are marked as hit ('X') on the game screen.
     *
     * @param ship        The ship to check.
     * @param gameScreen  The game screen to check against.
     * @return True if the ship is sunk; false otherwise.
     */
    public static boolean isShipSunk(Ship ship, CreateGameScreen gameScreen) {
        for (String position : ship.getPositions()) {
            int row = position.charAt(0) - 'A'; // Convert the row letter to a zero-based index
            int col = UserInputHandler.parseInt(position.substring(1)) - 1; // Convert the column number to a zero-based index

            // Check if the position on the game screen is not marked as hit ('X')
            if (gameScreen.getGameScreen()[row][col] != 'X') {
                return false;
            }
        }
        return true;
    }
}