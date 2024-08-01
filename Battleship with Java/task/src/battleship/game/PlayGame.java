package battleship.game;

import battleship.io.input.UserInputHandler;
import battleship.io.input.UserInputValidator;
import battleship.io.output.DisplayGameScreen;
import battleship.player.Player;
import battleship.ship.*;
import battleship.shooting.ShootingValidator;
import battleship.shooting.UpdateShot;

public class PlayGame {
    // INSTANCE VARIABLES
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;

    // CONSTRUCTOR
    public PlayGame() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.isPlayer1Turn = true;
    }

    public void playGame() {
        // PLAYER 1 - SHIP PLACEMENT
        System.out.println("Player 1, place your ships on the game field");

        // Displays the blank game field
        DisplayGameScreen.displayGameScreen(player1.getGameScreenArray());

        // Player 1 will place all ships in a valid location.
        placeShips(player1);

        // Prompts the user to press enter
        UserInputHandler.promptForEnter();

        // PLAYER 2 - SHIP PLACEMENT
        System.out.println("Player 2, place your ships on the game field");

        // Displays the blank game field
        DisplayGameScreen.displayGameScreen(player2.getGameScreenArray());

        // Player 2 will place all ships in a valid location.
        placeShips(player2);

        // While all ships are not sunk
        boolean running = true;
        while (running) {
            // Prompts the user to press enter
            UserInputHandler.promptForEnter();
            if (isPlayer1Turn) {
                // Displays the current game screen and fog of war for the user.
                DisplayGameScreen.displayGameScreenAndFogOfWar(player1.getFogOfWarArray(),
                        player1.getGameScreenArray());
                takeShot(player1, player2);
            } else {
                DisplayGameScreen.displayGameScreenAndFogOfWar(player2.getFogOfWarArray(),
                        player2.getGameScreenArray());
                takeShot(player2, player1);
            }

            if (player1.allShipsSunk() || player2.allShipsSunk()) {
                running = false;
            } else {
                isPlayer1Turn = !isPlayer1Turn;
            }
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private void placeShips(Player player) {
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
                            shipType, player.getGameScreenArray())) {
                        // Create the ship to be placed
                        Ship ship = new Ship(start, end);
                        ShipPlacementHandler.placeShip(ship, player.getGameScreenArray());

                        // Add the ship to the list of ships
                        player.placeShip(ship);

                        // Update the valid placement to true
                        validPlacement = true;

                        // Displays the blank game field
                        DisplayGameScreen.displayGameScreen(player.getGameScreenArray());

                    }

                } else {
                    System.out.println("Error: Invalid input format. Please enter two coordinates.");
                }

            }

        }
    }

    private void takeShot(Player shooter, Player opponent) {
        boolean validShot = false;
        while (!validShot) {
            System.out.println("Take a shot!");

            String coordinate = UserInputHandler.getUserString();

            // User input must not be empty or null
            if (UserInputValidator.isNullOrEmpty(coordinate)) {
                System.out.println("Error! Coordinates must be provided.");
                continue; // Prompt again
            }

            // Must be only one coordinate given
            if (coordinate.length() > 1) {
                // Validate coordinates
                if (ShootingValidator.isValidShot(coordinate, opponent.getGameScreenArray())) {
                    // Update the game screen with the correct symbol
                    UpdateShot.updateShot(coordinate, opponent.getGameScreenArray(), shooter.getFogOfWarArray());

                    Ship hitShip = ShipSunkHandler.findHitShip(coordinate, opponent.getShips());
                    if (hitShip != null) {
                        if (ShipSunkHandler.isShipSunk(hitShip, opponent.getGameScreen())) {
                            if (opponent.allShipsSunk()) {
                                System.out.println("You sank the last ship. You won. Congratulations!");
                                System.exit(0); // Exit the program
                            }
                            System.out.println("You sank a ship! Specify a new target:");
                        } else {
                            System.out.println("You hit a ship!");
                        }
                    } else {
                        System.out.println("You missed.");
                    }

                    // Update the valid shot flag
                    validShot = true;
                }
            } else {
                System.out.println("Error: Invalid input format. Please enter one coordinate.");
            }
        }
    }
}
