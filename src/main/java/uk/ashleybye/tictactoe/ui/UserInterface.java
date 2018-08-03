package uk.ashleybye.tictactoe.ui;


import uk.ashleybye.tictactoe.tictactoe.TurnNotificationSubscriber;
import uk.ashleybye.tictactoe.tictactoe.game.TicTacToeTurnNotification;

public interface UserInterface extends TurnNotificationSubscriber {

  boolean launch();

  int getPositionToPlay(TicTacToeTurnNotification turnNotification);
}
