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

public class PlayGame {

    private CreateGameScreen gameScreen;

    public PlayGame() {
        // Generates a blank game field
        this.gameScreen = new CreateGameScreen();
    }

    public void playGame() {
        // Displays the blank≠ game field
        DisplayGameScreen.displayGameScreen(gameScreen.getGameScreen());

        // User will place all ships in a valid location.
        placeShips();

        System.out.println("Ship placement done next is taking shot");
        ShootingValidator.test();
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
                    return;
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
}
