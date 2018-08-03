package uk.ashleybye.tictactoe.ui.console.gamePlay;

import java.util.stream.Collectors;
import uk.ashleybye.tictactoe.ui.View;

public class GamePlayView implements View<GamePlayViewModel> {

  public static final String VALID_SYMBOLS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@£#$%^&*()?";
  public static final String PLAYER_ONE_SYMBOL = "X";
  public static final String PLAYER_TWO_SYMBOL = "O";
  private String[] playerSymbols = new String[2];
  private boolean isFirstTurn = true;

  public GamePlayView() {
    playerSymbols[0] = PLAYER_ONE_SYMBOL;
    playerSymbols[1] = PLAYER_TWO_SYMBOL;
  }

  public void setPlayerOneSymbol(String symbol) {
    playerSymbols[0] = symbol;
  }

  public void setPlayerTwoSymbol(String symbol) {
    playerSymbols[1] = symbol;
  }

  @Override
  public void show(GamePlayViewModel viewModel) {
    String output = formatCurrentBoardState(viewModel)
        + formatTurnResult(viewModel)
        + formatPositionNotAvailableMessage(viewModel)
        + formatAvailablePositions(viewModel)
        + formatGameOver(viewModel);

    isFirstTurn = false;
    System.out.print(output);
  }

  private String formatCurrentBoardState(GamePlayViewModel vm) {
    if (isFirstTurn || vm.userInputIsRequired)
      return formatBoard(vm);
    else
      return "";
  }

  private String formatBoard(GamePlayViewModel vm) {
    int[] pos = vm.board;
    return "\n " + formatSymbol(pos[0]) + " | " + formatSymbol(pos[1]) + " | " + formatSymbol(pos[2])
        + "\n===+===+===\n"
        + " " + formatSymbol(pos[3]) + " | " + formatSymbol(pos[4]) + " | " + formatSymbol(pos[5])
        + "\n===+===+===\n"
        + " " + formatSymbol(pos[6]) + " | " + formatSymbol(pos[7]) + " | " + formatSymbol(pos[8])
        + "\n";
  }

  private String formatSymbol(int player) {
    if (player != -1)
      return playerSymbols[player];
    else
      return " ";
  }

  private String formatTurnResult(GamePlayViewModel vm) {
    if (vm.userInputIsRequired)
      return String.format("\n%s played in position %d.\n",
          vm.currentPlayerName,
          vm.lastPositionPlayed + 1);
    else
      return "";
  }

  private String formatPositionNotAvailableMessage(GamePlayViewModel vm) {
    if (vm.userInputIsRequired)
      return "\nPlease enter an available position.\n";
    else
      return "";
  }

  private String formatAvailablePositions(GamePlayViewModel vm) {
    if (vm.userInputIsRequired)
      return String.format("\nAvailable: [%s]\n%s ",
          vm.availablePositions
              .stream()
              .map(i -> i + 1)
              .map(Object::toString)
              .collect(Collectors.joining(", ")),
          vm.currentPlayerName);
    else
      return "";
  }

  private String formatGameOver(GamePlayViewModel vm) {
    if (vm.gameState.equals("game_over"))
      return String.format("\nGAME OVER. %s\n",
          vm.gameResult.equals("winner") ? vm.currentPlayerName + " is the winner!" : "It's a tie!");
    else
      return "";
  }

  @Override
  public char[] getInputOptions() {
    return "123456789".toCharArray();
  }
}
