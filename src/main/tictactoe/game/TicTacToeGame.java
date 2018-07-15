package tictactoe.game;

import static tictactoe.game.TicTacToeGame.GameState.ENDED;
import static tictactoe.game.TicTacToeGame.GameState.PLAYING;
import static tictactoe.player.PlayerResponse.INPUT_REQUIRED;
import static tictactoe.player.PlayerResponse.POSITION_TAKEN;

import tictactoe.ControllablePlayer;
import tictactoe.Game;
import tictactoe.Player;
import tictactoe.TurnPresenter;
import tictactoe.player.PlayerResponse;

public class TicTacToeGame implements Game {

  TicTacToeBoard board;
  private Player[] players;
  private int currentPlayer;
  GameState gameState;

  public TicTacToeGame(Player player1, Player player2) {
    board = new TicTacToeBoard();
    players = new Player[]{player1, player2};
    currentPlayer = 0;
    gameState = PLAYING;
  }

  public void setFirstPlayer(int player) {
    currentPlayer = player;
  }

  @Override
  public boolean play(TurnPresenter presenter) {
    while (gameState == PLAYING) {
      TicTacToeTurnResponseModel responseModel = initialiseResponseModelForTurn();
      updateBoardWithCurrentAndNextPlayersForTurn();
      PlayerResponse response = players[currentPlayer].playTurn(board);

      if (response == INPUT_REQUIRED)
        responseModel.turnResult = "user_input_required";
      else if (response == POSITION_TAKEN)
        responseModel.turnResult = "position_taken";
      else
        updateResponseModelAndEndTurn(responseModel);

      presenter.present(responseModel);
    }

    return true;
  }

  private TicTacToeTurnResponseModel initialiseResponseModelForTurn() {
    TicTacToeTurnResponseModel responseModel = new TicTacToeTurnResponseModel();
    responseModel.currentPlayer = currentPlayer;
    responseModel.currentPlayerName = players[currentPlayer].getName();
    responseModel.board = board.getPositions();
    responseModel.gameState = "playing";
    responseModel.availablePositions = board.getAvailablePositions();
    return responseModel;
  }

  private void updateBoardWithCurrentAndNextPlayersForTurn() {
    board.setCurrentPlayer(currentPlayer);
    board.setNextPlayer(nextPlayer());
  }

  private void updateResponseModelAndEndTurn(TicTacToeTurnResponseModel responseModel) {
    responseModel.turnResult = "turn_complete";
    responseModel.lastPositionPlayed = board.getLastPositionPlayed();
    updateResponseModelAndEndGameIfGameIsOver(responseModel);
    currentPlayer = nextPlayer();
  }

  private void updateResponseModelAndEndGameIfGameIsOver(TicTacToeTurnResponseModel responseModel) {
    if (board.gameIsWon() || board.gameIsTied()) {
      responseModel.gameState = "game_over";
      responseModel.gameResult = board.gameIsTied() ? "tied_game" : "winner";
      gameState = ENDED;
    }
  }

  private int nextPlayer() {
    if (currentPlayer == 0)
      return 1;
    else
      return 0;
  }

  int getCurrentPlayer() {
    return currentPlayer;
  }

  @Override
  public void receiveUserInput(int input) {
    ((ControllablePlayer) players[currentPlayer]).receiveInput(input);
  }

  enum GameState {ENDED, PLAYING}
}
