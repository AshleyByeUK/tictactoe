package console;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import tictactoe.Game;
import tictactoe.GameSpy;

public class GamePlayViewControllerTest {

  @Test
  void canReceiveViewModelFromPresenter() {
    Game gameSpy = new GameSpy("computer");
    GamePlayViewSpy viewSpy = new GamePlayViewSpy();
    GamePlayViewController controller = new GamePlayViewController(gameSpy, viewSpy);
    controller.playGame();

    assertTrue(viewSpy.showWasCalled);
  }

  @Test
  void canSendInputToGame() {
    GameSpy gameSpy = new GameSpy("human");
    GamePlayView viewSpy = new GamePlayViewSpy();
    GamePlayViewController controller = new GamePlayViewController(gameSpy, viewSpy);
    controller.playGame();

    assertTrue(gameSpy.receiveUserInputWasCalled);
  }
}
