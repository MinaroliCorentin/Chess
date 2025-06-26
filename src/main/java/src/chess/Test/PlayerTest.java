package src.chess.Test;

import org.junit.Before;
import org.junit.Test;
import src.chess.Factory.*;
import src.chess.Model.Players.HumanPlayer;
import src.chess.Model.Players.Player;
import src.chess.Model.pieces.Color;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class PlayerTest {

    private Board bishopBoard;
    private Board kingBoard;
    private Board rookBoard;
    private Board board;
    private Board queenBoard;


    private Player playerBlackBishop;
    private Player playerWhiteBishop;
    private Player playerBlackKing;
    private Player playerWhiteKing;
    private Player playerBlackRook;
    private Player playerWhiteRook;
    private Player playerBlack;
    private Player playerWhite;
    private Player playerBlackQueen;
    private Player playerWhiteQueen;
    String from ;
    String to;

    @Before
    public void setUp() {

        board = new StandartBoard();
        playerBlack = new HumanPlayer(board, Color.BLACK);
        playerWhite = new HumanPlayer(board, Color.WHITE);

        bishopBoard = new BishopBoard();
        playerBlackBishop = new HumanPlayer(bishopBoard, Color.BLACK);
        playerWhiteBishop = new HumanPlayer(bishopBoard, Color.WHITE);

        kingBoard = new KingBoard();
        playerBlackKing = new HumanPlayer(kingBoard, Color.BLACK);
        playerWhiteKing = new HumanPlayer(kingBoard, Color.WHITE);

        rookBoard = new RookBoard();
        playerBlackRook = new HumanPlayer(rookBoard, Color.BLACK);
        playerWhiteRook = new HumanPlayer(rookBoard, Color.WHITE);

        queenBoard = new QueenBoard();
        playerBlackQueen = new HumanPlayer(queenBoard, Color.BLACK);
        playerWhiteQueen = new HumanPlayer(queenBoard, Color.WHITE);

    }

    @Test
    public void playerTest(){
;
        assert ( playerBlack.getColor() == Color.BLACK):" Player1 is black";
        assert ( playerWhite.getColor() == Color.WHITE):" Player2 is white";
        assert ( playerBlack.getScore() == 0 ):" Player1 have a score of 0";
        assert ( playerWhite.getScore() == 0 ):" Player2 have a score of 0";

    }

    @Test
    public void movePawnTest(){

        from = "h2";
        to = "h4";
        playerWhite.play(from,to);
        board.displayWithIndices();
        assertNull("must be empty cause the pawn has been moved", board.getPiece(2,7) );
        assertNotNull( " the pawn must be here now",board.getPiece(4,7));
        board.displayWithIndices();

        from = "a2";
        to = "a3";
        playerWhite.play(from,to);
        board.displayWithIndices();
        assertNull( " must be empty cause the pawn has been moved", board.getPiece(6,0) );
        assertNotNull( " the pawn must be here now", board.getPiece(5,0));

        from = "a7";
        to = "a5";
        playerBlack.play(from,to);
        assertNull("must be empty cause the pawn has been moved", board.getPiece(1,0) );
        assertNotNull( " the pawn must be here now",board.getPiece(3,0));
        board.displayWithIndices();

        from = "H7";
        to = "H6";
        playerBlack.play(from,to);
        assertNull( " must be empty cause the pawn has been moved", board.getPiece(1,7) );
        assertNotNull( " the pawn must be here now", board.getPiece(2,7));
        board.displayWithIndices();

    }

    @Test
    public void moveBishopTest(){

        bishopBoard.displayWithIndices();
        from = "c1";
        to = "a3";
        playerWhiteBishop.play(from,to);
        assertNull("must be empty because the bishop has been moved", bishopBoard.getPiece(7,2));
        assertNotNull("the bishop must be here now", bishopBoard.getPiece(5,0));

        from = "f8";
        to = "h6";
        playerBlackBishop.play(from,to);
        assertNull("must be empty because the bishop has been moved", bishopBoard.getPiece(0, 5));
        assertNotNull("the bishop must be here now", bishopBoard.getPiece(2, 7));

        from = "f1";
        to = "g2" ;
        playerWhiteBishop.play(from,to);
        assertNull("must be empty because the bishop has been moved", bishopBoard.getPiece(7, 5));
        assertNotNull("the bishop must be here now", bishopBoard.getPiece(6, 6));

        from = "c8";
        to = "b7";
        playerBlackBishop.play(from,to);
        bishopBoard.displayWithIndices();
        assertNull("must be empty because the bishop has been moved", bishopBoard.getPiece(0, 2));
        assertNotNull("the bishop must be here now", bishopBoard.getPiece(1, 1));

    }

    @Test
    public void moveKingTest() {

        kingBoard.display();
        kingBoard.displayWithIndices();

        from = "e1";
        to = "e2";
        playerWhiteKing.play(from, to);
        assertNull("Must be empty because the king has been moved from (7,4)", kingBoard.getPiece(7,4));
        assertNotNull("The king must be here now at (6,4)", kingBoard.getPiece(6,4));
        kingBoard.reset();

        from = "e8";
        to = "f8";
        playerBlackKing.play(from, to);
        assertNull("Must be empty because the king has been moved from (0,4)", kingBoard.getPiece(0,4));
        assertNotNull("The king must be here now at (0,5)", kingBoard.getPiece(0,5));
        kingBoard.reset();

        from = "e8";
        to = "d8";
        playerBlackKing.play(from, to);
        assertNull("Must be empty because the king has been moved from (0,4)", kingBoard.getPiece(0,4));
        assertNotNull("The king must be here now at (0,3)", kingBoard.getPiece(0,3));
        kingBoard.reset();

        from = "e8";
        to = "d7";
        playerBlackKing.play(from, to);
        assertNull("Must be empty because the king has been moved from (0,4)", kingBoard.getPiece(0,4));
        assertNotNull("The king must be here now at (1,3)", kingBoard.getPiece(1,3));
        kingBoard.reset();

        from = "e8";
        to = "f7";
        playerBlackKing.play(from, to);
        assertNull("Must be empty because the king has been moved from (0,4)", kingBoard.getPiece(0,4));
        assertNotNull("The king must be here now at (1,5)", kingBoard.getPiece(1,5));
        kingBoard.reset();

        from = "e8";
        to = "f7";
        playerBlackKing.play(from, to);
        assertNull("Must be empty because the king has been moved from (0,4)", kingBoard.getPiece(0,4));
        assertNotNull("The king must be here now at (1,5)", kingBoard.getPiece(1,5));

        String secondMove = "e8";
        playerBlackKing.play(to, secondMove);
        assertNull("Must be empty because the king has been moved from (1,5)", kingBoard.getPiece(1,5));
        assertNotNull("The king must be here now at (0,4)", kingBoard.getPiece(0,4));
        kingBoard.reset();
    }

    @Test
    public void moveKnightTest(){

        board.display();
        board.displayWithIndices();

        from = "B1";
        to = "A3";
        playerWhite.play(from, to);
        assertNull("Must be empty cause the knight has been moved from B1 ", board.getPiece(7,1));
        assertNotNull("The knight must be here now at A3", board.getPiece(5,0));

        from = "A3";
        to = "C4";
        playerWhite.play(from, to);
        assertNull("Must be empty cause the knight has been moved from A3 ", board.getPiece(5,0));
        assertNotNull("The knight must be here now at C4", board.getPiece(4,2));

        from = "C4";
        to = "A5";
        playerWhite.play(from, to);
        assertNull("Must be empty cause the knight has been moved from C4 ", board.getPiece(4,2));
        assertNotNull("The knight must be here now at A5", board.getPiece(3,0));

        from = "A5";
        to = "B7";
        playerWhite.play(from, to);
        assertNull("Must be empty cause the knight has been moved from A5 ", board.getPiece(3,0));
        assertNotNull("The knight must be here now at B1", board.getPiece(1,1));


    }

    @Test
    public void moveQueenTest(){

        from = "D1";
        to = "E1";
        playerWhiteQueen.play(from, to);

        queenBoard.display();
        queenBoard.displayWithIndices();

        assertNull("Must be empty cause the knight has been moved from D1 ", queenBoard.getPiece(7,3));
        assertNotNull("The knight must be here now at E1 ", queenBoard.getPiece(7,4));
        queenBoard.reset();

        from = "d1";
        to = "d3";
        playerWhiteQueen.play(from, to);
        assertNull ("D1 must be empty ", queenBoard.getPiece(7,3));
        assertNotNull("The Queen must be at D3", queenBoard.getPiece(5,3));
        queenBoard.reset();

        from = "d1";
        to = "a1";
        playerWhiteQueen.play(from, to);
        assertNull ("D1 must be empty", queenBoard.getPiece(7,3));
        assertNotNull("The Queen must be at A1", queenBoard.getPiece(7,0));
        queenBoard.reset();

        from = "d1";
        to = "g4";
        playerWhiteQueen.play(from, to);
        assertNull ("D1 must be empty ", queenBoard.getPiece(7,3));
        assertNotNull("The Queen must be at G4", queenBoard.getPiece(4,6));
        queenBoard.reset();

        from = "d8";
        to = "d6";
        playerBlackQueen.play(from, to);
        assertNull ("D8 must be empty", queenBoard.getPiece(0,3));
        assertNotNull("The Queen must be at D6", queenBoard.getPiece(2,3));
        queenBoard.reset();

    }

    @Test
    public void moveRookTest(){

        from = "a1";
        to = "a4";
        playerWhiteRook.play(from, to);
        assertNull ("A1 Must be Empty", rookBoard.getPiece(7, 0));
        assertNotNull(" White Rook must be at A4", rookBoard.getPiece(4, 0));
        rookBoard.reset();

        from = "a1";
        to = "h1";
        playerWhiteRook.play(from, to);
        assertNull ("A1 Must be Empty", rookBoard.getPiece(7, 0));
        assertNotNull(" White Rook must be at H1 ", rookBoard.getPiece(7, 7));
        rookBoard.reset();

        from = "a8";
        to = "h8";
        playerBlackRook.play(from, to);
        assertNull ("A8 Must be Empty", rookBoard.getPiece(0, 0));
        assertNotNull(" Black Rook must be at H8 ", rookBoard.getPiece(0, 7));
        rookBoard.reset();

        from = "a8";
        to = "a5";
        playerBlackRook.play(from, to);
        assertNull ("A8 Must be Empty", rookBoard.getPiece(0, 0));
        assertNotNull(" Black Rook must be at A5", rookBoard.getPiece(3, 0));
        rookBoard.reset();
    }


}
