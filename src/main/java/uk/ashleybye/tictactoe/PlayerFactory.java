package uk.ashleybye.tictactoe;

import java.util.List;

public interface PlayerFactory {

  List<String> listPlayerTypes();

  Player make(String type, String name, UserInterface userInterface);
}
