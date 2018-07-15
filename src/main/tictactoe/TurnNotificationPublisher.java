package tictactoe;

public interface TurnNotificationPublisher<T> {

  void subscribe(TurnNotificationSubscriber subscriber);

  void notify(T notification);

  T getTurnNotification();
}
