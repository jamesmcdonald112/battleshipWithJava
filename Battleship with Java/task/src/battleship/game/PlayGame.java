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

public class PlayGame {

    private CreateGameScreen gameScreen;
    private CreateGameScreen fogOfWar;

    public PlayGame() {
        // Generates a blank game field
        this.gameScreen = new CreateGameScreen();
        this.fogOfWar = new CreateGameScreen();
    }

    public void playGame() {
        // Displays the blank≠ game field
        DisplayGameScreen.displayGameScreen(gameScreen.getGameScreen());

        // User will place all ships in a valid location.
        placeShips();

        System.out.println("The game starts!");

        // User is promoted to take a shot
        takeShot();
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

    private void takeShot() {
        // Displays the blank game screen
        DisplayGameScreen.displayGameScreen(fogOfWar.getGameScreen());

        boolean validShot = false;
        while (!validShot) {
            System.out.println("Take a shot!");

            String coordinate = UserInputHandler.getUserString();

            // User input must not be empty or null
            if (UserInputValidator.isNullOrEmpty(coordinate)) {
                System.out.println("Error! Coordinates must be provided.");
                return;
            }


            // Must be only two coordinates given
            if (coordinate.length() > 1) {

                // Validate coordinates
                if (ShootingValidator.isValidShot(coordinate, gameScreen.getGameScreen())) {

                    // Update the game screen with the correct symbol
                    UpdateShot.updateShot(coordinate, gameScreen.getGameScreen());

                    // Update the valid shot flag
                    validShot = true;

                    // Displays the updated game screen
                    DisplayGameScreen.displayGameScreen(gameScreen.getGameScreen());

                    // Displays the blank game screen
                    DisplayGameScreen.displayGameScreen(fogOfWar.getGameScreen());
                }

            } else {
                System.out.println("Error: Invalid input format. Please enter one coordinates.");
            }
        }
    }
}
