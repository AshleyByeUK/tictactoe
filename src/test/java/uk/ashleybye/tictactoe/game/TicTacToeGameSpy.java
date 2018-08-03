package uk.ashleybye.tictactoe.game;

import uk.ashleybye.tictactoe.TurnNotification;
import uk.ashleybye.tictactoe.TurnNotificationPublisher;

public class TicTacToeGameSpy extends TicTacToeGame {

  private String stubType;

  public TicTacToeGameSpy(String stubType) {
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
