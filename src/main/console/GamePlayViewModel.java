package console;

import java.util.ArrayList;
import java.util.List;

public class GamePlayViewModel {

  public String gameState = "";
  public String turnResult = "";
  public int currentPlayer = -1;
  public String currentPlayerName = "";
  public int[] board = new int[0];
  public String gameResult = "";
  public int lastPositionPlayed = -1;
  public List<Integer> availablePositions = new ArrayList<>();

  public boolean userInputIsRequired = false;
}
