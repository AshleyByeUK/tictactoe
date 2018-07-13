package console;

public class GamePlayViewSpy implements GamePlayView {

  public boolean showWasCalled;

  @Override
  public void show(GamePlayViewModel viewModel) {
    showWasCalled = true;
  }
}
