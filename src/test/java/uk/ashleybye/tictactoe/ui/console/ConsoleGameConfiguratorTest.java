package uk.ashleybye.tictactoe.ui.console;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameOptions;
import uk.ashleybye.tictactoe.game.PlayerFactory;
import uk.ashleybye.tictactoe.game.player.PlayerFactoryImpl;

class ConsoleGameConfiguratorTest {

  private Scanner input;
  private PlayerFactory playerFactory;

  @BeforeEach
  void setUp() {
    input = new Scanner(System.in);
    playerFactory = new PlayerFactoryImpl();
  }

  @Test
  void playerSymbolsCannotBeTheSame() {
    GameOptions options = new GameOptions();
    options.setPlayerOneSymbol("X");
    options.setPlayerTwoSymbol("X");

    ConsoleGameConfigurator gameConfigurator = new ConsoleGameConfigurator(input, playerFactory);

    assertFalse(gameConfigurator.symbolsDiffer(options));
  }

  @Test
  void playerSymbolsCanBeDifferent() {
    GameOptions options = new GameOptions();
    options.setPlayerOneSymbol("X");
    options.setPlayerTwoSymbol("O");

    ConsoleGameConfigurator gameConfigurator = new ConsoleGameConfigurator(input, playerFactory);

    assertTrue(gameConfigurator.symbolsDiffer(options));
  }
}
