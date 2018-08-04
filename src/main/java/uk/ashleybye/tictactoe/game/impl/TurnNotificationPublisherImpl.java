package uk.ashleybye.tictactoe.game.impl;


import uk.ashleybye.tictactoe.game.TurnNotification;
import uk.ashleybye.tictactoe.game.TurnNotificationPublisher;
import uk.ashleybye.tictactoe.game.TurnNotificationSubscriber;

public class TurnNotificationPublisherImpl implements TurnNotificationPublisher {

  private TurnNotificationSubscriber subscriber;
  private TurnNotification turnNotification;

  @Override
  public void subscribe(TurnNotificationSubscriber subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void notify(TurnNotification notification) {
    turnNotification = notification;
    subscriber.receiveTurnPlayedNotification(this);
  }

  @Override
  public TurnNotification getTurnNotification() {
    return turnNotification;
  }
}
