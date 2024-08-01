package battleship.shooting;

import battleship.io.input.UserInputHandler;

public class UpdateShot {

    /**
     * The map is updated using 'X' for a hit and 'M' for a miss
     *
     * @param coordinate The coordinates of the shot
     * @param gameScreen The game screen to update
     */
    public static void updateShot(String coordinate, char[][] gameScreen, char[][] fogOfWarScreen) {
        int row = coordinate.charAt(0) - 'A';
        int col = UserInputHandler.parseInt(coordinate.substring(1)) - 1;

        // Check if the position is already hit
        if (gameScreen[row][col] == 'X') {
            // Do nothing, it's already hit
            fogOfWarScreen[row][col] = 'X';
        } else if (gameScreen[row][col] == 'O') {
            // Hit a ship
            gameScreen[row][col] = 'X';
            fogOfWarScreen[row][col] = 'X';
        } else {
            // Miss
            gameScreen[row][col] = 'M';
            fogOfWarScreen[row][col] = 'M';
        }
    }
}
