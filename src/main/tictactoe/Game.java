package tictactoe;

public class Game {

  int[] board = new int[9];
  private Player[] players;
  private int currentPlayer;
  private UserInterface userInterface;
  private int movesMade;

  public Game(Player player1, Player player2, UserInterface userInterface) {
    this.userInterface = userInterface;
    players = new Player[]{player1, player2};
    currentPlayer = 0;
    initialiseBoard();
  }

  private void initialiseBoard() {
    for (int i = 0; i < board.length; i++)
      board[i] = -1;
  }

  public void setCurrentPlayer(int player) {
    currentPlayer = player;
  }

  public int getCurrentPlayer() {
    return currentPlayer;
  }

  public int[] getBoard() {
    return board;
  }

  public UserInterface getUserInterface() {
    return userInterface;
  }

  public void play() {
    while (!gameIsOver() && !gameIsTied())
      userInterface.showBoardStateForLastTurn(board, nextTurn());
    userInterface.showGameOver();
  }

  public boolean gameIsOver() {
    return isGameOver(0, 1, 2) ||
            isGameOver(3, 4, 5) ||
            isGameOver(6, 7, 8) ||
            isGameOver(0, 3, 6) ||
            isGameOver(1, 4, 7) ||
            isGameOver(2, 5, 8) ||
            isGameOver(0, 4, 8) ||
            isGameOver(2, 4, 6);
  }

  private boolean isGameOver(int pos1, int pos2, int pos3) {
    return board[pos1] != -1 && board[pos2] != -1 && board[pos3] != -1
        && board[pos1] == board[pos2] && board[pos2] == board[pos3];
  }

  public boolean gameIsTied() {
    return movesMade == 9;
  }

  public String nextTurn() {
    movesMade++;
    int spot = players[currentPlayer].playTurn(this);
    board[spot] = currentPlayer;
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

  public boolean positionIsAvailable(int spot) {
    return board[spot] == -1;
  }
}
