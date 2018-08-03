package uk.ashleybye.tictactoe;

public interface TurnNotificationSubscriber {

  void receiveTurnPlayedNotification(TurnNotificationPublisher publisher);
}
