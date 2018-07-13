package tictactoe;

import java.util.List;

public class TurnResponseModel {

  public int currentPlayer;
  public String currentPlayerName;
  public String turnResult;
  public int[] board;
  public String gameState;
  public String gameResult;
  public int lastPositionPlayed;
  public List<Integer> availablePositions;
}
