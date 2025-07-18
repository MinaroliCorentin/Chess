package src.chess.status;

import src.chess.factory.Board;
import src.chess.model.pieces.Color;

public class GameStatus {

    private Board board;
    private PiecesStatus piecesStatus;
    private PlayerStatus playerStatus;

    public GameStatus(Board board, PiecesStatus piecesStatus, PlayerStatus playerStatus){

        this.board = board;
        this.piecesStatus =  piecesStatus ;
        this.playerStatus = playerStatus ;

    }

    public boolean isCheck(Color color){

        return piecesStatus.isKingInCheck(board, color);

    }






}
