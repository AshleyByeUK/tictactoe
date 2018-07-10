public interface UserInterface {

  void printBoard(String[] board, String lastPlayersName);

  void printAvailablePositions(String[] board);

  void sendMessage(String message);

  String getInputForPlayer(String name, int maxOption);

  void gameOver();
}
