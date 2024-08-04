package battleship.player;

import battleship.gameScreen.CreateGameScreen;
import battleship.ship.*;
import battleship.shooting.ShootingValidator;
import battleship.shooting.UpdateShot;

import java.util.ArrayList;
import java.util.List;


public class Player {

    // INSTANCE VARIABLES
    private String name;
    private CreateGameScreen gameScreen;
    private CreateGameScreen fogOfWar;
    private List<Ship> ships;

    /**
     * Constructor to initialise the Player instance.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.gameScreen = new CreateGameScreen();
        this.fogOfWar = new CreateGameScreen();
        this.ships = new ArrayList<>();
    }

    // GETTERS

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current game screen array.
     *
     * @return The game screen array.
     */
    public char[][] getGameScreenArray() {
        return gameScreen.getGameScreen();
    }

    /**
     * Gets the current fog of war array.
     *
     * @return The fog of war array.
     */
    public char[][] getFogOfWarArray() {
        return fogOfWar.getGameScreen();
    }

    /**
     * Gets the CreateGameScreen object representing the game screen.
     *
     * @return The game screen.
     */
    public CreateGameScreen getGameScreen() {
        return gameScreen;
    }

    /**
     * Gets the list of ships owned by the player.
     *
     * @return The list of ships.
     */
    public List<Ship> getShips() {
        return ships;
    }

    // METHODS

    /**
     * Places a ship on the player's game screen.
     *
     * @param userInput The user input containing the start and end coordinates for the ship.
     * @param shipType The type of ship to be placed.
     * @return True if the ship is placed successfully; false otherwise.
     */
    public boolean placeShip(String userInput, ShipType shipType) {
        String[] coordinates = userInput.split(" ");
        if (coordinates.length == 2) {
            String start = coordinates[0];
            String end = coordinates[1];
            boolean isValidPlacement = ShipPlacementValidator.isValidCoordinates(start, end, shipType, gameScreen.getGameScreen());
            if (isValidPlacement) {
                Ship ship = new Ship(start, end);
                ships.add(ship);
                ShipPlacementHandler.placeShip(ship, gameScreen.getGameScreen());
                return true;
            }
        } else {
            System.out.println("Error! Invalid ship placement. Please try again.");
        }
        return false;
    }

    /**
     * Handles the shooting action from the player.
     *
     * @param coordinate The coordinate to shoot at.
     * @param opponent The opponent player.
     * @return True if the shot is valid; false otherwise.
     */
    public boolean shoot(String coordinate, Player opponent) {
        if (!ShootingValidator.isValidShot(coordinate, opponent.getGameScreenArray())) {
            return false;
        }

        UpdateShot.updateShot(coordinate, opponent.getGameScreenArray(), fogOfWar.getGameScreen());
        Ship hitShip = ShipSunkHandler.findHitShip(coordinate, opponent.getShips());

        if (hitShip != null) {
            handleHit(hitShip, opponent);
        } else {
            handleMiss();
        }

        return true;
    }

    /**
     * Handles the logic for when a ship is hit.
     *
     * @param hitShip The ship that was hit.
     * @param opponent The opponent player.
     */
    private void handleHit(Ship hitShip, Player opponent) {
        if (ShipSunkHandler.isShipSunk(hitShip, opponent.getGameScreen())) {
            if (opponent.allShipsSunk()) {
                announceVictory();
            } else {
                announceSunkShip();
            }
        } else {
            announceHit();
        }
    }

    /**
     * Handles the logic for when a shot misses.
     */
    private void handleMiss() {
        System.out.println("You missed.");
    }

    /**
     * Announces that a ship was hit.
     */
    private void announceHit() {
        System.out.println("You hit a ship!");
    }

    /**
     * Announces that a ship was sunk.
     */
    private void announceSunkShip() {
        System.out.println("You sank a ship! Specify a new target:");
    }

    /**
     * Announces that the player has won the game.
     */
    private void announceVictory() {
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    /**
     * Checks if all ships of the player are sunk.
     *
     * @return True if all ships are sunk; false otherwise.
     */
    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ShipSunkHandler.isShipSunk(ship, gameScreen)) {
                return false;
            }
        }
        return true;
    }
}