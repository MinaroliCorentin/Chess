package src.chess.status;

import src.chess.factory.Board;
import src.chess.model.handler.PromoteHandler;
import src.chess.model.pieces.Color;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameStatus {

    private Board board;

    public GameStatus(Board board) {

        this.board = board;

    }

    public void promoting() {

        PromoteHandler promoteHandler = new PromoteHandler(board);
        for (int i = 0; i < 8; i++) {

            if ( board.getPiece(0, i) != null && board.getPiece(0, i).isPawn() && board.getPiece(0, i).getColor() == Color.WHITE) {
                board.setPiece(0, i, null);
                promoteHandler.promotion(0, i, Color.WHITE);
            }
            if (board.getPiece(7, i)!= null && board.getPiece(7, i).isPawn() && board.getPiece(7, i).getColor() == Color.BLACK) {
                board.setPiece(7, i, null);
                promoteHandler.promotion(7, i, Color.BLACK);
            }
        }
    }

    public boolean whiteCheckmate(){

        PiecesStatus piecesStatus = new PiecesStatus(board);
        Boolean checkWhite = piecesStatus.isKingInCheck(board,Color.WHITE);
        if ( checkWhite ){

            for ( int i = 0 ; i < 8 ; i++){
                for ( int j = 0 ; j < 8 ; j++){
                    if ( board.getPiece(i, j) != null && board.getPiece(i, j).isKing()){

                        Pieces king = board.getPiece(i, j);
                        List<Localisation> kingmove = king.movements(i,j,board);
                        if ( kingmove.isEmpty()){
                            return true;
                        }

                    }
                }
            }
        }
        return false ;
    }

    public boolean blackCheckmate(){

        PiecesStatus piecesStatus = new PiecesStatus(board);
        Boolean checkBlack = piecesStatus.isKingInCheck(board,Color.BLACK);
        if ( checkBlack ){

            for ( int i = 0 ; i < 8 ; i++){
                for ( int j = 0 ; j < 8 ; j++){
                    if ( board.getPiece(i, j) != null && board.getPiece(i, j).isKing()){

                        Pieces king = board.getPiece(i, j);
                        List<Localisation> kingmove = king.movements(i,j,board);
                        if ( kingmove.isEmpty()){
                            return true;
                        }

                    }
                }
            }
        }
        return false ;
    }


}