package uk.ashleybye.tictactoe.game;


public interface Player {

  String getName();

  String getSymbol();

  void playTurn(GameState gameState);
}
