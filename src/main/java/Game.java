class Game {
  private static final int SIZE = 3; // Change to increase the size of the 3D grid
  private static final char EMPTY = '-';
  private static final char[] PLAYER_SYMBOLS = { 'X', 'O', 'A', 'Z' };
  private final char[][][] board = new char[SIZE][SIZE][SIZE];
  private int currentPlayerIndex = 0;
  private final int numPlayers;

  public Game(int numPlayers) {
    this.numPlayers = numPlayers;
    initializeBoard();
    assignPlayerSymbols(numPlayers);
  }

  // Initialize the game board with empty cells
  private void initializeBoard() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        for (int k = 0; k < SIZE; k++) {
          board[i][j][k] = EMPTY;
        }
      }
    }
  }

  // Assign symbols to players
  private void assignPlayerSymbols(int numPlayers) {
    for (int i = 0; i < numPlayers; i++) {
      if (i < PLAYER_SYMBOLS.length) {
        System.out.println("Player " + (i + 1) + " symbol: " + PLAYER_SYMBOLS[i]);
      } else {
        System.out.println("Player " + (i + 1) + " symbol: (not assigned)");
      }
    }
  }

  // Start the game
  public void play() {
    displayBoard();

    while (!checkWin() && !checkDraw()) {
      char currentPlayerSymbol = PLAYER_SYMBOLS[currentPlayerIndex % numPlayers];
      Player.makeMove(board, currentPlayerSymbol);
      displayBoard();
      currentPlayerIndex++;
    }

    if (checkWin()) {
      char winningPlayerSymbol = PLAYER_SYMBOLS[(currentPlayerIndex - 1) % numPlayers];
      System.out.println("Player " + winningPlayerSymbol + " wins!");
    } else {
      System.out.println("It's a draw!");
    }
  }

  // Display the game board
  private void displayBoard() {
    for (int i = 0; i < SIZE; i++) {
      System.out.println("Level " + (i + 1) + ":");
      for (int j = 0; j < SIZE; j++) {
        for (int k = 0; k < SIZE; k++) {
          System.out.print(board[i][j][k] + " ");
        }
        System.out.println();
      }
      System.out.println();
    }
    System.out.println();
  }

  // Check for win conditions
  private boolean checkWin() {
    // Check rows, columns, and diagonals on each level
    for (int i = 0; i < SIZE; i++) {
      // Check rows
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j][0] != EMPTY && board[i][j][0] == board[i][j][1] && board[i][j][1] == board[i][j][2]) {
          return true;
        }
      }
      // Check columns
      for (int k = 0; k < SIZE; k++) {
        if (board[i][0][k] != EMPTY && board[i][0][k] == board[i][1][k] && board[i][1][k] == board[i][2][k]) {
          return true;
        }
      }
      // Check diagonals
      if (board[i][0][0] != EMPTY && board[i][0][0] == board[i][1][1] && board[i][1][1] == board[i][2][2]) {
        return true;
      }
      if (board[i][0][2] != EMPTY && board[i][0][2] == board[i][1][1] && board[i][1][1] == board[i][2][0]) {
        return true;
      }
    }
    // Check vertical columns
    for (int j = 0; j < SIZE; j++) {
      for (int k = 0; k < SIZE; k++) {
        if (board[0][j][k] != EMPTY && board[0][j][k] == board[1][j][k] && board[1][j][k] == board[2][j][k]) {
          return true;
        }
      }
    }
    // Check diagonals across levels
    if (board[0][0][0] != EMPTY && board[0][0][0] == board[1][1][1] && board[1][1][1] == board[2][2][2]) {
      return true;
    }
    if (board[0][0][2] != EMPTY && board[0][0][2] == board[1][1][1] && board[1][1][1] == board[2][2][0]) {
      return true;
    }
    if (board[0][2][0] != EMPTY && board[0][2][0] == board[1][1][1] && board[1][1][1] == board[2][0][2]) {
      return true;
    }
    if (board[0][2][2] != EMPTY && board[0][2][2] == board[1][1][1] && board[1][1][1] == board[2][0][0]) {
      return true;
    }
    return false;
  }

  // Check for draw condition
  private boolean checkDraw() {
    // Implement draw condition checking logic
    return false;
  }
}
