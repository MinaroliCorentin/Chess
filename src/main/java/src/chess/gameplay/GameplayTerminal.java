package src.chess.gameplay;

import src.chess.factory.Board;
import src.chess.model.handler.CastlingHandler;
import src.chess.model.pieces.*;
import src.chess.gamestatus.GameStatusTerminal;
import src.chess.model.pieces.PiecesStatus;

import java.util.List;

public class GameplayTerminal extends Gameplay {


    public GameplayTerminal(Board board, PiecesColor piecesColor){
        super(board, piecesColor);
    }

    /**
     * Use 2 strings. The first one is the piece the playare if willing to play, the second is the move is want to do.
     * Verify if the move is current, then play it.
     * Allow Castling if everything is correct.
     * Every time a player want to play a Piece, the method verify if it's safe for the king
     * @param beginning The localisation where the desire Piece is located.
     * @param ending The Position the player want to move the Piece Located the beginning String.
     */
    @Override
    public void play(String beginning, String ending) {

        // Used to reset the drawCounter if attacking a piece
        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(getBoard());

        int[] value = StringToIntegerPlay(beginning,ending);

        int x = value[0];
        int y = value[1];
        int newX = value[2];
        int newY = value[3];

        if (!getBoard().isBound(x, y) || !getBoard().isBound(newX, newY)) throw new IllegalArgumentException("Out of bounds");

        Pieces movingPiece = getBoard().getPiece(x, y);
        Pieces destinationPiece = getBoard().getPiece(newX, newY);

        if (movingPiece == null) throw new IllegalStateException("No piece at source location");
        if (movingPiece.getColor() != this.getPiecesColor()) throw new IllegalStateException("You can only move your own pieces");

        if (playCastling(movingPiece, x, y, newX, newY)) {
            return;
        }

        if (destinationPiece != null && movingPiece.getColor() == destinationPiece.getColor()) throw new IllegalStateException("You can't attack your own piece");

        Pieces enemyPiece = null;

        List<Localisation> allMoves = movingPiece.movements(x, y, getBoard());
        for (Localisation localisation : allMoves) {
            // newX and newY are the destinationPieces coordinates
            if (localisation.getX() == newX && localisation.getY() == newY) {

                enemyPiece = getBoard().getPiece(newX, newY);

                // Simulation the move to see if it threaten the king else gameplay it
                getBoard().setPiece(newX, newY, movingPiece);
                getBoard().setPiece(x, y, null);

                PiecesStatus status = new PiecesStatus(getBoard());
                boolean threat = status.isKingInCheck(getBoard(),getBoard().getPiece(newX,newY).getColor());

                // Prevent a move if it threat the king
                if (threat){
                    getBoard().setPiece(x,y, movingPiece);
                    getBoard().setPiece(newX, newY, enemyPiece);
                    throw new IllegalStateException( " This move will threat your own king" );
                }

                if (movingPiece.isPawn()){

                    // If black used enPassant
                    if (movingPiece.getColor() == PiecesColor.BLACK) {
                        if ( x == getBoard().getEnPassantPawnX() && y + 1 == getBoard().getEnPassantPawnY() && newX == getBoard().getEnPassantPawnX() + 1 && newY == getBoard().getEnPassantPawnY() && enemyPiece == null) {
                            getBoard().setPiece(getBoard().getEnPassantPawnX(), getBoard().getEnPassantPawnY(), null);
                            gameStatusTerminal.resetDrawCounter();
                        }
                        if ( x == getBoard().getEnPassantPawnX() && y - 1 == getBoard().getEnPassantPawnY() && newX == getBoard().getEnPassantPawnX() + 1 && newY == getBoard().getEnPassantPawnY() && enemyPiece == null) {
                            getBoard().setPiece(getBoard().getEnPassantPawnX(), getBoard().getEnPassantPawnY(), null);
                            gameStatusTerminal.resetDrawCounter();
                        }
                    }
                    // If white used enPassant
                    if (movingPiece.getColor() == PiecesColor.WHITE) {
                        if ( x == getBoard().getEnPassantPawnX() && y + 1 == getBoard().getEnPassantPawnY() && newX == getBoard().getEnPassantPawnX() - 1 && newY == getBoard().getEnPassantPawnY() && enemyPiece == null) {
                            getBoard().setPiece(getBoard().getEnPassantPawnX(), getBoard().getEnPassantPawnY(), null);
                            gameStatusTerminal.resetDrawCounter();
                        }
                        if ( x == getBoard().getEnPassantPawnX() && y - 1 == getBoard().getEnPassantPawnY() && newX == getBoard().getEnPassantPawnX() - 1 && newY == getBoard().getEnPassantPawnY() && enemyPiece == null) {
                            getBoard().setPiece(getBoard().getEnPassantPawnX(), getBoard().getEnPassantPawnY(), null);
                            gameStatusTerminal.resetDrawCounter();
                        }
                    }

                    // set EnPassant pos if pawn double move
                    if ( x - newX == 2 || x - newX == -2 ) {
                        getBoard().setEnPassantPawn(newX,newY);
                    } else {
                        getBoard().setEnPassantPawnDefaultValue();
                    }
                }

                // Prevent Castling
                if ( movingPiece.isKing()){
                    ((King) movingPiece).setHasMoved(true);
                } else if ( movingPiece.isRook()){
                    if ( y == 0 ){
                        ((Rook) movingPiece).setLeftRookMoved(true);
                    } else if ( y == 7){
                        ((Rook) movingPiece).setRightRookMoved(true);
                    }
                }

            }
        }

        if ( enemyPiece != null){
            gameStatusTerminal.resetDrawCounter();
        }

        if ( getBoard().getPiece(newX, newY) == null) throw new IllegalStateException("This piece can't play this move ");

    }

}