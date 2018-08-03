package uk.ashleybye.tictactoe.tictactoe;

import java.util.List;

public interface Board {

  int[] getPositions();

  List<Integer> getAvailablePositions();

  int getLastPositionPlayed();

  void placeSymbolAtPosition(int position, int player);

  boolean positionIsAvailable(int pos);

  boolean gameIsTied();

  boolean gameIsWon();
}
