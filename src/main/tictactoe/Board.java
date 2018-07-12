package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {

  protected int[] positions = new int[9];
//  protected int movesMade;
  protected int currentPlayer;
  private int nextPlayer;

  public Board() {
    initialiseBoard();
  }

  private void initialiseBoard() {
    for (int i = 0; i < positions.length; i++)
      positions[i] = -1;
  }

  public int[] getPositions() {
    return positions;
  }

  public void setCurrentPlayer(int currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public int getCurrentPlayer() {
    return currentPlayer;
  }

  public void setNextPlayer(int nextPlayer) {
    this.nextPlayer = nextPlayer;
  }

  public int getNextPlayer() {
    return nextPlayer;
  }

  public void placeToken(int position) {
    positions[position] = currentPlayer;
  }

  public List<Integer> getAvailablePositions() {
    List<Integer> availableSpaces = new ArrayList<>();
    for (int i = 0; i < positions.length; i++)
      if (positions[i] == -1)
        availableSpaces.add(i);
    return availableSpaces;
  }

  public boolean positionIsAvailable(int pos) {
    return positions[pos] == -1;
  }

  public boolean gameIsTied() {
    int moves = 0;
    for (int p : positions)
      if (p != -1)
        moves++;
    return moves == 9;
  }

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
