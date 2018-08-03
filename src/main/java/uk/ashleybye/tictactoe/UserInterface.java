package uk.ashleybye.tictactoe;


import uk.ashleybye.tictactoe.TurnNotification;
import uk.ashleybye.tictactoe.TurnNotificationSubscriber;

public interface UserInterface extends TurnNotificationSubscriber {

  boolean launch();

  int getPositionToPlay(TurnNotification turnNotification);
}
