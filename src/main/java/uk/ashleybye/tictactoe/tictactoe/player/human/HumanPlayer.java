package uk.ashleybye.tictactoe.tictactoe.player.human;


import uk.ashleybye.tictactoe.tictactoe.GameState;
import uk.ashleybye.tictactoe.tictactoe.Player;
import uk.ashleybye.tictactoe.tictactoe.game.TicTacToeTurnNotification;
import uk.ashleybye.tictactoe.ui.UserInterface;

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
      TicTacToeTurnNotification notification = new TicTacToeTurnNotification();
      notification.availablePositions = gameState.getBoard().getAvailablePositions();
      notification.board = gameState.getBoard().getPositions();
      notification.currentPlayerName = name;
      notification.userInputIsRequired = true;

      positionToPlay = userInterface.getPositionToPlay(notification);
    }

    gameState.getBoard().placeSymbolAtPosition(positionToPlay, gameState.getCurrentPlayer());
  }
}
