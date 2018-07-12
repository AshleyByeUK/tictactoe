import computer.ArtificialIntelligence;
import computer.ComputerPlayer;
import computer.MediumArtificialIntelligence;
import console.ConsoleUI;
import human.HumanPlayer;
import tictactoe.Game;
import tictactoe.Player;
import tictactoe.TurnPresenter;

public class MainTest {

  public static void main(String[] args) {
    ConsoleUI console = new ConsoleUI();
    console.setPlayerOneToken("1");
    console.setPlayerTwoToken("£");
    Player human = new HumanPlayer("Player 1");
    ArtificialIntelligence ai = new MediumArtificialIntelligence();
    Player computer = new ComputerPlayer("Player 2", ai);
    TurnPresenter presenter = new TurnPresenter();
    Game game = new Game(human, computer);
    game.play(presenter);
  }
}
