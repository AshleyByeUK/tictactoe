package tictactoe;

import tictactoe.player.PlayerResponse;

public interface Player {

  String getName();

  PlayerResponse playTurn(Board board);
}
