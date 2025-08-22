package src.chess.model.pieces;

import src.chess.factory.Board;

import java.util.List;

public abstract class Pieces {

    private PiecesColor piecesColor;
    
    public Pieces(PiecesColor piecesColor){
        this.piecesColor = piecesColor;
    }

    /**
     * @return the piecesColor of the Piece
     */
    public PiecesColor getColor(){
        return piecesColor;
    }

    /**
     * @return False except if the Piece isn't a Bishop. Otherwise, the Return will be true because Bishop will override the False with a true
     */
    public boolean isBishop(){
        return false ;
    }


    /**
     * @return False except if the Piece isn't a King. Otherwise, the Return will be true because King will override the False with a true
     */
    public boolean isKing(){
        return false ;
    }


    /**
     * @return False except if the Piece isn't a Knight. Otherwise, the Return will be true because Knight will override the False with a true
     */
    public boolean isKnight(){
        return false ;
    }


    /**
     * @return False except if the Piece isn't a Pawn. Otherwise, the Return will be true because Pawn will override the False with a true
     */
    public boolean isPawn(){
        return false;
    }


    /**
     * @return False except if the Piece isn't a Queen. Otherwise, the Return will be true because Queen will override the False with a true
     */
    public boolean isQueen(){
        return false;
    }


    /**
     * @return False except if the Piece isn't a Rook. Otherwise, the Return will be true because Rook will override the False with a true
     */
    public boolean isRook(){
        return false;
    }

    /**
     * Return a list of all the movement possible for the Piece. The player can now move the piece as long it's in the returned List ( and if the movement is valid ).
     * @param x row
     * @param y col
     * @param board The board the player is involving
     * @return a list of all the possible movement for the Piece.
     */
    public abstract List<Localisation> movements(int x, int y, Board board);

    /**
     * @return return the Symbol of the Piece
     */
    public abstract String getSymbol();

    /**
     * @param x row
     * @param y col
     * @param board The board the player is involving
     * @return a lis of all the possible movement for the Piece with no restriction. This method is used only for King.java cause KinginCheck call Movement and Movement call KinginCheck
     */
    public abstract List<Localisation> getAttackSquares(int x, int y, Board board);

}