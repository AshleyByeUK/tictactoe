package tictactoe;

public class Game {

  private Board board;
  private Player[] players;
  private int currentPlayer;
  private UserInterface userInterface;

  public Game(Player player1, Player player2, UserInterface userInterface) {
    board = new Board();
    this.userInterface = userInterface;
    players = new Player[]{player1, player2};
    currentPlayer = 0;
  }

  public void setFirstPlayer(int player) {
    currentPlayer = player;
  }

  public void play() {
    while (!board.gameIsOver() && !board.gameIsTied())
      userInterface.showBoardStateForLastTurn(board, nextTurn());
    userInterface.showGameOver();
  }

  public String nextTurn() {
    board.setCurrentPlayer(currentPlayer);
    board.setNextPlayer(getNextPlayer());
    players[currentPlayer].playTurn(board, userInterface);
    String lastPlayersName = players[currentPlayer].getName();
    currentPlayer = getNextPlayer();
    return lastPlayersName;
  }

  public int getNextPlayer() {
    if (currentPlayer == 0)
      return 1;
    else
      return 0;
  }
}
