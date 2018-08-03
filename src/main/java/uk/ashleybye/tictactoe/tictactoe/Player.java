package uk.ashleybye.tictactoe.tictactoe;


import uk.ashleybye.tictactoe.tictactoe.player.TurnResult;

public interface Player {

  String getName();

  TurnResult playTurn(GameState gameState);
}
