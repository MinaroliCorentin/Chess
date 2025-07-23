package src.chess.status;

import src.chess.factory.Board;
import src.chess.model.pieces.Color;

public class GameStatus {

    private Board board;
    private PiecesStatus piecesStatus;

    public GameStatus(Board board, PiecesStatus piecesStatus){

        this.board = board;
        this.piecesStatus =  piecesStatus ;

    }

    public boolean isCheck(Color color){

        return piecesStatus.isKingInCheck(board, color);

    }






}
