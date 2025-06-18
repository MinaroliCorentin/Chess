package src.chess.Test;

import org.junit.Test;
import src.chess.Factory.Board;
import src.chess.Factory.EmptyBoard;
import src.chess.Factory.StandartBoard;
import src.chess.Model.pieces.*;

import java.util.Random;

public class QueenTest {
    
    public void queenTest(){
        
        Pieces queen_Black = new Queen(Color.BLACK);
        assert( !queen_Black.isBishop()): " queen is not a Bishop";
        assert( !queen_Black.isKing()) : " queen is not a king";
        assert( !queen_Black.isKnight()) : " queen is not a Knight";
        assert( !queen_Black.isPawn()) : " queen is not a Pawn";
        assert( queen_Black.isQueen()) : " queen is a Queen";
        assert( !queen_Black.isRook()) : " queen is not a Rook";

        Pieces queen_White = new Queen(Color.WHITE);
        assert( !queen_White.isBishop()): " queen is not a Bishop";
        assert( !queen_White.isKing()) : "queen is not a king";
        assert( !queen_White.isKnight()) : " queen is not a Knight";
        assert( !queen_White.isPawn()) : " queen is not a Pawn";
        assert( queen_White.isQueen()) : " queen is a Queen";
        assert( !queen_White.isRook()) : " queen is not a Rook";
        
    }

    @Test
    public void getSymbolTest(){

        Pieces Queen_Black = new Queen(Color.BLACK);
        Pieces Queen_White = new Queen(Color.WHITE);

        assert ( Queen_White.getSymbol().equals(UnicodePieces.QUEEN_WHITE)) : " Must display a White Queen ";
        assert ( Queen_White.getSymbol().equals("\u2655")) : " Must display a White Queen ";
        assert ( Queen_Black.getSymbol().equals(UnicodePieces.QUEEN_BLACK)) : " Must display a White Queen ";
        assert ( Queen_Black.getSymbol().equals("\u265B")) : " Must display a White Queen ";

    }

    @Test
    public void movementBlockedTest() {

        Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Board board = new EmptyBoard();
            Pieces queen = new Queen(Color.BLACK);
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x, y, queen);

            int[][] directions = {
                    {1, 0},
                    {-1, 0},
                    {0, 1},
                    {0, -1},
                    {1, 1},
                    {1, -1},
                    {-1, 1},
                    {-1, -1}
            };

            for (int[] dir : directions) {
                int mx = x + dir[0];
                int my = y + dir[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new King(Color.BLACK));
                }
            }

            assert (queen.movements(x, y, board).isEmpty()) : " Queen must have no movement available.";
        }
    }


    @Test
    public void queenMovementAvailableTest() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Pieces queen = new Queen(Color.BLACK);
            Board board = new EmptyBoard();
            int x = rand.nextInt(8);
            int y = rand.nextInt(8);

            board.setPiece(x, y, queen);

            int[][] moves = {
                    {x + 1, y + 1},
                    {x + 1, y - 1},
                    {x - 1, y + 1},
                    {x - 1, y - 1},
                    {x + 1, y},
                    {x - 1, y},
                    {x, y + 1},
                    {x, y - 1}
            };

            int test1 = queen.movements(x, y, board).size();
            assert (test1 >= 0) : "queen must have some movements available";

            for (int[] move : moves) {
                int mx = move[0];
                int my = move[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new Queen(Color.BLACK));
                }

                int test2 = queen.movements(x, y, board).size();
                assert (test1 >= test2) : "queen must have same or fewer moves after blocking";
            }
        }
    }


}