package uk.ashleybye.tictactoe.ui.console;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.game.GamePlayBoundary;
import uk.ashleybye.tictactoe.game.GameState;

public class ConsoleGamePlayBoundaryMock implements GamePlayBoundary {

  private List<Integer> positionsToPlay;
  private int nextPositionToPlay = 0;
  public GameState gameState;

  public void setPlayerPositionsToPlay(Integer... positions) {
    positionsToPlay = Arrays.asList(positions);
  }

  @Override
  public int getPositionToPlay() {
    return positionsToPlay.get(nextPositionToPlay++) - 1;
  }

  @Override
  public void updateDisplay(GameState gameState) {
    this.gameState = gameState;
  }
}
