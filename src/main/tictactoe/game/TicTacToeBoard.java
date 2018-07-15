package tictactoe.game;

import java.util.ArrayList;
import java.util.List;
import tictactoe.Board;

public class TicTacToeBoard implements Board {

  int[] positions = new int[9];
  private int currentPlayer;
  private int nextPlayer;
  private int lastPositionPlayed;

  public TicTacToeBoard() {
    initialiseBoard();
  }

  private void initialiseBoard() {
    for (int i = 0; i < positions.length; i++)
      positions[i] = -1;
  }

  @Override
  public int[] getPositions() {
    return positions;
  }

  @Override
  public int getCurrentPlayer() {
    return currentPlayer;
  }

  @Override
  public void setCurrentPlayer(int currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  @Override
  public int getNextPlayer() {
    return nextPlayer;
  }

  @Override
  public void setNextPlayer(int nextPlayer) {
    this.nextPlayer = nextPlayer;
  }

  @Override
  public int getLastPositionPlayed() {
    return lastPositionPlayed;
  }

  @Override
  public void placeSymbolAtPosition(int position) {
    positions[position] = currentPlayer;
    lastPositionPlayed = position;
  }

  @Override
  public List<Integer> getAvailablePositions() {
    List<Integer> ap = new ArrayList<>();
    for (int i = 0; i < positions.length; i++)
      if (positions[i] == -1)
        ap.add(i);
    return ap;
  }

  @Override
  public boolean positionIsAvailable(int pos) {
    return positions[pos] == -1;
  }

  @Override
  public boolean gameIsTied() {
    int moves = 0;
    for (int p : positions)
      if (p != -1)
        moves++;
    return moves == 9;
  }

  @Override
  public boolean gameIsWon() {
    return isWinningSequence(0, 1, 2) ||
        isWinningSequence(3, 4, 5) ||
        isWinningSequence(6, 7, 8) ||
        isWinningSequence(0, 3, 6) ||
        isWinningSequence(1, 4, 7) ||
        isWinningSequence(2, 5, 8) ||
        isWinningSequence(0, 4, 8) ||
        isWinningSequence(2, 4, 6);
  }

  private boolean isWinningSequence(int pos1, int pos2, int pos3) {
    return positions[pos1] != -1 && positions[pos2] != -1 && positions[pos3] != -1
        && positions[pos1] == positions[pos2] && positions[pos2] == positions[pos3];
  }
}
