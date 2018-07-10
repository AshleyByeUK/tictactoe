import computer.ComputerPlayer;
import console.ConsoleUI;
import human.HumanPlayer;
import tictactoe.Game;
import tictactoe.Player;
import tictactoe.UserInterface;

public class MainTest {

  public static void main(String[] args) {
    UserInterface console = new ConsoleUI();
    console.setPlayerOneToken("1");
    console.setPlayerTwoToken("£");
    Player human = new HumanPlayer("Player 1");
    Player computer = new ComputerPlayer("Player 2");
    Game game = new Game(human, computer, console);
    game.play();
  }
}
