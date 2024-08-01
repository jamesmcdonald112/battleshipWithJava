package battleship.ship;

import battleship.gameScreen.CreateGameScreen;
import battleship.io.input.UserInputHandler;

import java.util.List;

public class ShipSunkHandler {
    public static Ship findHitShip(String coordinate, List<Ship> listOfShips) {
        for (Ship ship : listOfShips) {
            if (ship.getPositions().contains(coordinate)) {
                return ship;
            }
        }
        return null;
    }

    public static boolean isShipSunk(Ship ship, CreateGameScreen gameScreen) {
        for (String position : ship.getPositions()) {
            int row = position.charAt(0) - 'A';
            int col = UserInputHandler.parseInt(position.substring(1)) - 1;

            if (gameScreen.getGameScreen()[row][col] != 'X') {
                return false;
            }
        }
        return true;
    }
}
