package uk.ashleybye.tictactoe.tictactoe;

public interface TurnNotificationSubscriber {

  void receiveTurnPlayedNotification(TurnNotificationPublisher publisher);
}
