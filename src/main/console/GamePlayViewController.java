package console;

import tictactoe.Game;
import tictactoe.TurnPresenter;

public class GamePlayViewController {

  private final Game game;
  private final GamePlayView view;
  private GamePlayViewModel viewModel;

  public GamePlayViewController(Game game, GamePlayView view) {
    this.game = game;
    this.view = view;
  }

  public boolean playGame() {
    TurnPresenter presenter = new TurnPresenter();
    presenter.register(this);
    return game.play(presenter);
  }

  public void viewModelUpdated(TurnPresenter presenter) {
    viewModel = presenter.getViewModel();
    view.show(viewModel);

    if (viewModel.userInputIsRequired) {
      game.receiveUserInput(viewModel.userInput);
    }
  }
}
