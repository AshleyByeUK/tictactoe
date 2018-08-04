package uk.ashleybye.tictactoe.game.player.human;


import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.TurnNotification;
import uk.ashleybye.tictactoe.game.UserInterface;

public class HumanPlayer implements Player {

  private final String name;
  private UserInterface userInterface;

  public HumanPlayer(String name, UserInterface userInterface) {
    this.name = name;
    this.userInterface = userInterface;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void playTurn(GameState gameState) {
    int positionToPlay = -1;
    while (!gameState.getBoard().positionIsAvailable(positionToPlay)) {
      TurnNotification notification = new TurnNotification();
      notification.availablePositions = gameState.getBoard().getAvailablePositions();
      notification.board = gameState.getBoard().getPositions();
      notification.currentPlayerName = name;
      notification.userInputIsRequired = true;

      positionToPlay = userInterface.getPositionToPlay(notification);
    }

    gameState.getBoard().placeSymbolAtPosition(positionToPlay, gameState.getCurrentPlayer());
  }
}
