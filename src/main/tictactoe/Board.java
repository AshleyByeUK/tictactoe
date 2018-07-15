package tictactoe;

import java.util.List;

public interface Board {

  int[] getPositions();

  void setCurrentPlayer(int currentPlayer);

  int getCurrentPlayer();

  void setNextPlayer(int nextPlayer);

  int getNextPlayer();

  int getLastPositionPlayed();

  void placeToken(int position);

  List<Integer> getAvailablePositions();

  boolean positionIsAvailable(int pos);

  boolean gameIsTied();

  boolean gameIsWon();
}
