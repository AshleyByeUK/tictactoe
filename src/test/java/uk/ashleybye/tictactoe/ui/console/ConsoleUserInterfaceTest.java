package uk.ashleybye.tictactoe.ui.console;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.tictactoe.game.TicTacToeGameSpy;
import uk.ashleybye.tictactoe.tictactoe.game.TicTacToeTurnNotificationPublisherSpy;
import uk.ashleybye.tictactoe.tictactoe.player.TicTacToePlayerFactory;
import uk.ashleybye.tictactoe.ui.console.firstPlayer.SelectFirstPlayerView;
import uk.ashleybye.tictactoe.ui.console.mainMenu.MainMenuView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.ChangePlayersSymbolsView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.SelectPlayerSymbolView;
import uk.ashleybye.tictactoe.ui.console.playerType.SelectPlayerView;

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
    console = new ConsoleUserInterface(
        input,
        new TicTacToePlayerFactory(),
        new MainMenuView(),
        new SelectPlayerView(),
        new SelectFirstPlayerView(),
        new ChangePlayersSymbolsView(),
        new SelectPlayerSymbolView(),
        viewSpy);
  }

  @Test
  void canReceiveViewModelFromPresenter() {
    console.game = new TicTacToeGameSpy("computer");
    boolean gameOver = console.launchGame(presenterSpy);

    assertTrue(presenterSpy.getTurnNotificationWasCalled);
    assertTrue(gameOver);
  }
}
