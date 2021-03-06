package uk.ashleybye.tictactoe.game.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.PlayerFactory;
import uk.ashleybye.tictactoe.game.player.computer.ComputerPlayer;
import uk.ashleybye.tictactoe.game.player.computer.HardDifficulty;
import uk.ashleybye.tictactoe.game.player.computer.MediumDifficulty;
import uk.ashleybye.tictactoe.game.player.human.HumanPlayer;
import uk.ashleybye.tictactoe.ui.console.ConsoleGamePlayBoundaryMock;

class PlayerFactoryImplTest {

  private PlayerFactory playerFactory;
  private ConsoleGamePlayBoundaryMock userInterface;

  @BeforeEach
  void setUp() {
    userInterface = new ConsoleGamePlayBoundaryMock();
    playerFactory = new PlayerFactoryImpl();
  }

  @Test
  void providesListOfPlayerTypes() {
    List<String> playerTypes = playerFactory.listPlayerTypes();

    assertIterableEquals(Arrays.asList("Human", "Computer (medium)", "Computer (hard)"), playerTypes);
  }

  @Test
  void unknownTypeThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> playerFactory.make("unknown", "no name", "X", userInterface));
  }

  @Test
  void makesHumanPlayer() {
    Player player = playerFactory.make("Human", "Player 1", "X", userInterface);

    assertTrue(player instanceof HumanPlayer);
    assertEquals("Player 1", player.getName());
  }

  @Test
  void makesMediumDifficultyComputerPlayer() {
    Player player = playerFactory.make("Computer (medium)", "Player 2", "X", userInterface);

    assertTrue(player instanceof ComputerPlayer);
    assertTrue(((ComputerPlayer) player).getDifficulty() instanceof MediumDifficulty);
    assertEquals("Player 2", player.getName());
  }

  @Test
  void makesHardDifficultyComputerPlayer() {
    Player player = playerFactory.make("Computer (hard)", "Player 1", "X", userInterface);

    assertTrue(player instanceof ComputerPlayer);
    assertTrue(((ComputerPlayer) player).getDifficulty() instanceof HardDifficulty);
    assertEquals("Player 1", player.getName());
  }
}
