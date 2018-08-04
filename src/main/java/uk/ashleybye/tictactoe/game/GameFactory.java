package uk.ashleybye.tictactoe.game;

public interface GameFactory {

  Game make(GameOptions options, GamePlayBoundary boundary);
}
