package ui.console;

import ui.console.gamePlay.GamePlayView;
import ui.console.gamePlay.GamePlayViewModel;

public class GamePlayViewSpy extends GamePlayView {

  public boolean showWasCalled;

  @Override
  public void show(GamePlayViewModel viewModel) {
    showWasCalled = true;
  }
}
