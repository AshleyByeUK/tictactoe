package uk.ashleybye.tictactoe.tictactoe.player.computer;


import static uk.ashleybye.tictactoe.tictactoe.player.TurnResult.TURN_COMPLETE;

import uk.ashleybye.tictactoe.tictactoe.Board;
import uk.ashleybye.tictactoe.tictactoe.Player;
import uk.ashleybye.tictactoe.tictactoe.player.TurnResult;

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
  public TurnResult playTurn(Board board) {
    board.placeSymbolAtPosition(ai.computeBestMove(board));
    return TURN_COMPLETE;
  }

  public ArtificialIntelligence getAI() {
    return ai;
  }
}
