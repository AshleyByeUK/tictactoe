package tictactoe.game;

import tictactoe.TurnNotificationPublisher;
import tictactoe.TurnNotificationSubscriber;

public class TicTacToeTurnNotificationPublisher implements TurnNotificationPublisher<TicTacToeTurnNotification> {

  private TurnNotificationSubscriber subscriber;
  private TicTacToeTurnNotification turnNotification;

  @Override
  public void subscribe(TurnNotificationSubscriber subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void notify(TicTacToeTurnNotification notification) {
    turnNotification = notification;
    subscriber.receiveTurnPlayedNotification(this);
  }

  @Override
  public TicTacToeTurnNotification getTurnNotification() {
    return turnNotification;
  }
}
