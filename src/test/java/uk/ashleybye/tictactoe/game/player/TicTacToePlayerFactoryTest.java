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
import uk.ashleybye.tictactoe.game.player.computer.HardArtificialIntelligence;
import uk.ashleybye.tictactoe.game.player.computer.MediumArtificialIntelligence;
import uk.ashleybye.tictactoe.game.player.human.HumanPlayer;
import uk.ashleybye.tictactoe.ui.console.UserInterfaceMock;

public class TicTacToePlayerFactoryTest {

  private PlayerFactory playerFactory;
  private UserInterfaceMock userInterface;

  @BeforeEach
  void setUp() {
    userInterface = new UserInterfaceMock();
    playerFactory = new TicTacToePlayerFactory();
  }

  @Test
  void providesListOfPlayerTypes() {
    List<String> playerTypes = playerFactory.listPlayerTypes();

    assertIterableEquals(Arrays.asList("Human", "Computer (medium)", "Computer (hard)"), playerTypes);
  }

  @Test
  void unknownTypeThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> playerFactory.make("unknown", "no name", userInterface));
  }

  @Test
  void makesHumanPlayer() {
    Player player = playerFactory.make("Human", "Player 1", userInterface);

    assertTrue(player instanceof HumanPlayer);
    assertEquals("Player 1", player.getName());
  }

  @Test
  void makesMediumComputerPlayer() {
    Player player = playerFactory.make("Computer (medium)", "Player 2", userInterface);

    assertTrue(player instanceof ComputerPlayer);
    assertTrue(((ComputerPlayer) player).getAI() instanceof MediumArtificialIntelligence);
    assertEquals("Player 2", player.getName());
  }

  @Test
  void makesHardComputerPlayer() {
    Player player = playerFactory.make("Computer (hard)", "Player 1", userInterface);

    assertTrue(player instanceof ComputerPlayer);
    assertTrue(((ComputerPlayer) player).getAI() instanceof HardArtificialIntelligence);
    assertEquals("Player 1", player.getName());
  }
}
