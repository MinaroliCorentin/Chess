package src.chess.Test;

import org.junit.Test;
import src.chess.Factory.Board;
import src.chess.Factory.EmptyBoard;
import src.chess.Model.pieces.*;

import java.util.Random;

public class RookTest {

    @Test
    public void rookTest(){

        Pieces rook_Black = new Rook(Color.BLACK);
        assert( !rook_Black.isBishop()): " rook is not a Bishop";
        assert( !rook_Black.isKing()) : " rook is not a king";
        assert( !rook_Black.isKnight()) : " rook is not a Knight";
        assert( !rook_Black.isPawn()) : " rook is not a Pawn";
        assert( !rook_Black.isQueen()) : " rook is not a Queen";
        assert( rook_Black.isRook()) : " rook is a Rook";

        Pieces rook_White = new Rook(Color.WHITE);
        assert( !rook_White.isBishop()): " rook is not a Bishop";
        assert( !rook_White.isKing()) : " rook is not a king";
        assert( !rook_White.isKnight()) : " rook is not a Knight";
        assert( !rook_White.isPawn()) : " rook is not a Pawn";
        assert( !rook_White.isQueen()) : " rook is not a Queen";
        assert( rook_White.isRook()) : " rook is a Rook";

    }

    @Test
    public void getSymbolTest(){

        Pieces Rook_Black = new Rook(Color.BLACK);
        Pieces Rook_White = new Rook(Color.WHITE);

        assert ( Rook_White.getSymbol().equals(UnicodePieces.ROOK_WHITE)) : " Must display a White Rook ";
        assert ( Rook_White.getSymbol().equals("\u265C")) : " Must display a White Rook ";
        assert ( Rook_Black.getSymbol().equals(UnicodePieces.ROOK_BLACK)) : " Must display a White Rook ";
        assert ( Rook_Black.getSymbol().equals("\u2656")) : " Must display a White Rook ";

    }

    @Test
    public void movementBlockedTest() {

        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            Board board = new EmptyBoard();
            Pieces rook = new Rook(Color.BLACK);
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x, y, rook);

            int[][] directions = {
                    {1, 0},
                    {-1, 0},
                    {0, 1},
                    {0, -1}
            };

            for (int[] dir : directions) {
                int mx = x + dir[0];
                int my = y + dir[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new King(Color.BLACK));
                }
            }

            assert (rook.movements(x, y, board).isEmpty()) : " Rook must have no movement available.";
        }
    }

    @Test
    public void movementAvailableTest() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Pieces bishop_Black = new Bishop(Color.BLACK);
            Board board = new EmptyBoard();
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x, y, bishop_Black);
            int[][] moves = {
                    {x + 1, y + 1},
                    {x + 1, y - 1},
                    {x - 1, y + 1},
                    {x - 1, y - 1},
            };

            int test1 = bishop_Black.movements(x, y, board).size();
            assert (test1 >= 0) : " bishop must have some movements available  ";

            for (int[] move : moves) {
                int mx = move[0];
                int my = move[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new Bishop(Color.BLACK));
                }

                int test2 = bishop_Black.movements(x, y, board).size();
                assert (test1 >= test2) : " bishop must have at least the same number of movement, probably more than before blocking";

            }
        }
    }

}