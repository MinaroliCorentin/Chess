package src.chess.model.players;

import src.chess.factory.Board;
import src.chess.gamestatus.GameStatus;
import src.chess.model.pieces.PiecesColor;

public class HumanPlayer extends Player {

    public HumanPlayer(Board board, PiecesColor piecesColor, String playerName, GameStatus gameStatus) {
        super(board, piecesColor, playerName, gameStatus);
    }

}
