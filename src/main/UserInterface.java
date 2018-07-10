public interface UserInterface {

  void printBoard(String[] board, String name);

  void gameOver();

  String getInputForPlayer(String name, int maxOption);

  void sendMessage(String message);
}
