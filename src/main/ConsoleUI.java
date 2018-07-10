import java.util.Scanner;

public class ConsoleUI implements UserInterface {

  public Scanner input = new Scanner(System.in); // the input Scanner

  @Override
  public void printBoard(String[] board, String name) {
    System.out.println("Board for " + name + "'s turn:");
    System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + "\n===+===+===\n"
        + " " + board[3] + " | " + board[4] + " | " + board[5] + "\n===+===+===\n"
        + " " + board[6] + " | " + board[7] + " | " + board[8] + "\n");
  }

  @Override
  public void gameOver() {
    System.out.print("Game over\n");
  }

  @Override
  public String getInputForPlayer(String name, int maxOption) {
    System.out.print(String.format("Enter [0-%d]:\n", maxOption));
    return input.next();
  }

  @Override
  public void sendMessage(String message) {
    System.out.println(message);
  }
}
