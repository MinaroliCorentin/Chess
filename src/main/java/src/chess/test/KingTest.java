package src.chess.test;

import org.junit.Test;
import src.chess.factory.Board;
import src.chess.factory.EmptyBoard;
import src.chess.model.pieces.*;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;

import java.util.Random;

import static org.junit.Assert.assertThrows;

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
        assert ( King_Black.getSymbol().equals("\u2654")):" Should display a black king ";
        assert ( King_White.getSymbol().equals(UnicodePieces.KING_WHITE)):" Should display a white king ";
        assert ( King_White.getSymbol().equals("\u265A")):" Should display a black king ";

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

    @Test
    public void kingMoveInCheckTest(){

        Board board = new EmptyBoard();
        Pieces king = new King(Color.BLACK);
        Pieces rook1 = new Rook(Color.WHITE);

        board.setPiece(0,1,king);
        board.setPiece(1,0,rook1);

        board.display();

        Player p1 = new HumanPlayer(board,Color.BLACK);
        assertThrows(IllegalStateException.class,()->{ p1.play("B8","A8");});

    }

    @Test
    public void PiecesMoveKingInCheckTest(){

        Board board = new EmptyBoard();
        Pieces king = new King(Color.BLACK);
        Pieces rook = new Rook(Color.WHITE);
        Pieces bishop = new Bishop(Color.BLACK);

        board.setPiece(0,1,king);
        board.setPiece(1,1,bishop);
        board.setPiece(2,1,rook);

        Player p1 = new HumanPlayer(board,Color.BLACK);
        assertThrows(IllegalStateException.class,()->{ p1.play("B7","C6");});

        board.display();
        board.reset();

        Pieces king1 = new King(Color.WHITE);
        Pieces rook1 = new Rook(Color.BLACK);
        Pieces bishop1 = new Bishop(Color.WHITE);

        board.setPiece(0,1,king1);
        board.setPiece(1,1,bishop1);
        board.setPiece(2,1,rook1);

        Player p2 = new HumanPlayer(board,Color.WHITE);
        assertThrows(IllegalStateException.class,()->{ p2.play("B7","C6");});

        board.display();

    }

}
