package uk.ashleybye.tictactoe.game;

public class GameState {

  private final Board board;
  private final int currentPlayer;
  private final int nextPlayer;

  public GameState(Board board, int currentPlayer, int nextPlayer) {
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.nextPlayer = nextPlayer;
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
}
