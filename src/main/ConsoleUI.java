import java.util.Scanner;

public class ConsoleUI implements UserInterface {

  public Scanner input = new Scanner(System.in); // the input Scanner

  @Override
  public void printBoard(String[] board, String lastPlayersName) {
    System.out.println("Board after " + lastPlayersName + "'s turn:\n");
    System.out.println(
        " " + formatToken(board[0]) + " | " + formatToken(board[1]) + " | " + formatToken(board[2])
            + "\n===+===+===\n"
            + " " + formatToken(board[3]) + " | " + formatToken(board[4]) + " | " + formatToken(board[5])
            + "\n===+===+===\n"
            + " " + formatToken(board[6]) + " | " + formatToken(board[7]) + " | " + formatToken(board[8])
            + "\n");
  }

  private String formatToken(String token) {
    if (!Character.isDigit(token.toCharArray()[0]))
      return token;
    else
      return " ";
  }

  @Override
  public void printAvailablePositions(String[] board) {
    System.out.println("Available positions:\n");
    System.out.println(
        " " + formatPosition(board[0]) + " | " + formatPosition(board[1]) + " | " + formatPosition(board[2])
            + "\n===+===+===\n"
            + " " + formatPosition(board[3]) + " | " + formatPosition(board[4]) + " | " + formatPosition(board[5])
            + "\n===+===+===\n"
            + " " + formatPosition(board[6]) + " | " + formatPosition(board[7]) + " | " + formatPosition(board[8])
            + "\n");
  }

  private String formatPosition(String position) {
    if (Character.isDigit(position.toCharArray()[0]))
      return position;
    else
      return " ";
  }

  @Override
  public void gameOver() {
    System.out.print("Game over\n");
  }

  @Override
  public String getInputForPlayer(String name, int maxOption) {
    System.out.print(String.format(name + ", enter [0-%d]:\n", maxOption));
    return input.next();
  }

  @Override
  public void sendMessage(String message) {
    System.out.println(message);
  }
}
