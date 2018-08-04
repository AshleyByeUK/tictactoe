package uk.ashleybye.tictactoe.game.player.human;

import uk.ashleybye.tictactoe.game.GamePlayBoundary;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;

public class HumanPlayer implements Player {

  private final String name;
  private final String symbol;
  private GamePlayBoundary gamePlayBoundary;

  public HumanPlayer(String name, String symbol, GamePlayBoundary gamePlayBoundary) {
    this.name = name;
    this.symbol = symbol;
    this.gamePlayBoundary = gamePlayBoundary;
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
    gameState.setUserInputRequired(true);
    while (!gameState.getBoard().positionIsAvailable(positionToPlay)) {
      gamePlayBoundary.updateDisplay(gameState);
      positionToPlay = gamePlayBoundary.getPositionToPlay();
    }
    gameState.setUserInputRequired(false);
    gameState.getBoard().placeSymbolAtPosition(positionToPlay, gameState.getCurrentPlayer());
  }
}
