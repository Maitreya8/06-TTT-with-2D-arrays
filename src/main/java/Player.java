import java.util.Scanner;

class Player {
  private static final Scanner scanner = new Scanner(System.in);

  // Allow player to make a move
  public static void makeMove(char[][][] board, char symbol) {
    int level, row, col;

    do {
      System.out.println("Enter level, row, and column numbers (0-" + (board.length - 1) + "): ");
      level = scanner.nextInt();
      row = scanner.nextInt();
      col = scanner.nextInt();

      if (!isValidMove(board, level, row, col)) {
        System.out.println("Invalid move. Try again.");
      }
    } while (!isValidMove(board, level, row, col));

  board[level][row][col] = symbol;
  }

// Check if the move is valid
  private static boolean isValidMove(char[][][] board, int level, int row, int col) {
    int size = board.length;
      // Check if the level, row, and column are within bounds
    return level >= 0 && level < size && row >= 0 && row < size && col >= 0 && col < size && board[level][row][col] == '-';
  }
}

  