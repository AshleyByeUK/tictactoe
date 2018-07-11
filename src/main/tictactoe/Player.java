package tictactoe;

public interface Player {

  String getName();

  PlayerResponse playTurn(Board board);
}
