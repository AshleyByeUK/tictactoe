package console;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import computer.ArtificialIntelligence;
import computer.ComputerPlayer;
import computer.HardArtificialIntelligence;
import computer.MediumArtificialIntelligence;
import org.junit.jupiter.api.Test;
import tictactoe.Game;
import tictactoe.Player;

public class GamePlayControllerTest {

  @Test
  void canReceiveViewModelFromPresenter() {
    ArtificialIntelligence medium = new MediumArtificialIntelligence();
    ArtificialIntelligence hard = new HardArtificialIntelligence();
    Player player1 = new ComputerPlayer("player1", medium);
    Player player2 = new ComputerPlayer("player2", hard);
    Game game = new Game(player1, player2);

    GamePlayView view = new GamePlayView();

    GamePlayController controller = new GamePlayController(game, view);
    boolean gameOver = controller.playGame();

    GamePlayViewModel viewModel = controller.viewModel;
    assertNotNull(viewModel.gameState);
    assertFalse(viewModel.gameState.isEmpty());
    assertTrue(gameOver);
  }
}
