package tictactoe;

public interface Player {

  String getName();

  void playTurn(Board board, UserInterface ui);
}
