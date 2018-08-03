package uk.ashleybye.tictactoe;

public interface TurnNotificationPublisher {

  void subscribe(TurnNotificationSubscriber subscriber);

  void notify(TurnNotification notification);

  TurnNotification getTurnNotification();
}
