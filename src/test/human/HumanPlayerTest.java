package human;

import static org.junit.jupiter.api.Assertions.assertEquals;

import console.ConsoleUIMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictacttoe.Game;
import tictacttoe.Player;

class HumanPlayerTest {

  private ConsoleUIMock mockConsoleUI;
  private Player player;
  private Game game;

  private void assertInvalidInput(String input) {
    mockConsoleUI.setUserInputValue(input);
    player.playTurn(game);

    assertEquals("Invalid input", mockConsoleUI.lastMessage);
  }

  @BeforeEach
  void setUp() {
    mockConsoleUI = new ConsoleUIMock();
    player = new HumanPlayer("X", "human");
    game = new Game(player, player, mockConsoleUI);
  }

  @Test
  void invalidUserInput() {
    assertInvalidInput("");
    assertInvalidInput(" ");
    assertInvalidInput("A");
    assertInvalidInput("£");
    assertInvalidInput("invalid input");
    assertInvalidInput("12 34");
  }

  @Test
  void outOfRangeUserInput() {
    assertInvalidInput("-1");
    assertInvalidInput("9");
  }
}
