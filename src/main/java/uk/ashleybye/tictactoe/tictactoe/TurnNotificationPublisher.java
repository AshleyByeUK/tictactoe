package uk.ashleybye.tictactoe.tictactoe;

public interface TurnNotificationPublisher<T extends TurnNotification> {

  void subscribe(TurnNotificationSubscriber subscriber);

  void notify(T notification);

  T getTurnNotification();
}
