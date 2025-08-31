package src.chess.gameplay;

import src.chess.factory.Board;
import src.chess.gamestatus.GameStatusTerminal;
import src.chess.model.handler.CastlingHandler;
import src.chess.model.pieces.*;

import java.util.List;

public abstract class Gameplay {

    private Board board;
    private PiecesColor piecesColor;

    public Gameplay(Board board, PiecesColor piecesColor) {
        this.board = board ;
        this.piecesColor = piecesColor;
    }

    public Board getBoard() {
        return board;
    }

    public PiecesColor getPiecesColor() {
        return piecesColor;
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

    public boolean playCastling(Pieces movingPiece, int x, int y, int newX, int newY) {

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

    public abstract void play(String beginning, String ending);

}
