import computer.ArtificialIntelligence;
import computer.ComputerPlayer;
import computer.HardArtificialIntelligence;
import console.ConsoleGamePlayView;
import console.GamePlayView;
import console.GamePlayViewController;
import human.HumanPlayer;
import tictactoe.Game;
import tictactoe.Player;

public class MainTest {

  public static void main(String[] args) {
    Player human = new HumanPlayer("Player 1");
    ArtificialIntelligence ai = new HardArtificialIntelligence();
    Player computer = new ComputerPlayer("Player 2", ai);
    Game game = new Game(human, computer);
    GamePlayView gameView = new ConsoleGamePlayView();
    GamePlayViewController controller = new GamePlayViewController(game, gameView);
    controller.playGame();
  }
}
