package tictactoe.game;

public class TicTacToeTurnNotificationPublisherSpy extends TicTacToeTurnNotificationPublisher {

  public boolean getTurnNotificationWasCalled;

  @Override
  public TicTacToeTurnNotification getTurnNotification() {
    TicTacToeTurnNotification turnNotification = new TicTacToeTurnNotification();
    turnNotification.turnResult = "user_input_required";
    getTurnNotificationWasCalled = true;
    return turnNotification;
  }
}
