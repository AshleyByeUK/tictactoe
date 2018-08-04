package uk.ashleybye.tictactoe.game;

public class GameState {

  private final Board board;
  private final int currentPlayer;
  private final int nextPlayer;
  private final Player[] players;

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
}
