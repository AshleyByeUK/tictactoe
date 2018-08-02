package uk.ashleybye.tictactoe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.tictactoe.player.TicTacToePlayerFactory;
import uk.ashleybye.tictactoe.ui.UserInterface;
import uk.ashleybye.tictactoe.ui.console.ConsoleUserInterface;
import uk.ashleybye.tictactoe.ui.console.firstPlayer.SelectFirstPlayerView;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayView;
import uk.ashleybye.tictactoe.ui.console.mainMenu.MainMenuView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.ChangePlayersSymbolsView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.SelectPlayerSymbolView;
import uk.ashleybye.tictactoe.ui.console.playerType.SelectPlayerView;

public class TicTacToeTest {

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        // Do nothing.
      }
    }));
  }

  @Test
  void humanVsHumanEndToEndTest() {
    Scanner input = new Scanner("1\n1\n1\ny\ny\n1\n2\n1\n2\n3\n4\n5\n6\n8\n7\n9\n");
    UserInterface ui = new ConsoleUserInterface(input);
    ui.setPlayerFactory(new TicTacToePlayerFactory());
    ui.setMainMenuView(new MainMenuView());
    ui.setSelectPlayerView(new SelectPlayerView());
    ui.setSelectFirstPlayerView(new SelectFirstPlayerView());
    ui.setChangePlayersSymbolsView(new ChangePlayersSymbolsView());
    ui.setSelectPlayerSymbolView(new SelectPlayerSymbolView());
    ui.setGamePlayView(new GamePlayView());
    boolean gameOver = ui.launch();

    assertTrue(gameOver);
  }

  @Test
  void mediumVsHardComputerEndToEndTest() {
    Scanner input = new Scanner("1\n2\n3\nn\nn\n");
    UserInterface ui = new ConsoleUserInterface(input);
    ui.setPlayerFactory(new TicTacToePlayerFactory());
    ui.setMainMenuView(new MainMenuView());
    ui.setSelectPlayerView(new SelectPlayerView());
    ui.setSelectFirstPlayerView(new SelectFirstPlayerView());
    ui.setChangePlayersSymbolsView(new ChangePlayersSymbolsView());
    ui.setSelectPlayerSymbolView(new SelectPlayerSymbolView());
    ui.setGamePlayView(new GamePlayView());
    boolean gameOver = ui.launch();

    assertTrue(gameOver);
  }
}
