import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HumanPlayerTest {

  private ConsoleUISpy consoleSpy;
  private Player player;
  private Game game;

  private void assertInvalidInput(String input) {
    consoleSpy.setUserInputValue(input);
    player.playTurn(game);

    assertEquals("Invalid input", consoleSpy.lastMessage);
  }

  @BeforeEach
  void setUp() {
    consoleSpy = new ConsoleUISpy();
    player = new HumanPlayer("X", "human");
    game = new Game(player, player, consoleSpy);
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
