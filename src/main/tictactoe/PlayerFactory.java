package tictactoe;

import java.util.List;
import tictactoe.Player;

public interface PlayerFactory {

  List<String> listPlayerTypes();

  Player make(String type, String name);
}
