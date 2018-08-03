package uk.ashleybye.tictactoe.ui.console;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.TurnNotification;
import uk.ashleybye.tictactoe.TurnNotificationPublisher;
import uk.ashleybye.tictactoe.UserInterface;

public class UserInterfaceMock implements UserInterface {

  private List<Integer> positionsToPlay;
  private int nextPositionToPlay = 0;

  public void setPlayerPositionsToPlay(Integer... positions) {
    positionsToPlay = Arrays.asList(positions);
  }

  @Override
  public boolean launch() {
    return false;
  }

  @Override
  public int getPositionToPlay(TurnNotification turnNotification) {
    return positionsToPlay.get(nextPositionToPlay++) - 1;
  }

  @Override
  public void receiveTurnPlayedNotification(TurnNotificationPublisher publisher) {

  }
}
