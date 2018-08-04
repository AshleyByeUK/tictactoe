package uk.ashleybye.tictactoe;

import java.util.Scanner;
import uk.ashleybye.tictactoe.game.player.PlayerFactoryImpl;
import uk.ashleybye.tictactoe.ui.UserInterface;
import uk.ashleybye.tictactoe.ui.console.ConsoleUserInterface;

public class TicTacToe {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    UserInterface ui = new ConsoleUserInterface(input, new PlayerFactoryImpl());
    ui.launch();
  }
}
