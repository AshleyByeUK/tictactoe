package uk.ashleybye.tictactoe;

import java.util.Scanner;
import uk.ashleybye.tictactoe.tictactoe.player.TicTacToePlayerFactory;
import uk.ashleybye.tictactoe.ui.UserInterface;
import uk.ashleybye.tictactoe.ui.console.ConsoleUserInterface;
import uk.ashleybye.tictactoe.ui.console.firstPlayer.SelectFirstPlayerView;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayView;
import uk.ashleybye.tictactoe.ui.console.mainMenu.MainMenuView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.ChangePlayersSymbolsView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.SelectPlayerSymbolView;
import uk.ashleybye.tictactoe.ui.console.playerType.SelectPlayerView;

public class TicTacToe {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    UserInterface ui = new ConsoleUserInterface(
        input,
        new TicTacToePlayerFactory(),
        new MainMenuView(),
        new SelectPlayerView(),
        new SelectFirstPlayerView(),
        new ChangePlayersSymbolsView(),
        new SelectPlayerSymbolView(),
        new GamePlayView());
    ui.launch();
  }
}
