import java.util.Scanner;
import ui.UserInterface;
import ui.console.ConsoleUserInterface;
import ui.console.GamePlayView;

public class TicTacToe {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    UserInterface ui = new ConsoleUserInterface(input);
    ui.setGamePlayView(new GamePlayView());
    ui.launch();
  }
}
