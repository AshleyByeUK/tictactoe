package uk.ashleybye.tictactoe.game.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.GameFactory;
import uk.ashleybye.tictactoe.game.GameOptions;
import uk.ashleybye.tictactoe.game.GamePlayBoundary;
import uk.ashleybye.tictactoe.game.player.PlayerFactoryImpl;
import uk.ashleybye.tictactoe.game.player.human.HumanPlayer;
import uk.ashleybye.tictactoe.ui.console.ConsoleGamePlayBoundary;

class GameFactoryImplTest {

  private GameOptions options;
  private GamePlayBoundary boundary;
  private GameFactory factory;

  @BeforeEach
  void setUp() {
    options = new GameOptions();
    boundary = new ConsoleGamePlayBoundary(new Scanner(System.in));
    factory = new GameFactoryImpl(new PlayerFactoryImpl());
  }

  @Test
  void initialisedWithInvalidPlayerFactoryThrowsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> new GameFactoryImpl(null));
  }

  @Test
  void callingMakeWithInvalidArgumentsThrowsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> factory.make(null, null));
    assertThrows(IllegalArgumentException.class, () -> factory.make(null, boundary));
    assertThrows(IllegalArgumentException.class, () -> factory.make(options, null));
  }

  @Test
  void buildsGame() {
    options.setPlayerOneName("Player 1");
    options.setPlayerTwoName("Player 1");
    options.setPlayerOneType("Human");
    options.setPlayerTwoType("Human");
    options.setPlayerOneSymbol("X");
    options.setPlayerTwoSymbol("O");
    options.setFirstPlayer(1);

    Game game = factory.make(options, boundary);
    assertTrue(game instanceof GameImpl);

    GameImpl gameImpl = (GameImpl) game;
    assertTrue(gameImpl.players[0] instanceof HumanPlayer);
    assertTrue(gameImpl.players[1] instanceof HumanPlayer);
  }
}
