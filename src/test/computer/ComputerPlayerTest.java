package computer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import console.ConsoleUIMock;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.GameMock;
import tictactoe.Player;

class ComputerPlayerTest {

  private ConsoleUIMock mockConsoleUI;
  private Player player;
  private GameMock mockGame;

  @BeforeEach
  void setUp() {
    mockConsoleUI = new ConsoleUIMock();
    Random randomStub = new RandomStub();
    player = new ComputerPlayer("computer", randomStub);
    mockGame = new GameMock(player, player, mockConsoleUI);
  }

  @Test
  void choosesCentreSpotIfAvailable() {
    assertEquals(4, player.playTurn(mockGame));
  }

  @Test
  void choosesWinningSpotIfAvailable() {
    mockGame.setCurrentPlayer(1);
    mockGame.setBoard(new int[]{1, 1, -1, 0, 0, -1, 0, -1, -1});

    assertEquals(2, player.playTurn(mockGame));
  }

  @Test
  void stopsOppositionWinning() {
    mockGame.setCurrentPlayer(1);
    mockGame.setBoard(new int[]{0, 0, -1, 1, 1, -1, -1, -1, 0});

    assertEquals(2, player.playTurn(mockGame));
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    mockGame.setCurrentPlayer(1);
    mockGame.setBoard(new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1});

    assertEquals(0, player.playTurn(mockGame));
  }
}
