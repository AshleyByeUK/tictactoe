package console;

public class GamePlayViewSpy implements GamePlayView {

  public boolean showWasCalled;

  @Override
  public void setPlayerOneToken(String token) {
    // Do nothing.
  }

  @Override
  public void setPlayerTwoToken(String token) {
    // Do nothing.
  }

  @Override
  public void show(GamePlayViewModel viewModel) {
    showWasCalled = true;
  }
}
