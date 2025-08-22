package src.chess.test;

import org.junit.Test;
import src.chess.factory.Board;
import src.chess.factory.EmptyBoard;
import src.chess.model.pieces.*;

import java.util.Random;

public class PawnTest {
    
    @Test
    public void pawnTest(){


        Pieces pawn_Black = new Pawn(PiecesColor.BLACK);
        assert( !pawn_Black.isBishop()): " pawn is not a Bishop";
        assert( !pawn_Black.isKing()) : " pawn is not a king";
        assert( !pawn_Black.isKnight()) : " pawn is not a Knight";
        assert( pawn_Black.isPawn()) : " pawn is a Pawn";
        assert( !pawn_Black.isQueen()) : " pawn is not a Queen";
        assert( !pawn_Black.isRook()) : " pawn is not a Rook";

        Pieces pawn_White = new Pawn(PiecesColor.WHITE);
        assert( !pawn_White.isBishop()): " pawn is not a Bishop";
        assert( !pawn_White.isKing()) : "pawn is not a king";
        assert( !pawn_White.isKnight()) : " pawn is not a Knight";
        assert( pawn_White.isPawn()) : " pawn is a Pawn";
        assert( !pawn_White.isQueen()) : " pawn is not a Queen";
        assert( !pawn_White.isRook()) : " pawn is not a Rook";
        
    }

    @Test
    public void getSymbolTest(){

        Pieces Pawn_White = new Pawn(PiecesColor.WHITE);
        Pieces Pawn_Black = new Pawn(PiecesColor.BLACK);

        assert (Pawn_White.getSymbol().equals("\u265F") ):" Must display a White Pawn ";
        assert (Pawn_White.getSymbol().equals(UnicodePieces.PAWN_WHITE) ):" Must display a White Pawn ";
        assert (Pawn_Black.getSymbol().equals("\u2659") ):" Must display a Black Pawn ";
        assert (Pawn_Black.getSymbol().equals(UnicodePieces.PAWN_BLACK) ):" Must display a Black Pawn ";

    }

    @Test
    public void movementBlockedTest() {

        Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Board board = new EmptyBoard();
            Pieces pawn = new Pawn(PiecesColor.BLACK);
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x, y, pawn);

            if (board.isBound(x +1 , y)){
                board.setPiece(x +1, y, new Pawn(PiecesColor.BLACK));
            }

            assert (pawn.movements(x, y, board).isEmpty()) : " Black pawn should have no movement : " + pawn.movements(x, y, board).size() ;
        }
    }

    @Test
    public void pawnMovementAvailableTest() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Pieces pawn = new Pawn(PiecesColor.BLACK);
            Board board = new EmptyBoard();
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x, y, pawn);

            int[][] moves = {
                    {x, y - 1},
                    {x - 1, y - 1},
                    {x + 1, y - 1}
            };

            int test1 = pawn.movements(x, y, board).size();
            assert (test1 >= 0) : "pawn must have some movements available";

            for (int[] move : moves) {
                int mx = move[0];
                int my = move[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new Pawn(PiecesColor.BLACK));
                }

                int test2 = pawn.movements(x, y, board).size();
                assert (test1 >= test2) : "pawn must have same or fewer moves after blocking";
            }
        }
    }

}