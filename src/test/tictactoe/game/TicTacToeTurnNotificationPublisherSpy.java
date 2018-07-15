package tictactoe.game;

import tictactoe.TurnNotification;

public class TicTacToeTurnNotificationPublisherSpy extends TicTacToeTurnNotificationPublisher {

  public boolean getTurnNotificationWasCalled;

  @Override
  public TurnNotification getTurnNotification() {
    TicTacToeTurnNotification turnNotification = new TicTacToeTurnNotification();
    turnNotification.turnResult = "user_input_required";
    getTurnNotificationWasCalled = true;
    return turnNotification;
  }
}
