package uk.ashleybye.tictactoe.game;

public interface GamePlayBoundary {

  int getPositionToPlay(GameState gameState);

  void updateDisplay(GameState gameState);
}
