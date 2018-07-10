package console;

import java.util.Scanner;
import tictactoe.Board;
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
  public void showBoardStateForLastTurn(Board board, String lastPlayersName) {
    int[] pos = board.getPositions();
    System.out.println("Board after " + lastPlayersName + "'s turn:\n");
    System.out.println(
        " " + formatToken(pos[0]) + " | " + formatToken(pos[1]) + " | " + formatToken(pos[2])
            + "\n===+===+===\n"
            + " " + formatToken(pos[3]) + " | " + formatToken(pos[4]) + " | " + formatToken(pos[5])
            + "\n===+===+===\n"
            + " " + formatToken(pos[6]) + " | " + formatToken(pos[7]) + " | " + formatToken(pos[8])
            + "\n");
  }

  String formatToken(int player) {
    if (player != -1)
      return playerTokens[player];
    else
      return " ";
  }

  @Override
  public void showAvailablePositions(Board board) {
    int[] pos = board.getPositions();
    System.out.println("Available positions:\n");
    System.out.println(
        " " + formatPosition(pos, 0) + " | " + formatPosition(pos, 1) + " | " + formatPosition(pos, 2)
            + "\n===+===+===\n"
            + " " + formatPosition(pos, 3) + " | " + formatPosition(pos, 4) + " | " + formatPosition(pos, 5)
            + "\n===+===+===\n"
            + " " + formatPosition(pos, 6) + " | " + formatPosition(pos, 7) + " | " + formatPosition(pos, 8)
            + "\n");
  }

  private String formatPosition(int[] positions, int pos) {
    if (positions[pos] == -1)
      return Integer.toString(pos);
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
