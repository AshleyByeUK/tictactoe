package uk.ashleybye.tictactoe.game;

public class GameState {

  private final Board board;
  private final int currentPlayer;
  private final int nextPlayer;
  private final Player[] players;
  private String gameStatus;
  private String gameResult;
  private boolean userInputRequired = false;

  public GameState(Board board, int currentPlayer, int nextPlayer, Player[] players) {
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.nextPlayer = nextPlayer;
    this.players = players;
  }

  public Board getBoard() {
    return board;
  }

  public int getCurrentPlayer() {
    return currentPlayer;
  }

  public int getNextPlayer() {
    return nextPlayer;
  }

  public Player[] getPlayers() {
    return players;
  }

  public String getGameStatus() {
    return gameStatus;
  }

  public void setGameStatus(String gameStatus) {
    this.gameStatus = gameStatus;
  }

  public String getGameResult() {
    return gameResult;
  }

  public void setGameResult(String gameResult) {
    this.gameResult = gameResult;
  }

  public boolean isUserInputRequired() {
    return userInputRequired;
  }

  public void setUserInputRequired(boolean userInputRequired) {
    this.userInputRequired = userInputRequired;
  }
}
