package console;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConsoleUITest {

  @Test
  void setsDefaultTokenForUser() {
    ConsoleUI consoleUI = new ConsoleUI();

    assertEquals("X", consoleUI.formatToken(0));
    assertEquals("O", consoleUI.formatToken(1));
  }

  @Test
  void playersCanSetCustomToken() {
    ConsoleUI consoleUI = new ConsoleUI();
    consoleUI.setPlayerOneToken("1");
    consoleUI.setPlayerTwoToken("£");

    assertEquals("1", consoleUI.formatToken(0));
    assertEquals("£", consoleUI.formatToken(1));
  }
}
