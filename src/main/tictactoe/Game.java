package tictactoe;

import static tictactoe.Game.GameState.ENDED;
import static tictactoe.Game.GameState.PLAYING;
import static tictactoe.PlayerResponse.INPUT_REQUIRED;
import static tictactoe.PlayerResponse.INVALID_INPUT;

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

  public void play(TurnPresenter presenter) {
    while (gameState == PLAYING) {
      TurnResponseModel responseModel = initialiseResponseModel();
      PlayerResponse response = players[currentPlayer].playTurn(board);

      if (response == INPUT_REQUIRED)
        responseModel.turnResult = "user_input_required";
      else if (response == INVALID_INPUT)
        responseModel.turnResult = "user_input_invalid";
      else
        updateResponseModelAndEndTurn(responseModel);

      presenter.present(responseModel);
    }
  }

  private TurnResponseModel initialiseResponseModel() {
    TurnResponseModel responseModel = new TurnResponseModel();
    responseModel.currentPlayer = currentPlayer;
    responseModel.currentPlayerName = players[currentPlayer].getName();
    responseModel.board = board.getPositions();
    responseModel.gameState = "playing";
    return responseModel;
  }

  private void updateResponseModelAndEndTurn(TurnResponseModel responseModel) {
    responseModel.turnResult = "turn_complete";
    updateResponseModelAndEndGameIfGameIsOver(responseModel);
    currentPlayer = nextPlayer();
  }

  private void updateResponseModelAndEndGameIfGameIsOver(TurnResponseModel responseModel) {
    if (board.gameIsOver() || board.gameIsTied()) {
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

  enum GameState {ENDED, PLAYING}
}
