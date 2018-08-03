package uk.ashleybye.tictactoe.tictactoe.game;

public class TicTacToeTurnNotificationPublisherSpy extends TicTacToeTurnNotificationPublisher {

  public boolean getTurnNotificationWasCalled;

  @Override
  public TicTacToeTurnNotification getTurnNotification() {
    TicTacToeTurnNotification turnNotification = new TicTacToeTurnNotification();
    turnNotification.userInputIsRequired = true;
    getTurnNotificationWasCalled = true;
    return turnNotification;
  }
}
