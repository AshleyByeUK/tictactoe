package uk.ashleybye.tictactoe.game;


import uk.ashleybye.tictactoe.game.impl.GameImpl;

public interface Game {

  static GameImpl playTicTacToe(GameOptions options, GamePlayBoundary gamePlayBoundary, PlayerFactory playerFactory) {
    Player player1 = playerFactory.make(options.getPlayerOneType(), options.getPlayerOneName(), options.getPlayerOneSymbol(),
        gamePlayBoundary);
    Player player2 = playerFactory.make(options.getPlayerTwoType(), options.getPlayerTwoName(), options.getPlayerTwoSymbol(),
        gamePlayBoundary);
    GameImpl game = new GameImpl(player1, player2, gamePlayBoundary);
    game.setFirstPlayer(options.getFirstPlayer());
    return game;
  }

  boolean play();
}
