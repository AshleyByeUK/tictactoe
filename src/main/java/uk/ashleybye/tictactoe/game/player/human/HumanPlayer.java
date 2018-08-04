package uk.ashleybye.tictactoe.game.player.human;


import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.TurnNotification;
import uk.ashleybye.tictactoe.game.UserInterface;

public class HumanPlayer implements Player {

  private final String name;
  private final String symbol;
  private UserInterface userInterface;

  public HumanPlayer(String name, String symbol, UserInterface userInterface) {
    this.name = name;
    this.symbol = symbol;
    this.userInterface = userInterface;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getSymbol() {
    return symbol;
  }

  @Override
  public void playTurn(GameState gameState) {
    int positionToPlay = -1;
    while (!gameState.getBoard().positionIsAvailable(positionToPlay)) {
      TurnNotification notification = new TurnNotification();
      notification.players = gameState.getPlayers();
      notification.availablePositions = gameState.getBoard().getAvailablePositions();
      notification.board = gameState.getBoard().getPositions();
      notification.currentPlayerName = name;
      notification.userInputIsRequired = true;

      positionToPlay = userInterface.getPositionToPlay(notification);
    }

    gameState.getBoard().placeSymbolAtPosition(positionToPlay, gameState.getCurrentPlayer());
  }
}
