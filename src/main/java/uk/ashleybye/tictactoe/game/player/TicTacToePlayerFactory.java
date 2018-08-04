package uk.ashleybye.tictactoe.game.player;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.PlayerFactory;
import uk.ashleybye.tictactoe.game.UserInterface;
import uk.ashleybye.tictactoe.game.player.computer.ComputerPlayer;
import uk.ashleybye.tictactoe.game.player.computer.HardArtificialIntelligence;
import uk.ashleybye.tictactoe.game.player.computer.MediumArtificialIntelligence;
import uk.ashleybye.tictactoe.game.player.human.HumanPlayer;

public class TicTacToePlayerFactory implements PlayerFactory {

  @Override
  public List<String> listPlayerTypes() {
    return Arrays.asList("Human", "Computer (medium)", "Computer (hard)");
  }

  @Override
  public Player make(String type, String name, UserInterface userInterface) {
    if (type.equalsIgnoreCase("Human"))
      return new HumanPlayer(name, userInterface);
    else if (type.equalsIgnoreCase("Computer (medium)"))
      return new ComputerPlayer(name, new MediumArtificialIntelligence());
    else if (type.equalsIgnoreCase("Computer (hard)"))
      return new ComputerPlayer(name, new HardArtificialIntelligence());
    else
      throw new IllegalArgumentException("Player type not supported.");
  }
}
