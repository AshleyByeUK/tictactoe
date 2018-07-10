package tictactoe;

public interface UserInterface {

  void showBoardStateForLastTurn(Board board, String lastPlayersName);

  void showAvailablePositions(Board board);

  void showMessage(String message);

  void showGameOver();

  String getInputForPlayer(String name, int maxOption);

  void setPlayerOneToken(String token);

  void setPlayerTwoToken(String token);
}
