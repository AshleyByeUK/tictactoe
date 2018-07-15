package ui.console.gamePlay;

import java.util.stream.Collectors;
import ui.View;
import ui.ViewModel;

public class GamePlayView implements View {

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
  public void show(ViewModel viewModel) {
    GamePlayViewModel vm = (GamePlayViewModel) viewModel;
    String output = formatCurrentBoardState(vm)
        + formatTurnResult(vm)
        + formatPositionNotAvailableMessage(vm)
        + formatAvailablePositions(vm)
        + formatGameOver(vm);

    isFirstTurn = false;
    System.out.print(output);
  }

  private String formatCurrentBoardState(GamePlayViewModel viewModel) {
    if (isFirstTurn || viewModel.turnResult.equals("turn_complete"))
      return formatBoard(viewModel);
    else
      return "";
  }

  private String formatBoard(GamePlayViewModel viewModel) {
    int[] pos = viewModel.board;
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

  private String formatTurnResult(GamePlayViewModel viewModel) {
    if (viewModel.turnResult.equals("turn_complete"))
      return String.format("\n%s played in position %d.\n",
          viewModel.currentPlayerName,
          viewModel.lastPositionPlayed + 1);
    else
      return "";
  }

  private String formatPositionNotAvailableMessage(GamePlayViewModel viewModel) {
    if (viewModel.userPositionIsTaken)
      return "\nPlease enter an available position.\n";
    else
      return "";
  }

  private String formatAvailablePositions(GamePlayViewModel viewModel) {
    if (viewModel.userInputIsRequired)
      return String.format("\nAvailable: [%s]\n%s ",
          viewModel.availablePositions
              .stream()
              .map(i -> i + 1)
              .map(Object::toString)
              .collect(Collectors.joining(", ")),
          viewModel.currentPlayerName);
    else
      return "";
  }

  private String formatGameOver(GamePlayViewModel viewModel) {
    if (viewModel.gameState.equals("game_over"))
      return String.format("\nGAME OVER. %s\n",
          viewModel.gameResult.equals("winner") ? viewModel.currentPlayerName + " is the winner!" : "It's a tie!");
    else
      return "";
  }
}
