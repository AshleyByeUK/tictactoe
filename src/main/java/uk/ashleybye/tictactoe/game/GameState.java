package uk.ashleybye.tictactoe.game;

public class GameState {

  private Board board;
  private int currentPlayer;
  private int nextPlayer;
  private Player[] players;
  private String gameStatus;
  private String gameResult;
  private boolean userInputRequired = false;

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public int getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(int currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public int getNextPlayer() {
    return nextPlayer;
  }

  public void setNextPlayer(int nextPlayer) {
    this.nextPlayer = nextPlayer;
  }

  public Player[] getPlayers() {
    return players;
  }

  public void setPlayers(Player[] players) {
    this.players = players;
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
