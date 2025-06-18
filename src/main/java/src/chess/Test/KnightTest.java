package src.chess.Test;

import org.junit.Test;
import src.chess.Factory.Board;
import src.chess.Factory.EmptyBoard;
import src.chess.Model.pieces.*;

import java.util.Random;

public class KnightTest {

    @Test
    public void knightTest() {

        Pieces knight_Black = new Knight(Color.BLACK);
        assert( !knight_Black.isBishop()): " knight is not a Bishop";
        assert( !knight_Black.isKing()) : " knight is not a king";
        assert( knight_Black.isKnight()) : " knight is a Knight";
        assert( !knight_Black.isPawn()) : " knight is not a Pawn";
        assert( !knight_Black.isQueen()) : " knight is not a Queen";
        assert( !knight_Black.isRook()) : " knight is not a Rook";

        Pieces knight_White = new Knight(Color.WHITE);
        assert( !knight_White.isBishop()): " knight is not a Bishop";
        assert( !knight_White.isKing()) : "knight is not a king";
        assert( knight_White.isKnight()) : " knight is a Knight";
        assert( !knight_White.isPawn()) : " knight is not a Pawn";
        assert( !knight_White.isQueen()) : " knight is not a Queen";
        assert( !knight_White.isRook()) : " knight is not a Rook";

    }

    @Test
    public void getSymboleTest(){

        Pieces Knight_White = new Knight(Color.WHITE);
        Pieces Knight_Black = new Knight(Color.BLACK);

        assert (Knight_White.getSymbol().equals("\u2658") ):" Must display a White Knight ";
        assert (Knight_White.getSymbol().equals(UnicodePieces.KNIGHT_WHITE) ):" Must display a White Knight ";
        assert (Knight_Black.getSymbol().equals("\u265E") ):" Must display a Black Knight ";
        assert (Knight_Black.getSymbol().equals(UnicodePieces.KNIGHT_BLACK) ):" Must display a Black Knight ";

    }

    @Test
    public void movementBlockedTest() {

        Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Pieces knight = new Knight(Color.BLACK);
            Board board = new EmptyBoard();
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x, y, knight);

            int[][] moves = {
                    {x + 2, y + 1},
                    {x + 2, y - 1},
                    {x - 2, y + 1},
                    {x - 2, y - 1},
                    {x + 1, y + 2},
                    {x + 1, y - 2},
                    {x - 1, y + 2},
                    {x - 1, y - 2}
            };

            for (int[] move : moves) {
                int mx = move[0];
                int my = move[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new Knight(Color.BLACK));
                }
            }

            assert (knight.movements(x, y, board).isEmpty()): " Knight have to be stuck ";



        }
    }

    @Test
    public void knightMovementAvailableTest() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Pieces knight = new Knight(Color.BLACK);
            Board board = new EmptyBoard();
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x, y, knight);

            int[][] moves = {
                    {x + 2, y + 1},
                    {x + 2, y - 1},
                    {x - 2, y + 1},
                    {x - 2, y - 1},
                    {x + 1, y + 2},
                    {x + 1, y - 2},
                    {x - 1, y + 2},
                    {x - 1, y - 2}
            };

            int test1 = knight.movements(x, y, board).size();
            assert (test1 >= 0) : "knight must have some movements available";

            for (int[] move : moves) {
                int mx = move[0];
                int my = move[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new Knight(Color.BLACK));
                }

                int test2 = knight.movements(x, y, board).size();
                assert (test1 >= test2) : "knight must have same or fewer moves after blocking";
            }
        }
    }

}