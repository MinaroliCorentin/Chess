package src.chess.test;

import org.junit.Test;
import src.chess.factory.Board;
import src.chess.factory.EmptyBoard;
import src.chess.model.pieces.*;
import java.util.Random;

public class BishopTest {

    @Test
    public void bishopTest() {

        Pieces bishop_Black = new Bishop(Color.BLACK);
        assert (bishop_Black.isBishop()) : " Bishop is a Bishop";
        assert (!bishop_Black.isKing()) : " Bishop isn't a King";
        assert (!bishop_Black.isKnight()) : " Bishop is not a Knight";
        assert (!bishop_Black.isPawn()) : " Bishop is not a Pawn";
        assert (!bishop_Black.isQueen()) : " Bishop is not a Queen";
        assert (!bishop_Black.isRook()) : " Bishop is not a Rook";

        Pieces bishop_White = new Bishop(Color.WHITE);
        assert (bishop_White.isBishop()) : " Bishop is a Bishop";
        assert (!bishop_White.isKing()) : " Bishop isn't a King";
        assert (!bishop_White.isKnight()) : " Bishop is not a Knight";
        assert (!bishop_Black.isPawn()) : " Bishop is not a Pawn";
        assert (!bishop_Black.isQueen()) : " Bishop is not a Queen";
        assert (!bishop_Black.isRook()) : " Bishop is not a Rook";

    }

    @Test
    public void getSymboleTest() {

        Pieces bishop_Black = new Bishop(Color.BLACK);
        Pieces bishop_White = new Bishop(Color.WHITE);

        assert (bishop_Black.getSymbol().equals("\u2657")) : " Should display black bishop ";
        assert (bishop_White.getSymbol().equals("\u265D")) : " Should display white bishop ";

        assert (bishop_Black.getSymbol().equals(UnicodePieces.BISHOP_BLACK)) : " Should display black bishop ";
        assert (bishop_White.getSymbol().equals(UnicodePieces.BISHOP_WHITE)) : " Should display white bishop ";

    }


    @Test
    public void movementBlockedTest() {

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

            for (int[] move : moves) {
                int mx = move[0];
                int my = move[1];
                if (board.isBound(mx, my)) {
                    board.setPiece(mx, my, new Bishop(Color.BLACK));
                }
            }

            assert (bishop_Black.movements(x, y, board).isEmpty()) : " The bishop have no movement available";

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