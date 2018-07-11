package tictactoe;

public interface Player {

  String getName();

  TurnResult playTurn(Board board, UserInterface ui);
}
