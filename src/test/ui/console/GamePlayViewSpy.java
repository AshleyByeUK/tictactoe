package ui.console;

import ui.ViewModel;
import ui.console.gamePlay.GamePlayView;

public class GamePlayViewSpy extends GamePlayView {

  public boolean showWasCalled;

  @Override
  public void show(ViewModel viewModel) {
    showWasCalled = true;
  }
}
