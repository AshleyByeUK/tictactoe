package uk.ashleybye.tictactoe.game.impl;

import uk.ashleybye.tictactoe.game.TurnNotification;
import uk.ashleybye.tictactoe.game.TurnNotificationPublisher;

public class GameImplSpy extends GameImpl {

  private String stubType;

  public GameImplSpy(String stubType) {
    super(null, null);
    this.stubType = stubType;
  }

  @Override
  public boolean play(TurnNotificationPublisher publisher) {
    TurnNotification notification = new TurnNotification();
    notification.userInputIsRequired = !stubType.equals("computer");
    publisher.notify(notification);
    return true;
  }
}
