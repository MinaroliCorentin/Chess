package src.chess.status;

import src.chess.factory.Board;
import src.chess.model.pieces.Color;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;

import java.util.List;

public class PiecesStatus {

    private Board board;

    public PiecesStatus(Board board){
        this.board = board ;
    }

    public boolean isKingInCheck(Board board, Color color){

        int posXKing = -1 ;
        int posYKing = -1 ;

        for ( int i = 0 ; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {

                Pieces piece = board.getPiece(i, j);
                if ( piece != null && piece.isKing() && piece.getColor().equals(color)){
                    posXKing = i ;
                    posYKing = j ;
                }

            }
        }

        if ( posXKing == -1 || posYKing == -1){
            throw new RuntimeException(" King not found ");
        }

        for ( int i = 0 ; i < 8 ; i++){
            for ( int j = 0 ; j < 8 ; j++){

                Pieces pieces = board.getPiece(i,j);
                if ( pieces != null && !pieces.getColor().equals(color)){

                    List<Localisation> checkMovement = pieces.movements(i,j,board);
                    for ( Localisation localisation : checkMovement ){

                        if ( localisation.getX() == posXKing && localisation.getY() == posYKing ){
                            return true ;
                        }
                    }

                }
            }
        }

        return false ;
    }

}
