package tictacttoe;

public interface UserInterface {

  void showBoardStateForLastTurn(String[] board, String lastPlayersName);

  void showAvailablePositions(String[] board);

  void showMessage(String message);

  void showGameOver();

  String getInputForPlayer(String name, int maxOption);
}
