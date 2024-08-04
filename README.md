# Battleship Game Project

This project is a command-line implementation of the classic game Battleship. It allows two players to place their ships on a game board and take turns trying to sink each other’s fleet. The game continues until one player has sunk all the opponent’s ships.



## Features

- Ship Placement: Players can place their ships on the game board by entering coordinates.
- Turn-Based Gameplay: Players take turns to shoot at the opponent’s game board.
- Hit and Miss Indicators: The game displays hits and misses on the game board.
- Victory Condition: The game checks for victory when one player's ships are sunk.

## How to Run

1.	Clone the repository and enter the directory

```bash
git clone https://github.com/jamesmcdonald112/battleshipWithJava
cd battleshipWithJava/Battleship\ with\ Java/task/src/       
```

2. Compile the project
```bash
javac battleship/main/Runner.java 
```

3. Run the game
```bash
java battleship/main/Runner 
```

## How to Play

1. Start the Game: Run the Runner class to start the game.
2. Place Ships: Players will be prompted to place their ships on their respective game boards.
3. Take Turns: Players take turns to shoot by entering the coordinates they wish to target.
4. Win the Game: The game continues until one player has sunk all the opponent’s ships.


## Main Classes

1.	PlayGame: Manages the overall game flow including setup, turns, and victory condition.
2.	Player: Represents a player in the game and contains the player’s game board, fog of war, and fleet of ships.
3.	CreateGameScreen: Initialises and manages the game board display.
4.	Ship: Represents a ship and its positions on the game board.
5.	ShipType: Enum representing different types of ships and their lengths.
6.	ShipPlacementHandler: Handles the logic for placing ships on the game board.
7.	ShipPlacementValidator: Validates the coordinates for ship placement to ensure they are valid according to the game rules.
8.	ShipSunkHandler: Checks if a ship is sunk based on the current state of the game board.
9.	ShootingValidator: Validates the coordinates for shooting to ensure they are within bounds.
10.	UpdateShot: Updates the game board based on the result of a shot.
11.	UserInputHandler: Handles user input.
12.	UserInputValidator: Validates user input to ensure it is not null or empty.
13.	DisplayGameScreen: Handles displaying the game screen and fog of war.


## Conclusion
- This project brought together a lot of topics covered in the course, making it an excellent opportunity to integrate and apply multiple concepts simultaneously. The challenges faced during development provided valuable lessons in problem-solving, debugging, and writing clean, maintainable code. Overall, this project has significantly enhanced my Java programming and game development skills.
