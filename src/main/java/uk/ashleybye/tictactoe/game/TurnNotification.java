package uk.ashleybye.tictactoe.game;

import java.util.ArrayList;
import java.util.List;

public class TurnNotification {

  public String currentPlayerName = "";
  public int[] board = new int[0];
  public String gameState = "";
  public String gameResult = "";
  public int lastPositionPlayed = -1;
  public List<Integer> availablePositions = new ArrayList<>();
  public boolean userInputIsRequired = false;
}
