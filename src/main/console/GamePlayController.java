package console;

import tictactoe.Game;
import tictactoe.TurnPresenter;

public class GamePlayController {

  private final Game game;
  private final GamePlayView view;
  GamePlayViewModel viewModel;

  public GamePlayController(Game game, GamePlayView view) {
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
    view.setGameState(viewModel.gameState);
    view.show();
  }
}
