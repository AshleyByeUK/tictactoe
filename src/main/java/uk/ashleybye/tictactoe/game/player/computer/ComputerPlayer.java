package uk.ashleybye.tictactoe.game.player.computer;

import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;

public class ComputerPlayer implements Player {

  private final String name;
  private final String symbol;
  private final Difficulty difficulty;

  public ComputerPlayer(String name, String symbol, Difficulty difficulty) {
    this.name = name;
    this.symbol = symbol;
    this.difficulty = difficulty;
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
    int positionToPlay = difficulty.computeNextMove(gameState);
    gameState.getBoard().placeSymbolAtPosition(positionToPlay, gameState.getCurrentPlayer());
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }
}
