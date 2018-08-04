package uk.ashleybye.tictactoe.game;


public interface GamePlayBoundary {

  int getPositionToPlay();

  void updateDisplay(GameState gameState);
}
