package battleship.game;

import battleship.gameScreen.CreateGameScreen;
import battleship.io.input.UserInputHandler;
import battleship.io.input.UserInputValidator;
import battleship.io.output.DisplayGameScreen;
import battleship.ship.Ship;
import battleship.ship.ShipPlacementHandler;
import battleship.ship.ShipPlacementValidator;

public class PlayGame {

    public void playGame() {
        // Generates a blank game field
        CreateGameScreen gameScreen = new CreateGameScreen();

        // Displays the blank game field
        DisplayGameScreen.displayGameScreen(gameScreen.getGameScreen());

        // Get the coordinates of the ship
        System.out.println("Enter the coordinates of the ship: ");
        String userInput = UserInputHandler.getUserString();
        System.out.println(userInput);

        // User input must not be empty or null
        if (UserInputValidator.isNullOrEmpty(userInput)) {
            System.out.println("Error! Coordinates must be provided.");
            return;
        }

        String[] coordinates = userInput.split(" ");

        // Must be only two coordinates given
        if (coordinates.length == 2) {

            // Start and end coordinates for ship placement
            String start = coordinates[0];
            String end = coordinates[1];

            // Validate coordinates
            if (ShipPlacementValidator.isValidCoordinates(start, end, gameScreen.getGameScreen())) {
                ShipPlacementHandler.placeShip(start, end, gameScreen.getGameScreen());
                Ship ship = new Ship(start, end);
                System.out.println("Length: " + ship.getLength());
                System.out.println("Parts: " + String.join(" ", ship.getPositions()));

                // Display the updated game filed
                DisplayGameScreen.displayGameScreen(gameScreen.getGameScreen());
            } else {
                System.out.println("Error! Invalid coordinates.");
            }


        } else {
            System.out.println("Error: Invalid input format. Please enter two coordinates.");
        }


    }
}
