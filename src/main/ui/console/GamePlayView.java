package ui.console;

import java.util.stream.Collectors;
import ui.View;
import ui.ViewModel;

public class GamePlayView implements View {

  public static final String PLAYER_ONE_TOKEN = "X";
  public static final String PLAYER_TWO_TOKEN = "O";
  private String[] playerTokens = new String[2];
  private boolean isFirstTurn = true;

  public GamePlayView() {
    playerTokens[0] = PLAYER_ONE_TOKEN;
    playerTokens[1] = PLAYER_TWO_TOKEN;
  }

  public void setPlayerOneToken(String token) {
    playerTokens[0] = token;
  }

  public void setPlayerTwoToken(String token) {
    playerTokens[1] = token;
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
    return "\n " + formatToken(pos[0]) + " | " + formatToken(pos[1]) + " | " + formatToken(pos[2])
        + "\n===+===+===\n"
        + " " + formatToken(pos[3]) + " | " + formatToken(pos[4]) + " | " + formatToken(pos[5])
        + "\n===+===+===\n"
        + " " + formatToken(pos[6]) + " | " + formatToken(pos[7]) + " | " + formatToken(pos[8])
        + "\n";
  }

  private String formatToken(int player) {
    if (player != -1)
      return playerTokens[player];
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
