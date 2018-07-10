package tictacttoe;

public class Game {

  public String[] board = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
  private Player[] players;
  public int currentPlayer;
  private UserInterface userInterface;
  private int movesMade;

  public Game(Player player1, Player player2, UserInterface userInterface) {
    this.userInterface = userInterface;
    players = new Player[]{player1, player2};
    currentPlayer = 0;
  }

  public String[] getBoard() {
    return board;
  }

  public void setFirstPlayer(int player) {
    currentPlayer = player;
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
    return board[0] == board[1] && board[1] == board[2] ||
        board[3] == board[4] && board[4] == board[5] ||
        board[6] == board[7] && board[7] == board[8] ||
        board[0] == board[3] && board[3] == board[6] ||
        board[1] == board[4] && board[4] == board[7] ||
        board[2] == board[5] && board[5] == board[8] ||
        board[0] == board[4] && board[4] == board[8] ||
        board[2] == board[4] && board[4] == board[6];
  }

  public boolean gameIsTied() {
    return movesMade == 9;
  }

  public String nextTurn() {
    movesMade++;
    int spot = players[currentPlayer].playTurn(this);
    board[spot] = players[currentPlayer].getToken();
    String lastPlayersName = players[currentPlayer].getName();
    currentPlayer = nextPlayer();
    return lastPlayersName;
  }

  public int nextPlayer() {
    if (currentPlayer == 0)
      return 1;
    else
      return 0;
  }

  public boolean positionIsAvailable(int spot) {
    String s = board[spot];
    return s != "X" && s != "O";
  }
}
