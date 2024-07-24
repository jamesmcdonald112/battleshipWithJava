package battleship.gameScreen;

public class CreateGameScreen {
    // INSTANCE VARIABLES
    private char[][] gameScreen;

    // GETTERS AND SETTERS


    public char[][] getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(char[][] gameScreen) {
        this.gameScreen = gameScreen;
    }

    // CONSTRUCTORS
    public CreateGameScreen() {
        // Row for column and row labels
        gameScreen = new char[10][10];
        initialiseGameScreen();
    }

    // METHODS
    private void initialiseGameScreen() {
        // Add tilde
        addTilde();
    }

    private void addTilde() {
        for (int row = 0; row < gameScreen.length; row++) {
            for (int col = 0; col < gameScreen[row].length; col++) {
                gameScreen[row][col] = '~';
            }
        }
    }




}
