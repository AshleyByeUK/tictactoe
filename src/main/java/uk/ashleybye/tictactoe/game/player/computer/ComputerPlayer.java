package uk.ashleybye.tictactoe.game.player.computer;

import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;

public class ComputerPlayer implements Player {

  private final String name;
  private final ArtificialIntelligence ai;

  public ComputerPlayer(String name, ArtificialIntelligence ai) {
    this.name = name;
    this.ai = ai;
  }

  @Override
  public String getName() {
    return name;
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
