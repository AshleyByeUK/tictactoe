package tictactoe.game;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeTurnNotification {

  public String currentPlayerName = "";
  public String turnResult = "";
  public int[] board = new int[0];
  public String gameState = "";
  public String gameResult = "";
  public int lastPositionPlayed = -1;
  public List<Integer> availablePositions = new ArrayList<>();
}
