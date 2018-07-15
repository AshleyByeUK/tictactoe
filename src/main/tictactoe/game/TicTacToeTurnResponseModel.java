package tictactoe.game;

import java.util.List;
import tictactoe.TurnResponseModel;

public class TicTacToeTurnResponseModel implements TurnResponseModel {

  public int currentPlayer;
  public String currentPlayerName;
  public String turnResult;
  public int[] board;
  public String gameState;
  public String gameResult;
  public int lastPositionPlayed;
  public List<Integer> availablePositions;
}
