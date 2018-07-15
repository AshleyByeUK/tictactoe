package ui.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.GameSpy;
import tictactoe.TurnPresenterSpy;

class ConsoleUserInterfaceTest  {

  private Scanner input;
  private GamePlayViewSpy viewSpy;
  private TurnPresenterSpy presenterSpy;
  private ConsoleUserInterface console;

  @BeforeEach
  void setUp() {
    InputUtilities.showInputPrompt = false;
    input = new Scanner("1");
    viewSpy = new GamePlayViewSpy();
    presenterSpy = new TurnPresenterSpy();
    console = new ConsoleUserInterface(input);
    console.setGamePlayView(viewSpy);
  }

  @Test
  void canReceiveViewModelFromPresenter() {
    console.game = new GameSpy("computer");
    boolean gameOver = console.launchGame(presenterSpy);

    assertTrue(presenterSpy.getViewModelWasCalled);
    assertTrue(gameOver);
  }

  @Test
  void canSendUserInputToGame() {
    GameSpy gameSpy = new GameSpy("human");
    console.game = gameSpy;
    console.notifyTurnPlayed(presenterSpy);

    assertTrue(gameSpy.receiveUserInputWasCalled);
    assertEquals(0, gameSpy.userInput);
  }
}
