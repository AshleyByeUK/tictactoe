package ui.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.game.TicTacToeSpy;
import tictactoe.game.TicTacToeTurnPresenterSpy;

class ConsoleUserInterfaceTest  {

  private Scanner input;
  private GamePlayViewSpy viewSpy;
  private TicTacToeTurnPresenterSpy presenterSpy;
  private ConsoleUserInterface console;

  @BeforeEach
  void setUp() {
    InputUtilities.showInputPrompt = false;
    input = new Scanner("1");
    viewSpy = new GamePlayViewSpy();
    presenterSpy = new TicTacToeTurnPresenterSpy();
    console = new ConsoleUserInterface(input);
    console.setGamePlayView(viewSpy);
  }

  @Test
  void canReceiveViewModelFromPresenter() {
    console.game = new TicTacToeSpy("computer");
    boolean gameOver = console.launchGame(presenterSpy);

    assertTrue(presenterSpy.getViewModelWasCalled);
    assertTrue(gameOver);
  }

  @Test
  void canSendUserInputToGame() {
    TicTacToeSpy gameSpy = new TicTacToeSpy("human");
    console.game = gameSpy;
    console.notifyTurnPlayed(presenterSpy);

    assertTrue(gameSpy.receiveUserInputWasCalled);
    assertEquals(0, gameSpy.userInput);
  }
}
