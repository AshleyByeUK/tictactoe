import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

  private ConsoleUISpy consoleSpy;
  private HumanPlayerSpy player1Spy;
  private HumanPlayerSpy player2Spy;
  private Game game;

  @BeforeEach
  void setUp() {
    consoleSpy = new ConsoleUISpy();
    player1Spy = new HumanPlayerSpy("X", "player1");
    player2Spy = new HumanPlayerSpy("O", "player2");
    game = new Game(player1Spy, player2Spy, consoleSpy);
  }

  @Test
  void afterSetupGameIsReady() {
    assertEquals("ready", game.getCurrentState());
  }

  @Test
  void playerOneMakesTheFirstMove() {
    game.playTurn();

    assertEquals("playing", game.getCurrentState());
    assertTrue(player1Spy.playedMove);
    assertEquals("player1", consoleSpy.callersName);
    assertFalse(player2Spy.playedMove);
  }

  @Test
  void playerTwoMakesTheSecondMove() {
    game.playTurn();
    game.playTurn();

    assertTrue(player1Spy.playedMove);
    assertTrue(player2Spy.playedMove);
    assertEquals("player2", consoleSpy.callersName);
  }

  @Test
  void playerTwoCanGoFirst() {
    game.setFirstPlayer(1);
    game.playTurn();

    assertTrue(player2Spy.playedMove);
    assertEquals("player2", consoleSpy.callersName);
    assertFalse(player1Spy.playedMove);
  }

  @Test
  void afterNineMovesTheGameIsTied() {
    for (int i = 0; i < 9; i++)
      game.playTurn();

    assertTrue(game.isTied());
  }
}
