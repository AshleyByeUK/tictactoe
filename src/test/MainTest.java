import computer.MediumComputerPlayer;
import console.ConsoleUI;
import human.HumanPlayer;
import tictactoe.Game;
import tictactoe.Player;
import tictactoe.TurnPresenter;
import tictactoe.UserInterface;

public class MainTest {

  public static void main(String[] args) {
    UserInterface console = new ConsoleUI();
    console.setPlayerOneToken("1");
    console.setPlayerTwoToken("£");
    Player human = new HumanPlayer("Player 1");
    Player computer = new MediumComputerPlayer("Player 2");
    TurnPresenter presenter = new TurnPresenter();
    Game game = new Game(human, computer);
    game.play(presenter);
  }
}
