package ui.console;

import ui.ViewModel;

public class GamePlayViewSpy extends GamePlayView {

  public boolean showWasCalled;

  @Override
  public void show(ViewModel viewModel) {
    showWasCalled = true;
  }
}
