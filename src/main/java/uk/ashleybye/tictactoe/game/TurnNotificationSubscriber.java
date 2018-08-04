package uk.ashleybye.tictactoe.game;

public interface TurnNotificationSubscriber {

  void receiveTurnPlayedNotification(TurnNotificationPublisher publisher);
}
