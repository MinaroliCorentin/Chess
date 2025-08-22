package src.chess.test;

import org.junit.Test;
import src.chess.factory.Board;
import src.chess.factory.EmptyBoard;
import src.chess.factory.RookBoard;
import src.chess.model.pieces.*;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;

import java.util.Random;

public class RookTest {

    @Test
    public void rookTest(){

        Pieces rook_Black = new Rook(PiecesColor.BLACK);
        assert( !rook_Black.isBishop()): " rook is not a Bishop";
        assert( !rook_Black.isKing()) : " rook is not a king";
        assert( !rook_Black.isKnight()) : " rook is not a Knight";
        assert( !rook_Black.isPawn()) : " rook is not a Pawn";
        assert( !rook_Black.isQueen()) : " rook is not a Queen";
        assert( rook_Black.isRook()) : " rook is a Rook";

        Pieces rook_White = new Rook(PiecesColor.WHITE);
        assert( !rook_White.isBishop()): " rook is not a Bishop";
        assert( !rook_White.isKing()) : " rook is not a king";
        assert( !rook_White.isKnight()) : " rook is not a Knight";
        assert( !rook_White.isPawn()) : " rook is not a Pawn";
        assert( !rook_White.isQueen()) : " rook is not a Queen";
        assert( rook_White.isRook()) : " rook is a Rook";

    }

    @Test
    public void getSymbolTest(){

        Pieces Rook_Black = new Rook(PiecesColor.BLACK);
        Pieces Rook_White = new Rook(PiecesColor.WHITE);

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
            Pieces rook = new Rook(PiecesColor.BLACK);
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
                    board.setPiece(mx, my, new King(PiecesColor.BLACK));
                }
            }

            assert (rook.movements(x, y, board).isEmpty()) : " Rook must have no movement available.";
        }
    }

    @Test
    public void movementAvailableTest() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {

            Pieces bishop_Black = new Bishop(PiecesColor.BLACK);
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
                    board.setPiece(mx, my, new Bishop(PiecesColor.BLACK));
                }

                int test2 = bishop_Black.movements(x, y, board).size();
                assert (test1 >= test2) : " bishop must have at least the same number of movement, probably more than before blocking";

            }
        }
    }

    @Test
    public void leftRookMovedBlackTest(){

        RookBoard rookBoard = new RookBoard();
        Player playerBlackRook = new HumanPlayer(rookBoard, PiecesColor.BLACK);

        String from = "A8";
        String to = "A7";

        Rook rookBefore = (Rook) rookBoard.getPiece(0, 0);
        assert (!rookBefore.isLeftRookMoved()): "The left rook didn't move yet";

        playerBlackRook.play(from,to);

        Rook rookAfter = (Rook) rookBoard.getPiece(1, 0);
        assert (rookAfter.isLeftRookMoved()): "The left rook did move";

    }

    @Test
    public void rightRookMovedBlackTest(){

        RookBoard rookBoard = new RookBoard();
        Player playerBlackRook = new HumanPlayer(rookBoard, PiecesColor.BLACK);

        String from = "H8";
        String to = "H7";

        Rook rookBefore = (Rook) rookBoard.getPiece(0, 7);
        assert (!rookBefore.isRightRookMoved()): "The left rook didn't move yet";

        playerBlackRook.play(from,to);

        Rook rookAfter = (Rook) rookBoard.getPiece(1, 7);
        assert (rookAfter.isRightRookMoved()): "The left rook did move";

    }

    @Test
    public void leftRookMovedWhiteTest(){

        RookBoard rookBoard = new RookBoard();
        Player playerWhiteRook = new HumanPlayer(rookBoard, PiecesColor.WHITE);

        String from = "A1"; // (7, 0)
        String to = "B1";   // (7, 1)

        Rook rookBefore = (Rook) rookBoard.getPiece(7, 0);
        assert (!rookBefore.isLeftRookMoved()): "The left rook didn't move yet";

        playerWhiteRook.play(from, to);

        Rook rookAfter = (Rook) rookBoard.getPiece(7, 1); // et non (1, 7)
        assert (rookAfter.isLeftRookMoved()): "The left rook did move";

        rookBoard.displayWithIndices();
        rookBoard.display();

    }

    @Test
    public void rightRookMovedWhiteTest(){

        RookBoard rookBoard = new RookBoard();
        Player playerWhiteRook = new HumanPlayer(rookBoard, PiecesColor.WHITE);

        String from = "H1";
        String to = "H2";

        Rook rookBefore = (Rook) rookBoard.getPiece(7, 7);
        assert (!rookBefore.isRightRookMoved()): "The right rook didn't move yet";

        playerWhiteRook.play(from, to);

        Rook rookAfter = (Rook) rookBoard.getPiece(6, 7);
        assert (rookAfter.isRightRookMoved()): "The right rook did move";

    }

}