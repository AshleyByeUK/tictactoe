import computer.ComputerPlayer;
import console.ConsoleUI;
import human.HumanPlayer;
import tictacttoe.Game;
import tictacttoe.Player;
import tictacttoe.UserInterface;

public class MainTest {

  public static void main(String[] args) {
    UserInterface console = new ConsoleUI();
    Player human = new HumanPlayer("X", "Player 1");
    Player computer = new ComputerPlayer("O", "Player 2");
    Game game = new Game(human, computer, console);
    game.play();
  }
}
