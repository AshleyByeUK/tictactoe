package tictactoe;

public interface TurnNotificationSubscriber {

  void receiveTurnPlayedNotification(TurnNotificationPublisher publisher);
}
