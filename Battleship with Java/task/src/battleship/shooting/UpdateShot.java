package battleship.shooting;

import battleship.io.input.UserInputHandler;

public class UpdateShot {

    /**
     * The map is updated using 'X' for a hit and 'M' for a miss
     *
     * @param coordinate The coordinates of the shot
     * @param gameScreen The game screen to update
     */
    public static void updateShot(String coordinate, char[][] gameScreen) {
        int row = coordinate.charAt(0) - 'A';
        int col = UserInputHandler.parseInt(coordinate.substring(1)) - 1;

        // If there is a boat at the coordinate, place an 'X'
        if (gameScreen[row][col] == 'O') {
            gameScreen[row][col] = 'X';
            System.out.println("You hit a ship!");
        } else { // Place M for a miss
            gameScreen[row][col] = 'M';
            System.out.println("You missed!");
        }
    }
}
