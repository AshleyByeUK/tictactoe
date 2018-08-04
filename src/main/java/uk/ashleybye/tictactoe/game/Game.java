package uk.ashleybye.tictactoe.game;


import uk.ashleybye.tictactoe.game.impl.GameImpl;

public interface Game {

  static GameImpl playTicTacToe(Player player1, Player player2, int firstPlayer) {
    GameImpl game = new GameImpl(player1, player2);
    game.setFirstPlayer(firstPlayer);
    return game;
  }

  boolean play(TurnNotificationPublisher publisher);
}
