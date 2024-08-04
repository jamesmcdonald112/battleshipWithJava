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

    /**
     * Constructor to initialise the PlayGame instance.
     * It creates two players and sets the initial turn to Player 1.
     */
    public PlayGame() {
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.isPlayer1Turn = true;
    }

    /**
     * Main method to start the game.
     * It handles the setup of both players and manages the game loop until one player wins.
     */
    public void playGame() {
        // Set up Player 1
        setupPlayer(player1);

        // Prompt the user to press Enter
        UserInputHandler.promptForEnter();

        // Set up Player 2
        setupPlayer(player2);

        // Main game loop
        boolean running = true;
        while (running) {
            // Prompt the user to press Enter
            UserInputHandler.promptForEnter();
            if (isPlayer1Turn) {
                // Player 1's turn
                playTurn(player1, player2);
            } else {
                // Player 2's turn
                playTurn(player2, player1);
            }

            // Check if any player has won
            if (player1.allShipsSunk() || player2.allShipsSunk()) {
                running = false;
            } else {
                // Switch turns
                isPlayer1Turn = !isPlayer1Turn;
            }
        }
    }

    /**
     * Sets up a player by displaying the game field and prompting for ship placements.
     *
     * @param player The player to set up.
     */
    private void setupPlayer(Player player) {
        System.out.println(player.getName() + ", place your ships on the game field");

        // Display the blank game field
        DisplayGameScreen.displayGameScreen(player.getGameScreenArray());

        // Player places all ships
        placeShips(player);
    }

    /**
     * Handles the ship placement process for a player.
     *
     * @param player The player placing ships.
     */
    private void placeShips(Player player) {
        // Loop through each ship type
        for (ShipType shipType : ShipType.values()) {
            boolean validPlacement = false;
            while (!validPlacement) {
                // Prompt the user to enter coordinates for the current ship
                System.out.println("Enter the coordinates of the " + shipType.getName() + " (" + shipType.getLength() + " cells): ");
                String userInput = UserInputHandler.getUserString();

                // Validate user input
                if (UserInputValidator.isNullOrEmpty(userInput)) {
                    System.out.println("Error! Coordinates must be provided.");
                    continue;
                }

                // Attempt to place the ship
                validPlacement = player.placeShip(userInput, shipType);
                if (!validPlacement) {
                    System.out.println("Error! Invalid ship placement. Please try again.");
                }
            }
            // Display the updated game field
            DisplayGameScreen.displayGameScreen(player.getGameScreenArray());
        }
    }

    /**
     * Handles a player's turn, displaying the game screens and processing a shot.
     *
     * @param shooter  The player taking the shot.
     * @param opponent The player being shot at.
     */
    private void playTurn(Player shooter, Player opponent) {
        // Display the game screens
        DisplayGameScreen.displayGameScreenAndFogOfWar(shooter.getFogOfWarArray(), shooter.getGameScreenArray());
        // Process the shot
        takeShot(shooter, opponent);
    }

    /**
     * Processes the shooting action for a player, validating the shot and updating the game state.
     *
     * @param shooter  The player taking the shot.
     * @param opponent The player being shot at.
     */
    private void takeShot(Player shooter, Player opponent) {
        boolean validShot = false;
        while (!validShot) {
            System.out.println("Take a shot!");

            String coordinate = UserInputHandler.getUserString();

            // Validate user input
            if (UserInputValidator.isNullOrEmpty(coordinate)) {
                System.out.println("Error! Coordinates must be provided.");
                continue; // Prompt again
            }

            // Attempt to shoot at the coordinate
            validShot = shooter.shoot(coordinate, opponent);
        }
    }
}
