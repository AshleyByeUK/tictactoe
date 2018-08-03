package uk.ashleybye.tictactoe.game;


import uk.ashleybye.tictactoe.TurnNotification;
import uk.ashleybye.tictactoe.TurnNotificationPublisher;
import uk.ashleybye.tictactoe.TurnNotificationSubscriber;

public class TicTacToeTurnNotificationPublisher implements TurnNotificationPublisher {

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
