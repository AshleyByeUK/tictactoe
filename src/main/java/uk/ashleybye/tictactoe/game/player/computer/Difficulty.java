package uk.ashleybye.tictactoe.game.player.computer;

import uk.ashleybye.tictactoe.game.GameState;

public interface Difficulty {

  int computeNextMove(GameState gameState);
}
