package console;

import java.util.Scanner;
import tictactoe.UserInterface;

public class ConsoleUI implements UserInterface {

  public Scanner input = new Scanner(System.in);
  private String[] playerTokens = new String[2];

  public ConsoleUI() {
    playerTokens[0] = "X";
    playerTokens[1] = "O";
  }

  @Override
  public void setPlayerOneToken(String token) {
    playerTokens[0] = token;
  }

  @Override
  public void setPlayerTwoToken(String token) {
    playerTokens[1] = token;
  }

  @Override
  public void showBoardStateForLastTurn(int[] board, String lastPlayersName) {
    System.out.println("Board after " + lastPlayersName + "'s turn:\n");
    System.out.println(
        " " + formatToken(board[0]) + " | " + formatToken(board[1]) + " | " + formatToken(board[2])
            + "\n===+===+===\n"
            + " " + formatToken(board[3]) + " | " + formatToken(board[4]) + " | " + formatToken(board[5])
            + "\n===+===+===\n"
            + " " + formatToken(board[6]) + " | " + formatToken(board[7]) + " | " + formatToken(board[8])
            + "\n");
  }

  String formatToken(int player) {
    if (player != -1)
      return playerTokens[player];
    else
      return " ";
  }

  @Override
  public void showAvailablePositions(int[] board) {
    System.out.println("Available positions:\n");
    System.out.println(
        " " + formatPosition(board, 0) + " | " + formatPosition(board, 1) + " | " + formatPosition(board, 2)
            + "\n===+===+===\n"
            + " " + formatPosition(board, 3) + " | " + formatPosition(board, 4) + " | " + formatPosition(board, 5)
            + "\n===+===+===\n"
            + " " + formatPosition(board, 6) + " | " + formatPosition(board, 7) + " | " + formatPosition(board, 8)
            + "\n");
  }

  private String formatPosition(int[] board, int position) {
    if (board[position] == -1)
      return Integer.toString(position);
    else
      return " ";
  }

  @Override
  public void showGameOver() {
    System.out.print("Game over\n");
  }

  @Override
  public String getInputForPlayer(String name, int maxOption) {
    System.out.print(String.format(name + ", enter [0-%d]:\n", maxOption));
    return input.next();
  }

  @Override
  public void showMessage(String message) {
    System.out.println(message);
  }
}
