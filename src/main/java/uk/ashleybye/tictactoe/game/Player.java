package uk.ashleybye.tictactoe.game;


public interface Player {

  String getName();

  void playTurn(GameState gameState);
}
