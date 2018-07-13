package tictactoe;

import static tictactoe.Game.GameState.ENDED;
import static tictactoe.Game.GameState.PLAYING;
import static tictactoe.PlayerResponse.INPUT_REQUIRED;
import static tictactoe.PlayerResponse.POSITION_TAKEN;

public class Game {

  Board board;
  private Player[] players;
  private int currentPlayer;
  GameState gameState;

  public Game(Player player1, Player player2) {
    board = new Board();
    players = new Player[]{player1, player2};
    currentPlayer = 0;
    gameState = PLAYING;
  }

  public void setFirstPlayer(int player) {
    currentPlayer = player;
  }

  public boolean play(TurnPresenter presenter) {
    while (gameState == PLAYING) {
      TurnResponseModel responseModel = initialiseResponseModelForTurn();
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

  private TurnResponseModel initialiseResponseModelForTurn() {
    TurnResponseModel responseModel = new TurnResponseModel();
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

  private void updateResponseModelAndEndTurn(TurnResponseModel responseModel) {
    responseModel.turnResult = "turn_complete";
    responseModel.lastPositionPlayed = board.getLastPositionPlayed();
    updateResponseModelAndEndGameIfGameIsOver(responseModel);
    currentPlayer = nextPlayer();
  }

  private void updateResponseModelAndEndGameIfGameIsOver(TurnResponseModel responseModel) {
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

  public void receiveUserInput(int input) {
    ((ControllablePlayer) players[currentPlayer]).receiveInput(input);
  }

  enum GameState {ENDED, PLAYING}
}
