package uk.ashleybye.tictactoe.ui.console;

import java.util.Scanner;
import java.util.stream.Collectors;
import uk.ashleybye.tictactoe.game.GamePlayBoundary;
import uk.ashleybye.tictactoe.game.GameState;

public class ConsoleGamePlayBoundary implements GamePlayBoundary {

  public static final String VALID_SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@£#$%^&*()?";
  public static final String POSITION_OPTIONS = "123456789";

  private final Scanner input;
  private boolean isFirstTurn = true;

  public ConsoleGamePlayBoundary(Scanner input) {
    this.input = input;
  }

  @Override
  public int getPositionToPlay() {
    return Integer.valueOf(InputUtilities.getUppercaseInput(input, POSITION_OPTIONS.toCharArray())) - 1;
  }

  @Override
  public void updateDisplay(GameState gameState) {
    String display = clearConsole()
        + formatTurnResult(gameState)
        + formatCurrentBoardState(gameState)
        + formatPositionNotAvailableMessage(gameState)
        + formatAvailablePositions(gameState)
        + formatGameOver(gameState);

    isFirstTurn = false;
    System.out.print(display);
  }

  private String clearConsole() {
    String newLines = "";
    for (int i = 0; i < 32; i++)
      newLines += "\n";
    return newLines;
  }

  private String formatTurnResult(GameState s) {
    if (s.isUserInputRequired() && s.getBoard().getLastPositionPlayed() > -1)
      return String.format("\n%s played in position %d.\n",
          s.getPlayers()[s.getNextPlayer()].getName(),
          s.getBoard().getLastPositionPlayed() + 1);
    else
      return "";
  }

  private String formatCurrentBoardState(GameState s) {
    if (isFirstTurn || s.isUserInputRequired() || s.getGameStatus().equals("game_over"))
      return formatBoard(s);
    else
      return "";
  }

  private String formatBoard(GameState s) {
    String[] sym = new String[]{s.getPlayers()[0].getSymbol(), s.getPlayers()[1].getSymbol()};
    int[] pos = s.getBoard().getPositions();
    return "\n " + formatSymbol(pos[0], sym) + " | " + formatSymbol(pos[1], sym) + " | " + formatSymbol(pos[2], sym)
        + "\n===+===+===\n"
        + " " + formatSymbol(pos[3], sym) + " | " + formatSymbol(pos[4], sym) + " | " + formatSymbol(pos[5], sym)
        + "\n===+===+===\n"
        + " " + formatSymbol(pos[6], sym) + " | " + formatSymbol(pos[7], sym) + " | " + formatSymbol(pos[8], sym)
        + "\n";
  }

  private String formatSymbol(int player, String[] playerSymbols) {
    if (player != -1)
      return playerSymbols[player];
    else
      return " ";
  }

  private String formatPositionNotAvailableMessage(GameState s) {
    if (s.isUserInputRequired())
      return "\nPlease enter an available position.\n";
    else
      return "";
  }

  private String formatAvailablePositions(GameState s) {
    if (s.isUserInputRequired())
      return String.format("\nAvailable: [%s]\n%s ",
          s.getBoard().getAvailablePositions()
              .stream()
              .map(i -> i + 1)
              .map(Object::toString)
              .collect(Collectors.joining(", ")),
          s.getPlayers()[s.getCurrentPlayer()].getName());
    else
      return "";
  }

  private String formatGameOver(GameState s) {
    String name = s.getPlayers()[s.getCurrentPlayer()].getName();
    if (s.getGameStatus().equals("game_over"))
      return String.format("\nGAME OVER. %s\n",
          s.getGameResult().equals("winner") ? name + " is the winner!" : "It's a tie!");
    else
      return "";
  }
}
