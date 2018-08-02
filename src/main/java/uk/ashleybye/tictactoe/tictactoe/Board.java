package uk.ashleybye.tictactoe.tictactoe;

import java.util.List;

public interface Board {

  int getCurrentPlayer();

  void setCurrentPlayer(int currentPlayer);

  int getNextPlayer();

  void setNextPlayer(int nextPlayer);

  int[] getPositions();

  List<Integer> getAvailablePositions();

  int getLastPositionPlayed();

  void placeSymbolAtPosition(int position);

  boolean positionIsAvailable(int pos);

  boolean gameIsTied();

  boolean gameIsWon();
}
