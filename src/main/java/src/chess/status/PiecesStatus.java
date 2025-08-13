package src.chess.status;

import src.chess.factory.Board;
import src.chess.model.pieces.*;

import java.util.List;

public class PiecesStatus {

    private Board board;

    public PiecesStatus(Board board){
        this.board = board ;
    }

    /**
     * Verify is the King of the param color is in Check
     * @param board The board the player are using
     * @param color The color we want ot verify
     * @return True if the king of @param color is in danger
     */
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

    /**
     * Verify if the king is in check using the position X and Y
     * @param posX The position X of the king
     * @param posY The position Y of the King
     * @param color The color of the king
     * @return True if the king in @param Color is in danger
     */
    public boolean isKingInCheckPos(int posX, int posY, Color color){

        for ( int i = 0 ; i < 8 ; i++){
            for ( int j = 0 ; j < 8 ; j++){

                Pieces pieces = board.getPiece(i,j);
                if ( pieces != null && !pieces.getColor().equals(color)){
                    List<Localisation> checkMovement = pieces.getAttackSquares(i,j,board);
                    for ( Localisation localisation : checkMovement ){
                        if ( localisation.getX() == posX && localisation.getY() == posY ){
                            return true ;
                        }
                    }
                }

            }
        }
        return false ;
    }

    /**
     * Verify if the Black King can do a Casting Left Side
     * @return True if the castling is possible and if he rook.isLeftRookMOved and the king.IsMoved are false
     */
    public boolean canCastleBlackLeftSide(){

        Pieces pieces = board.getPiece(0,0);
        Pieces pieces2 = board.getPiece(0,4);

        for ( int i = 1 ; i < 4 ; i++ ){
            Pieces emptyPiece = board.getPiece(0,i);
            if ( emptyPiece != null){
                return false ;
            }
        }

        if ( pieces != null && pieces.isRook() && pieces2 !=null && pieces2.isKing() ){
            Rook rook = (Rook) board.getPiece(0, 0);
            King king = (King) board.getPiece(0, 4);
            return !rook.isLeftRookMoved() && !king.isMoved();
        }
        return false ;
    }

    /**
     * Verify if the White King can do a Casting Left Side
     * @return True if the castling is possible and if he rook.isLeftRookMOved and the king.IsMoved are false
     */
    public boolean canCastleWhiteLeftSide(){

        Pieces pieces = board.getPiece(7,0);
        Pieces pieces2 = board.getPiece(7,4);

        for ( int i = 1 ; i < 4 ; i++ ){
            Pieces emptyPiece = board.getPiece(7,i);
            if ( emptyPiece != null){
                return false ;
            }
        }

        if ( pieces != null && pieces.isRook() && pieces2 !=null && pieces2.isKing() ){
            Rook rook = (Rook) board.getPiece(7, 0);
            King king = (King) board.getPiece(7, 4);
            return !rook.isLeftRookMoved() && !king.isMoved();
        }
        return false ;
    }

    /**
     * Verify if the Black King can do a Casting Right Side
     * @return True if the castling is possible and if he rook.isRightRookMOved and the king.IsMoved are false
     */
    public boolean canCastleBlackRightSide(){

        Pieces pieces = board.getPiece(0,7);
        Pieces pieces2 = board.getPiece(0,4);

        for ( int i = 6 ; i > 4 ; i-- ){
            Pieces emptyPiece = board.getPiece(0,i);
            if ( emptyPiece != null){
                return false ;
            }
        }

        if ( pieces != null && pieces.isRook() && pieces2 !=null && pieces2.isKing() ){
            Rook rook = (Rook) board.getPiece(0, 7);
            King king = (King) board.getPiece(0, 4);
            return !rook.isLeftRookMoved() && !king.isMoved() ;
        }
        return false ;
    }

    /**
     * Verify if the White King can do a Casting Right  Side
     * @return True if the castling is possible and if he rook.isRightRookMoved and the king.IsMoved are false
     */
    public boolean canCastleWhiteRightSide(){

        Pieces pieces = board.getPiece(7,7);
        Pieces pieces2 = board.getPiece(7,4);

        for ( int i = 6 ; i > 4 ; i-- ){
            Pieces emptyPiece = board.getPiece(7,i);
            if ( emptyPiece != null){
                return false ;
            }
        }

        if ( pieces != null && pieces.isRook() && pieces2 !=null && pieces2.isKing() ){
            Rook rook = (Rook) board.getPiece(7, 7);
            King king = (King) board.getPiece(7, 4);
            return !rook.isLeftRookMoved() && !king.isMoved();
        }
        return false ;
    }

}