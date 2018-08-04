package uk.ashleybye.tictactoe.game;


import uk.ashleybye.tictactoe.game.impl.GameImpl;

public interface Game {

  static GameImpl playTicTacToe(GameOptions options, PlayerFactory playerFactory, UserInterface userInterface) {
    Player player1 = playerFactory.make(options.getPlayerOneType(), options.getPlayerOneName(), options.getPlayerOneSymbol(), userInterface);
    Player player2 = playerFactory.make(options.getPlayerTwoType(), options.getPlayerTwoName(), options.getPlayerTwoSymbol(), userInterface);
    GameImpl game = new GameImpl(player1, player2);
    game.setFirstPlayer(options.getFirstPlayer());
    return game;
  }

  boolean play(TurnNotificationPublisher publisher);
}
