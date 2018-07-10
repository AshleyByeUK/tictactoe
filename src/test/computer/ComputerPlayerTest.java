package computer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import console.ConsoleUIMock;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictacttoe.Game;
import tictacttoe.Player;

class ComputerPlayerTest {

  private ConsoleUIMock mockConsoleUI;
  private Player player;
  private Game game;

  @BeforeEach
  void setUp() {
    mockConsoleUI = new ConsoleUIMock();
    Random randomStub = new RandomStub();
    player = new ComputerPlayer("computer", randomStub);
    game = new Game(player, player, mockConsoleUI);
  }

  @Test
  void choosesMiddleSpotIfAvailable() {
    assertEquals(4, player.playTurn(game));
  }

  @Test
  void choosesWinningSpotIfAvailable() {
    game.currentPlayer = 1;
    game.board = new int[]{1, 1, -1, 0, 0, -1, 0, -1, -1};

    assertEquals(2, player.playTurn(game));
  }

  @Test
  void stopsOppositionWinning() {
    game.currentPlayer = 1;
    game.board = new int[]{0, 0, -1, 1, 1, -1, -1, -1, 0};

    assertEquals(2, player.playTurn(game));
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    game.currentPlayer = 1;
    game.board = new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1};

    assertEquals(0, player.playTurn(game));
  }
}