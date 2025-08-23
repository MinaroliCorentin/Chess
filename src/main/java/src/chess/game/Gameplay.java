package src.chess.game;

import src.chess.factory.Board;
import src.chess.model.handler.CastlingHandler;
import src.chess.model.pieces.*;
import src.chess.status.GameStatus;
import src.chess.status.PiecesStatus;

import java.util.List;

public class Gameplay {

    private Board board;
    private PiecesColor piecesColor;

    public Gameplay(Board board, PiecesColor piecesColor) {
        this.board = board ;
        this.piecesColor = piecesColor;
    }

    /**
     * Convert two string into x,y coordinate to play.
     * @param beginning Starting Piece
     * @param ending Ending Piece
     * @return array of int. First and second value are Starting x,y coordinate. Third and 4th are ending coordinate.
     */
    public int[] StringToIntegerPlay(String beginning, String ending){

        // Remove the spaces
        beginning = beginning.replaceAll("\\s","");
        ending = ending.replaceAll("\\s","");

        if ( beginning.isBlank() || beginning.length() != 2|| ending.isBlank() || ending.length() != 2  ) throw new IllegalArgumentException("Inputs are wrong. Please use 2 Strings with positions, ex. From: B2 To: B4");

        char strA = beginning.toLowerCase().charAt(0);
        char strB = beginning.charAt(1);
        char strC = ending.toLowerCase().charAt(0);
        char strD = ending.charAt(1);

        if (strA < 'a' || strA > 'h') throw new IllegalStateException("Out of bounds");
        if (strC < 'a' || strC > 'h') throw new IllegalStateException("Out of bounds");
        if (strB < '1' || strB > '8') throw new IllegalStateException("Out of bounds");
        if (strD < '1' || strD > '8') throw new IllegalStateException("Out of bounds");

        // Transform String into Integer
        int x = 8 - (strB - '0');
        int y = strA - 'a';
        int newX = 8 - (strD - '0');
        int newY = strC - 'a';

        int[] value = new int [4];
        value[0] = x;
        value[1] = y;
        value[2] = newX;
        value[3] = newY;

        return value;

    }

    /**
     * Use 2 strings. The first one is the piece the playare if willing to play, the second is the move is want to do.
     * Verify if the move is current, then play it.
     * Allow Castling if everything is correct.
     * Every time a player want to play a Piece, the method verify if it's safe for the king
     * @param beginning The localisation where the desire Piece is located.
     * @param ending The Position the player want to move the Piece Located the beginning String.
     */
    public void play(String beginning, String ending) {

        // Used to reset the drawCounter if attacking a piece
        GameStatus gameStatus = new GameStatus(board);

        int[] value = StringToIntegerPlay(beginning,ending);

        int x = value[0];
        int y = value[1];
        int newX = value[2];
        int newY = value[3];

        if (!board.isBound(x, y) || !board.isBound(newX, newY)) throw new IllegalArgumentException("Out of bounds");

        Pieces movingPiece = board.getPiece(x, y);
        Pieces destinationPiece = board.getPiece(newX, newY);

        if (movingPiece == null) throw new IllegalStateException("No piece at source location");
        if (movingPiece.getColor() != this.piecesColor) throw new IllegalStateException("You can only move your own pieces");

        if (playCastling(movingPiece, x, y, newX, newY)) {
            return;
        }

        if (destinationPiece != null && movingPiece.getColor() == destinationPiece.getColor()) throw new IllegalStateException("You can't attack your own piece");

        Pieces enemyPiece = null;

        List<Localisation> allMoves = movingPiece.movements(x, y, board);
        for (Localisation localisation : allMoves) {
            // newX and newY are the destinationPieces coordinates
            if (localisation.getX() == newX && localisation.getY() == newY) {

                enemyPiece = board.getPiece(newX, newY);

                // Simulation the move to see if it threaten the king else gameplay it
                board.setPiece(newX, newY, movingPiece);
                board.setPiece(x, y, null);

                PiecesStatus status = new PiecesStatus(board);
                boolean threat = status.isKingInCheck(board,board.getPiece(newX,newY).getColor());

                // Prevent a move if it threat the king
                if (threat){
                    board.setPiece(x,y, movingPiece);
                    board.setPiece(newX, newY, enemyPiece);
                    throw new IllegalStateException( " This move will threat your own king" );
                }

                if (movingPiece.isPawn()){

                    // If black used enPassant
                    if (movingPiece.getColor() == PiecesColor.BLACK) {
                        if ( x == board.getEnPassantPawnX() && y + 1 == board.getEnPassantPawnY() && newX == board.getEnPassantPawnX() + 1 && newY == board.getEnPassantPawnY() && enemyPiece == null) {
                            board.setPiece(board.getEnPassantPawnX(), board.getEnPassantPawnY(), null);
                            gameStatus.resetDrawCounter();
                        }
                        if ( x == board.getEnPassantPawnX() && y - 1 == board.getEnPassantPawnY() && newX == board.getEnPassantPawnX() + 1 && newY == board.getEnPassantPawnY() && enemyPiece == null) {
                            board.setPiece(board.getEnPassantPawnX(), board.getEnPassantPawnY(), null);
                            gameStatus.resetDrawCounter();
                        }
                    }
                    // If white used enPassant
                    if (movingPiece.getColor() == PiecesColor.WHITE) {
                        if ( x == board.getEnPassantPawnX() && y + 1 == board.getEnPassantPawnY() && newX == board.getEnPassantPawnX() - 1 && newY == board.getEnPassantPawnY() && enemyPiece == null) {
                            board.setPiece(board.getEnPassantPawnX(), board.getEnPassantPawnY(), null);
                            gameStatus.resetDrawCounter();
                        }
                        if ( x == board.getEnPassantPawnX() && y - 1 == board.getEnPassantPawnY() && newX == board.getEnPassantPawnX() - 1 && newY == board.getEnPassantPawnY() && enemyPiece == null) {
                            board.setPiece(board.getEnPassantPawnX(), board.getEnPassantPawnY(), null);
                            gameStatus.resetDrawCounter();
                        }
                    }

                    // set EnPassant pos if pawn double move
                    if ( x - newX == 2 || x - newX == -2 ) {
                        board.setEnPassantPawn(newX,newY);
                    } else {
                        board.setEnPassantPawnDefaultValue();
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
            gameStatus.resetDrawCounter();
        }

        if ( board.getPiece(newX, newY) == null) throw new IllegalStateException(" This move is impossible");

    }

    private boolean playCastling(Pieces movingPiece, int x, int y, int newX, int newY) {

        if (movingPiece.isKing()) {
            // Castling
            CastlingHandler castling = new CastlingHandler(board);
            if (movingPiece.getColor() == PiecesColor.WHITE) {
                if (x == 7 && y == 4 && newX == 7 && newY == 7) {
                    castling.handleWhiteKingsideCastling(x, y, newX, newY);
                    return true;
                } else if (x == 7 && y == 4 && newX == 7 && newY == 0) {
                    castling.handleWhiteQueensideCastling(x, y, newX, newY);
                    return true;
                }
            } else if (movingPiece.getColor() == PiecesColor.BLACK) {
                if (x == 0 && y == 4 && newX == 0 && newY == 7) {
                    castling.handleBlackKingsideCastling(x, y, newX, newY);
                    return true;
                } else if (x == 0 && y == 4 && newX == 0 && newY == 0) {
                    castling.handleBlackQueensideCastling(x, y, newX, newY);
                    return true;
                }
            }
        }
        return false;
    }

}