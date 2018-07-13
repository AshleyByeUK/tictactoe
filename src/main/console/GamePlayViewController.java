package console;

import java.util.Scanner;
import tictactoe.Game;
import tictactoe.TurnPresenter;

public class GamePlayViewController {

  private final Game game;
  private final GamePlayView view;
  private final Scanner input;
  private GamePlayViewModel viewModel;

  public GamePlayViewController(Game game, GamePlayView view) {
    this(game, view, new Scanner(System.in));
  }

  GamePlayViewController(Game game, GamePlayView view, Scanner input) {
    this.game = game;
    this.view = view;
    this.input = input;
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
      game.receiveUserInput(Utilities.getIntegerInput(input, 1, 9) - 1);
    }
  }
}
