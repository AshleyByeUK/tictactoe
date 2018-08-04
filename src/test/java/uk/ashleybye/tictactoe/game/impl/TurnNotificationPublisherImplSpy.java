package uk.ashleybye.tictactoe.game.impl;

import uk.ashleybye.tictactoe.game.TurnNotification;

public class TurnNotificationPublisherImplSpy extends TurnNotificationPublisherImpl {

  public boolean getTurnNotificationWasCalled;

  @Override
  public TurnNotification getTurnNotification() {
    TurnNotification turnNotification = new TurnNotification();
    turnNotification.board = BoardImplMock.configureBoard().positions;
    turnNotification.userInputIsRequired = true;
    getTurnNotificationWasCalled = true;
    return turnNotification;
  }
}
