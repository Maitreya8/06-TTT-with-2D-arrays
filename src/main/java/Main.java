/*
This project must play Tic-Tac-Toe.


For the first in-class demonstration, you must have the following:
1) a static constand 2-D Array the represnets the board and holds the STATE data for the game.
2) You must correctly resolve:
  horizontal, 
  diagonal, 
  vertical winner conditions
  And a draw
3) You must have 1 player.

For the future and more points above an A-...
0) You must correctly resolve all win and draw states
1) You should have a seond player
2) You should be able to play on a 4 x 4 board
3) You should correctly resolve turns
4) The player and the game should be in seperate classes from your static Main


For more in the future and more awesomer...
1) You can have a text file documenting player win-loss records and another stat of your choice
2) You can have a 3rd or 4th player
3) You can have a 3rd dimension to the game
4) You can make a new class for a game session as well as a single game
5) You can make a new class for a turn, or a piece, or a screen painter
*/

import java.util.Random;
import java.util.Scanner;

public class Main {
  private static final int SIZE = 3; // Change to 4 for a 4x4 board
  private static final char EMPTY = '-';
  private static final char PLAYER_SYMBOL = 'X';
  private static final char COMPUTER_SYMBOL = 'O';
  private static final char[][] board = new char[SIZE][SIZE];
  private static boolean playerTurn = true; //tracks whose turn it is
  private static final Scanner scanner = new Scanner(System.in); // Scanner for user input

  public static void main(String[] args) {
    initializeBoard(); // Initialize the game board with empty cells
    displayBoard(); // Display the initial empty board
    // Main game loop
    while (!checkWin() && !checkDraw()) {
      if (playerTurn) {
        playerMove(); // Player's turn
      } else {
        computerMove(); // Computer's turn
      }
      displayBoard(); // Display the updated board after each move
      playerTurn = !playerTurn; // Switch turns
    }

    // Display the game result
    if (checkWin()) {
      System.out.println(playerTurn ? "Computer wins!" : "YOU win!");
    } else {
      System.out.println("It's a draw!");
    }
  }

  // Initialize the game board with empty cells
  private static void initializeBoard() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        board[i][j] = EMPTY;
      }
    }
  }

  // Display the current state of the game board
  private static void displayBoard() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  // Player's move logic
  private static void playerMove() {
    System.out.println("Enter row and column numbers (0-" + (SIZE - 1) + "): ");
    int row = scanner.nextInt();
    int col = scanner.nextInt();
    if (isValidMove(row, col)) {
      board[row][col] = PLAYER_SYMBOL;
    } else {
      System.out.println("Invalid move. Try again.");
      playerMove(); // Keep ask for valid input
    }
  }

  // Computer's move logic
  private static void computerMove() {
    Random random = new Random();
    int row, col;
    do {
      row = random.nextInt(SIZE);
      col = random.nextInt(SIZE);
    } while (!isValidMove(row, col));
    board[row][col] = COMPUTER_SYMBOL;
  }

  // Check for win conditions
  private static boolean checkWin() {
    // Check rows
    for (int i = 0; i < SIZE; i++) {
      if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
        return true;
      }
    }
    // Check columns
    for (int j = 0; j < SIZE; j++) {
      if (board[0][j] != EMPTY && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
        return true;
      }
    }
    // Check diagonals
    if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
      return true;
    }
    if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
      return true;
    }
    return false;
  }

  // Check for a draw (all cells filled without a win)
  private static boolean checkDraw() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j] == EMPTY) {
          return false; // If any cell is empty, game is not a draw
        }
      }
    }
    return true; // If no empty cell found, game is a draw
  }

  // Check if a move is valid (within bounds and cell is empty)
  private static boolean isValidMove(int row, int col) {
    return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
  }
}