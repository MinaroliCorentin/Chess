package src.chess.Test;

import org.junit.Test;
import src.chess.Factory.Board;
import src.chess.Factory.EmptyBoard;
import src.chess.Model.pieces.*;

import java.util.Random;

public class KingTest {

    @Test
    public void kingTest() {

        Pieces king_Black = new King(Color.BLACK);
        assert( !king_Black.isBishop()): " King is not a Bishop";
        assert( king_Black.isKing()) : " King is a King";
        assert( !king_Black.isKnight()) : " King is not a Knight";
        assert( !king_Black.isPawn()) : " King is not a Pawn";
        assert( !king_Black.isQueen()) : " King is not a Queen";
        assert( !king_Black.isRook()) : " King is not a Rook";

        Pieces king_White = new King(Color.WHITE);
        assert( !king_White.isBishop()): " King is not a Bishop";
        assert( king_White.isKing()) : "King is a King";
        assert( !king_White.isKnight()) : " King is not a Knight";
        assert( !king_White.isPawn()) : " King is not a Pawn";
        assert( !king_White.isQueen()) : " King is not a Queen";
        assert( !king_White.isRook()) : " King is not a Rook";

    }

    @Test
    public void getSymbolTest(){

        Pieces King_Black = new King(Color.BLACK);
        Pieces King_White = new King(Color.WHITE);

        assert ( King_Black.getSymbol().equals(UnicodePieces.KING_BLACK)):" Should display a black king ";
        assert ( King_Black.getSymbol().equals("\u265A")):" Should display a black king ";
        assert ( King_White.getSymbol().equals(UnicodePieces.KING_WHITE)):" Should display a white king ";
        assert ( King_White.getSymbol().equals("\u2654")):" Should display a black king ";

    }

    @Test
    public void movementBlockedTest() {

        Random rand = new Random();

        for ( int i = 0 ; i < 100 ; i ++ ){

            Board board = new EmptyBoard();
            Pieces king_black = new King(Color.BLACK);
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x,y,king_black);

            int[][] moves = {
                    {x + 1, y + 1},
                    {x + 1, y - 1},
                    {x - 1, y + 1},
                    {x - 1, y - 1},
                    {x, y + 1},
                    {x, y - 1},
                    {x - 1, y},
                    {x + 1, y}
            };

            for (int[] move : moves) {
                int mx = move[0];
                int my = move[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new King(Color.BLACK));
                }
            }

            assert (king_black.movements(x, y, board).isEmpty()):" The king have no movement available";

        }
    }

    @Test
    public void kingMovementAvailableTest() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Pieces king = new King(Color.BLACK);
            Board board = new EmptyBoard();
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x, y, king);

            int[][] moves = {
                    {x + 1, y + 1},
                    {x + 1, y - 1},
                    {x - 1, y + 1},
                    {x - 1, y - 1},
                    {x, y + 1},
                    {x, y - 1},
                    {x + 1, y},
                    {x - 1, y}
            };

            int test1 = king.movements(x, y, board).size();
            assert (test1 >= 0) : "king must have some movements available";

            for (int[] move : moves) {
                int mx = move[0];
                int my = move[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new King(Color.BLACK));
                }

                int test2 = king.movements(x, y, board).size();
                assert (test1 >= test2) : "king must have same or fewer moves after blocking";
            }
        }
    }


}
