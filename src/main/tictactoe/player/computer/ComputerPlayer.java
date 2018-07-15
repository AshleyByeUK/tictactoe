package tictactoe.player.computer;

import static tictactoe.player.PlayerResponse.TURN_COMPLETE;

import tictactoe.Board;
import tictactoe.Player;
import tictactoe.player.PlayerResponse;

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
  public PlayerResponse playTurn(Board board) {
    board.placeToken(ai.computeBestMove(board));
    return TURN_COMPLETE;
  }

  public ArtificialIntelligence getAI() {
    return ai;
  }
}
