import java.util.Scanner;

public class ConsoleUI implements UserInterface {

  public Scanner input = new Scanner(System.in); // the input Scanner

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
  public int getPlayersMove(String name) {
    System.out.print("Enter [0-8]:\n");
    return input.nextInt();
  }
}
