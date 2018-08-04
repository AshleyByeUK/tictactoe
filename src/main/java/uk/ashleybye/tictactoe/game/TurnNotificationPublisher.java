package uk.ashleybye.tictactoe.game;

public interface TurnNotificationPublisher {

  void subscribe(TurnNotificationSubscriber subscriber);

  void notify(TurnNotification notification);

  TurnNotification getTurnNotification();
}
