package uk.ashleybye.tictactoe.tictactoe.player.human;


import static uk.ashleybye.tictactoe.tictactoe.player.TurnResult.INPUT_REQUIRED;
import static uk.ashleybye.tictactoe.tictactoe.player.TurnResult.POSITION_TAKEN;
import static uk.ashleybye.tictactoe.tictactoe.player.TurnResult.TURN_COMPLETE;

import uk.ashleybye.tictactoe.tictactoe.ControllablePlayer;
import uk.ashleybye.tictactoe.tictactoe.GameState;
import uk.ashleybye.tictactoe.tictactoe.Player;
import uk.ashleybye.tictactoe.tictactoe.player.TurnResult;

public class HumanPlayer implements Player, ControllablePlayer {

  private final String name;
  private int positionToPlay;

  public HumanPlayer(String name) {
    this.name = name;
    positionToPlay = -1;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public TurnResult playTurn(GameState gameState) {
    if (positionToPlay == -1)
      return INPUT_REQUIRED;
    else if (!gameState.getBoard().positionIsAvailable(positionToPlay))
      return POSITION_TAKEN;
    else {
      return takeTurn(gameState);
    }
  }

  private TurnResult takeTurn(GameState gameState) {
    gameState.getBoard().placeSymbolAtPosition(positionToPlay, gameState.getCurrentPlayer());
    positionToPlay = -1;
    return TURN_COMPLETE;
  }

  @Override
  public void receiveInput(int value) {
    positionToPlay = value;
  }
}
