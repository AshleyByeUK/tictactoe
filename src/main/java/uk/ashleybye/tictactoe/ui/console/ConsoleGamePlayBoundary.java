package uk.ashleybye.tictactoe.ui.console;

import java.util.Scanner;
import java.util.stream.Collectors;
import uk.ashleybye.tictactoe.game.GamePlayBoundary;
import uk.ashleybye.tictactoe.game.GameState;

public class ConsoleGamePlayBoundary implements GamePlayBoundary {

  static final String VALID_SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ!@£#$%^&*()?";
  private static final String POSITION_OPTIONS = "123456789";

  private final Scanner input;
  private boolean isFirstTurn = true;

  public ConsoleGamePlayBoundary(Scanner input) {
    this.input = input;
  }

  @Override
  public int getPositionToPlay(GameState gameState) {
    return Integer.valueOf(InputUtilities.getUppercaseInput(
        input, POSITION_OPTIONS.toCharArray(), buildDisplay(gameState))) - 1;
  }

  @Override
  public void updateDisplay(GameState gameState) {
    System.out.print(buildDisplay(gameState));
    isFirstTurn = false;
  }

  private String buildDisplay(GameState gameState) {
    return clearConsole()
        + formatTurnResult(gameState)
        + formatCurrentBoardState(gameState)
        + formatPositionNotAvailableMessage(gameState)
        + formatAvailablePositions(gameState)
        + formatGameOver(gameState);
  }

  private String clearConsole() {
    String newLines = "";
    for (int i = 0; i < 80; i++)
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
      return formatBoard(s, s.getGameStatus().equals("game_over"));
    else
      return "";
  }

  private String formatBoard(GameState s, boolean gameOver) {
    String[] sym = new String[]{s.getPlayers()[0].getSymbol(), s.getPlayers()[1].getSymbol()};
    int[] pos = s.getBoard().getPositions();
    if (gameOver)
      return "\n " + symbol(pos[0], sym) + " | " + symbol(pos[1], sym) + " | " + symbol(pos[2], sym)
          + "\n===+===+===\n"
          + " " + symbol(pos[3], sym) + " | " + symbol(pos[4], sym) + " | " + symbol(pos[5], sym)
          + "\n===+===+===\n"
          + " " + symbol(pos[6], sym) + " | " + symbol(pos[7], sym) + " | " + symbol(pos[8], sym)
          + "\n";
    else
      return "\n " + symbol(pos[0], sym, "1") + " | " + symbol(pos[1], sym, "2") + " | " + symbol(pos[2], sym, "3")
          + "\n===+===+===\n"
          + " " + symbol(pos[3], sym, "4") + " | " + symbol(pos[4], sym, "5") + " | " + symbol(pos[5], sym,"6")
          + "\n===+===+===\n"
          + " " + symbol(pos[6], sym, "7") + " | " + symbol(pos[7], sym, "8") + " | " + symbol(pos[8], sym, "9")
          + "\n";
  }

  private String symbol(int player, String[] playerSymbols) {
    return symbol(player, playerSymbols, " ");
  }

  private String symbol(int player, String[] playerSymbols, String position) {
    if (player != -1)
      return playerSymbols[player];
    else
      return position;
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
