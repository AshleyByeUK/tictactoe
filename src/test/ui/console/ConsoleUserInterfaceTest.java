package ui.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.game.TicTacToeGameSpy;
import tictactoe.game.TicTacToeTurnNotificationPublisherSpy;

class ConsoleUserInterfaceTest {

  private Scanner input;
  private GamePlayViewSpy viewSpy;
  private TicTacToeTurnNotificationPublisherSpy presenterSpy;
  private ConsoleUserInterface console;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        // Do nothing.
      }
    }));
    input = new Scanner("1");
    viewSpy = new GamePlayViewSpy();
    presenterSpy = new TicTacToeTurnNotificationPublisherSpy();
    console = new ConsoleUserInterface(input);
    console.setGamePlayView(viewSpy);
  }

  @Test
  void canReceiveViewModelFromPresenter() {
    console.game = new TicTacToeGameSpy("computer");
    boolean gameOver = console.launchGame(presenterSpy);

    assertTrue(presenterSpy.getTurnNotificationWasCalled);
    assertTrue(gameOver);
  }

  @Test
  void canSendUserInputToGame() {
    TicTacToeGameSpy gameSpy = new TicTacToeGameSpy("human");
    console.game = gameSpy;
    console.receiveTurnPlayedNotification(presenterSpy);

    assertTrue(gameSpy.receiveUserInputWasCalled);
    assertEquals(0, gameSpy.userInput);
  }
}
