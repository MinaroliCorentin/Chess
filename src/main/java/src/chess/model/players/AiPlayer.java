package src.chess.model.players;

import src.chess.factory.Board;
import src.chess.model.pieces.PiecesColor;

public class AiPlayer extends Player{

    public AiPlayer(Board board, PiecesColor piecesColor) {
        super(board, piecesColor);
    }

}
