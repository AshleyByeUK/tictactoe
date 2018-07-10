package tictacttoe;

public interface UserInterface {

  void showBoardStateForLastTurn(int[] board, String lastPlayersName);

  void showAvailablePositions(int[] board);

  void showMessage(String message);

  void showGameOver();

  String getInputForPlayer(String name, int maxOption);

  void setPlayerOneToken(String token);

  void setPlayerTwoToken(String token);
}
