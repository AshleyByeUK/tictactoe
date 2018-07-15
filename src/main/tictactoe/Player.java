package tictactoe;

import tictactoe.player.TurnResult;

public interface Player {

  String getName();

  TurnResult playTurn(Board board);
}
