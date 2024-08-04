package battleship.gameScreen;

public class CreateGameScreen {
    // INSTANCE VARIABLES
    private char[][] gameScreen;

    // GETTERS AND SETTERS

    /**
     * Gets the current game screen.
     *
     * @return The current game screen as a 2D char array.
     */
    public char[][] getGameScreen() {
        return gameScreen;
    }

    /**
     * Sets the game screen.
     *
     * @param gameScreen The game screen to set as a 2D char array.
     */
    public void setGameScreen(char[][] gameScreen) {
        this.gameScreen = gameScreen;
    }

    // CONSTRUCTORS

    /**
     * Constructor to initialize the CreateGameScreen instance.
     * It creates a 10x10 game screen and initializes it with default values.
     */
    public CreateGameScreen() {
        gameScreen = new char[10][10];
        initialiseGameScreen();
    }

    // METHODS

    /**
     * Initializes the game screen by adding default values.
     * This method sets each cell of the game screen to a tilde ('~') to represent water.
     */
    private void initialiseGameScreen() {
        addTilde();
    }

    /**
     * Fills the game screen with tilde ('~') characters to represent water.
     */
    private void addTilde() {
        for (int row = 0; row < gameScreen.length; row++) {
            for (int col = 0; col < gameScreen[row].length; col++) {
                gameScreen[row][col] = '~';
            }
        }
    }
}