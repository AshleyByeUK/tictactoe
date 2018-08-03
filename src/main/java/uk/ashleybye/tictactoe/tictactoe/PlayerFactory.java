package uk.ashleybye.tictactoe.tictactoe;

import java.util.List;
import uk.ashleybye.tictactoe.ui.UserInterface;

public interface PlayerFactory {

  List<String> listPlayerTypes();

  Player make(String type, String name, UserInterface userInterface);
}
