package uk.ashleybye.tictactoe.game;

import uk.ashleybye.tictactoe.TurnNotification;

public class TicTacToeTurnNotificationPublisherSpy extends TicTacToeTurnNotificationPublisher {

  public boolean getTurnNotificationWasCalled;

  @Override
  public TurnNotification getTurnNotification() {
    TurnNotification turnNotification = new TurnNotification();
    turnNotification.board = TicTacToeBoardMock.configureBoard().positions;
    turnNotification.userInputIsRequired = true;
    getTurnNotificationWasCalled = true;
    return turnNotification;
  }
}
