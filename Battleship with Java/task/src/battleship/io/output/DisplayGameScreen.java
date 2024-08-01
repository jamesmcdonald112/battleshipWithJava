package battleship.io.output;

public class DisplayGameScreen {


    /**
     * Displays the game screen in the terminal/
     *
     * @param gameScreen The current game screen to be printed.
     */
    public static void displayGameScreen(char[][] gameScreen) {
        System.out.println(createPrintableGameScreen(gameScreen));
    }

    /**
     * Displays the game screen and fog of war screen in the terminal/
     *
     * @param gameScreen The current game screen to be printed.
     * @param fogOfWar The current fog of war screen to be printed.
     */
    public static void displayGameScreenAndFogOfWar(char[][] fogOfWar, char[][] gameScreen) {
        System.out.println(createPrintableGameScreen(fogOfWar));
        System.out.println("---------------------");
        System.out.println(createPrintableGameScreen(gameScreen));

    }

    /**
     * Create a string of the current game screen. Row labels of 'A' to 'J' are added and column
     * labels of 1-10 are added also.
     *
     * @param gameScreen The game screen to be adjusted.
     * @return The game screen as a string
     */
    private static String createPrintableGameScreen(char[][] gameScreen) {
        StringBuilder gameScreenPrint = new StringBuilder();

        gameScreenPrint.append("  "); // Top left corner is empty with two spaces

        // Append col labels
        for (int col = 1; col <= gameScreen[0].length; col++) {
            gameScreenPrint.append(col).append(" ");
        }
        gameScreenPrint.append("\n");
        
        // Append Row labels and Row information
        for (int row = 0; row < gameScreen.length; row++) {
            // Add the Row labels
            gameScreenPrint.append((char) ('A' + row)).append(" ");
            for (int col = 0; col < gameScreen[row].length; col++) {
                gameScreenPrint.append(gameScreen[row][col]).append(" ");
            }
            gameScreenPrint.append("\n");
        }
        return gameScreenPrint.toString();
    }
}
