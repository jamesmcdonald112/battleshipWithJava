package battleship.game;

import battleship.gameScreen.CreateGameScreen;
import battleship.io.input.UserInputHandler;
import battleship.io.input.UserInputValidator;
import battleship.io.output.DisplayGameScreen;
import battleship.ship.Ship;
import battleship.ship.ShipPlacementHandler;
import battleship.ship.ShipPlacementValidator;
import battleship.ship.ShipType;
import battleship.shooting.ShootingValidator;
import battleship.shooting.UpdateShot;

import java.util.ArrayList;
import java.util.List;

public class PlayGame {

    private CreateGameScreen gameScreen;
    private CreateGameScreen fogOfWar;
    private List<Ship> listOfShips;

    public PlayGame() {
        // Generates a blank game field
        this.gameScreen = new CreateGameScreen();
        this.fogOfWar = new CreateGameScreen();
        this.listOfShips = new ArrayList<>();
    }

    public void playGame() {
        // Displays the blank≠ game field
        DisplayGameScreen.displayGameScreen(gameScreen.getGameScreen());

        // User will place all ships in a valid location.
        placeShips();

        System.out.println("The game starts!");

        // Displays the blank game screen
        DisplayGameScreen.displayGameScreen(fogOfWar.getGameScreen());

        // While all ships are not sunk
        while (!allShipsSunk()) {
            // User is promoted to take a shot
            takeShot();

            // Displays the blank game screen
            DisplayGameScreen.displayGameScreen(fogOfWar.getGameScreen());
        }

        // All ships sank
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private void placeShips() {
        // Loops through each ship type
        for (ShipType shipType : ShipType.values()) {
            // A flag keep track if the ship has been placed
            boolean validPlacement = false;
            while (!validPlacement) {

                // Prompt the user to enter the coordinates for the current ship
                System.out.println("Enter the coordinates of the " + shipType.getName() + " (" + shipType.getLength() + " cells): ");
                String userInput = UserInputHandler.getUserString();

                // User input must not be empty or null
                if (UserInputValidator.isNullOrEmpty(userInput)) {
                    System.out.println("Error! Coordinates must be provided.");
                    continue;
                }

                // Separate the coordinates
                String[] coordinates = userInput.split(" ");

                // Must be only two coordinates given
                if (coordinates.length == 2) {

                    // Start and end coordinates for ship placement
                    String start = coordinates[0];
                    String end = coordinates[1];

                    // Validate coordinates
                    if (ShipPlacementValidator.isValidCoordinates(start, end,
                            shipType, gameScreen.getGameScreen())) {
                        // Create the ship to be placed
                        Ship ship = new Ship(start, end);
                        ShipPlacementHandler.placeShip(ship, gameScreen.getGameScreen());

                        // Add the ship to the list of ships
                        listOfShips.add(ship);

                        // Update the valid placement to true
                        validPlacement = true;

                        // Displays the blank game field
                        DisplayGameScreen.displayGameScreen(gameScreen.getGameScreen());

                    }

                } else {
                    System.out.println("Error: Invalid input format. Please enter two coordinates.");
                }

            }

        }
    }

    private void takeShot() {
        boolean validShot = false;
        while (!validShot) {
            System.out.println("Take a shot!");

            String coordinate = UserInputHandler.getUserString();

            // User input must not be empty or null
            if (UserInputValidator.isNullOrEmpty(coordinate)) {
                System.out.println("Error! Coordinates must be provided.");
                continue; // Prompt again
            }


            // Must be only two coordinates given
            if (coordinate.length() > 1) {

                // Validate coordinates
                if (ShootingValidator.isValidShot(coordinate, gameScreen.getGameScreen())) {

                    // Update the game screen with the correct symbol
                    UpdateShot.updateShot(coordinate, gameScreen.getGameScreen(),
                            fogOfWar.getGameScreen());

                    Ship hitShip = findHitShip(coordinate);
                    if (hitShip != null && isShipSunk(hitShip)) {
                        System.out.println("You sank a ship! Specify a new target:");
                    }

                    // Update the valid shot flag
                    validShot = true;
                }

            } else {
                System.out.println("Error: Invalid input format. Please enter one coordinates.");
            }
        }
    }

    private boolean allShipsSunk() {
        for (Ship ship : listOfShips) {
            if (!isShipSunk(ship)) {
                return false;
            }
        }
        return true;
    }

    private boolean isShipSunk(Ship ship) {
        for (String position : ship.getPositions()) {
            int row = position.charAt(0) - 'A';
            int col = UserInputHandler.parseInt(position.substring(1)) - 1;

            if (fogOfWar.getGameScreen()[row][col] != 'X') {
                return false;
            }
        }
        return true;
    }

    private Ship findHitShip(String coordinate) {
        for (Ship ship : listOfShips) {
            if (ship.getPositions().contains(coordinate)) {
                return ship;
            }
        }
        return null;
    }
}
