package uk.ashleybye.tictactoe.ui.console.gamePlay;

import java.util.ArrayList;
import java.util.List;
import uk.ashleybye.tictactoe.ui.ViewModel;

public class GamePlayViewModel implements ViewModel {

  public String gameState = "";
  public String currentPlayerName = "";
  public int[] board = new int[0];
  public String gameResult = "";
  public int lastPositionPlayed = -1;
  public List<Integer> availablePositions = new ArrayList<>();
  public boolean userInputIsRequired = false;
  public String playerOneSymbol;
  public String playerTwoSymbol;
}
