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
    UserInterface ui = new ConsoleUserInterface(input);
    ui.setPlayerFactory(new TicTacToePlayerFactory());
    ui.setMainMenuView(new MainMenuView());
    ui.setSelectPlayerView(new SelectPlayerView());
    ui.setSelectFirstPlayerView(new SelectFirstPlayerView());
    ui.setChangePlayersSymbolsView(new ChangePlayersSymbolsView());
    ui.setSelectPlayerSymbolView(new SelectPlayerSymbolView());
    ui.setGamePlayView(new GamePlayView());
    ui.launch();
  }
}
