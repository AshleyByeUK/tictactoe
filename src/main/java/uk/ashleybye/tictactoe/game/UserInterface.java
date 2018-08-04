package uk.ashleybye.tictactoe.game;


public interface UserInterface extends TurnNotificationSubscriber {

  boolean launch();

  int getPositionToPlay(TurnNotification turnNotification);
}
