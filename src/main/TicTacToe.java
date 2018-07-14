import console.ConsoleGamePlayView;
import console.ConsoleUserInterface;
import console.UserInterface;
import java.util.Scanner;

public class TicTacToe {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    UserInterface console = new ConsoleUserInterface(input);
    console.setGamePlayView(new ConsoleGamePlayView());
    console.launch();
  }
}
