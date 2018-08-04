package uk.ashleybye.tictactoe.game.player.computer;

import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;

public class ComputerPlayer implements Player {

  private final String name;
  private final String symbol;
  private final ArtificialIntelligence ai;

  public ComputerPlayer(String name, String symbol, ArtificialIntelligence ai) {
    this.name = name;
    this.symbol = symbol;
    this.ai = ai;
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
    int positionToPlay = ai.computeNextMove(gameState);
    gameState.getBoard().placeSymbolAtPosition(positionToPlay, gameState.getCurrentPlayer());
  }

  public ArtificialIntelligence getAI() {
    return ai;
  }
}
