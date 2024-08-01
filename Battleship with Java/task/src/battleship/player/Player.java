package battleship.player;

import battleship.gameScreen.CreateGameScreen;
import battleship.ship.Ship;
import battleship.ship.ShipPlacementHandler;
import battleship.ship.ShipSunkHandler;

import java.util.ArrayList;
import java.util.List;

public class Player {

    // INSTANCE VARIABLES
    private CreateGameScreen gameScreen;
    private CreateGameScreen fogOfWar;
    private List<Ship> ships;

    public Player() {
        this.gameScreen = new CreateGameScreen();
        this.fogOfWar = new CreateGameScreen();
        this.ships = new ArrayList<>();
    }

    public char[][] getGameScreenArray() {
        return gameScreen.getGameScreen();
    }

    public char[][] getFogOfWarArray() {
        return fogOfWar.getGameScreen();
    }

    public CreateGameScreen getGameScreen() {
        return gameScreen;
    }

    public List<Ship> getShips() {
        return ships;
    }


    public void placeShip(Ship ship) {
        ships.add(ship);
        ShipPlacementHandler.placeShip(ship, gameScreen.getGameScreen());
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ShipSunkHandler.isShipSunk(ship, gameScreen)){
                return false;
            }
        }
        return true;
    }
}
