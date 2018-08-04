package uk.ashleybye.tictactoe.game.impl;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.GameFactory;
import uk.ashleybye.tictactoe.game.GameOptions;
import uk.ashleybye.tictactoe.game.GamePlayBoundary;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.PlayerFactory;

public class GameFactoryImpl implements GameFactory {

  private final PlayerFactory playerFactory;

  public GameFactoryImpl(PlayerFactory playerFactory) {
    this.playerFactory = playerFactory;
    if (playerFactory == null)
      throw new IllegalArgumentException("A player factory cannot be null");
  }

  @Override
  public Game make(GameOptions o, GamePlayBoundary b) {
    if (o == null)
      throw new IllegalArgumentException("Game options cannot be null");
    if (b == null)
      throw new IllegalArgumentException("A game play boundary cannot be null");

    Player p1 = playerFactory.make(o.getPlayerOneType(), o.getPlayerOneName(), o.getPlayerOneSymbol(), b);
    Player p2 = playerFactory.make(o.getPlayerTwoType(), o.getPlayerTwoName(), o.getPlayerTwoSymbol(), b);
    GameImpl game = new GameImpl(p1, p2, b);
    game.setFirstPlayer(o.getFirstPlayer());
    return game;
  }
}
