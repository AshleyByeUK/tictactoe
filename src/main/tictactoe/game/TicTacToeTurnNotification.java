package tictactoe.game;

import java.util.List;
import tictactoe.TurnNotification;

public class TicTacToeTurnNotification implements TurnNotification {

  public String currentPlayerName;
  public String turnResult;
  public int[] board;
  public String gameState;
  public String gameResult;
  public int lastPositionPlayed;
  public List<Integer> availablePositions;
}
