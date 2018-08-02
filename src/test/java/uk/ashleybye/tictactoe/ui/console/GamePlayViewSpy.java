package uk.ashleybye.tictactoe.ui.console;


import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayView;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayViewModel;

public class GamePlayViewSpy extends GamePlayView {

  public boolean showWasCalled;

  @Override
  public void show(GamePlayViewModel viewModel) {
    showWasCalled = true;
  }
}
