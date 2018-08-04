package uk.ashleybye.tictactoe.game;

import java.util.List;

public interface PlayerFactory {

  List<String> listPlayerTypes();

  Player make(String type, String name, String symbol, UserInterface userInterface);
}
