package src.chess.test;

import org.junit.Before;
import org.junit.Test;
import src.chess.factory.*;
import src.chess.model.pieces.Color;
import src.chess.model.pieces.Pieces;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GameplayTest {

    private Board bishopBoard;
    private Board kingBoard;
    private Board rookBoard;
    private Board board;
    private Board queenBoard;
    private Board castlingBoard ;

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
    private Player playerBlackCastling ;
    private Player playerWhiteCastling;
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

        castlingBoard = new CastlingBoard();
        playerBlackCastling = new HumanPlayer(castlingBoard, Color.BLACK);
        playerWhiteCastling = new HumanPlayer(castlingBoard, Color.WHITE);

    }


    @Test
    public void movePawnTest(){

        from = "h2";
        to = "h4";
        playerWhite.play(from,to);
        assertNull("must be empty cause the pawn has been moved", board.getPiece(2,7) );
        assertNotNull( " the pawn must be here now",board.getPiece(4,7));

        from = "a2";
        to = "a3";
        playerWhite.play(from,to);
        assertNull( " must be empty cause the pawn has been moved", board.getPiece(6,0) );
        assertNotNull( " the pawn must be here now", board.getPiece(5,0));

        from = "a7";
        to = "a5";
        playerBlack.play(from,to);
        assertNull("must be empty cause the pawn has been moved", board.getPiece(1,0) );
        assertNotNull( " the pawn must be here now",board.getPiece(3,0));

        from = "H7";
        to = "H6";
        playerBlack.play(from,to);
        assertNull( " must be empty cause the pawn has been moved", board.getPiece(1,7) );
        assertNotNull( " the pawn must be here now", board.getPiece(2,7));

    }

    @Test
    public void moveBishopTest(){

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
        assertNull("must be empty because the bishop has been moved", bishopBoard.getPiece(0, 2));
        assertNotNull("the bishop must be here now", bishopBoard.getPiece(1, 1));

    }

    @Test
    public void moveKingTest() {

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
    public void moveRookTest() {

        from = "a1";
        to = "a4";
        playerWhiteRook.play(from, to);
        assertNull("A1 must be empty", rookBoard.getPiece(7, 0));
        assertNotNull("White Rook must be at A4", rookBoard.getPiece(4, 0));
        rookBoard.reset();

        from = "a1";
        to = "g1";
        playerWhiteRook.play(from, to);
        assertNull("A1 must be empty", rookBoard.getPiece(7, 0));
        assertNotNull("White Rook must be at G1", rookBoard.getPiece(7, 6));
        rookBoard.reset();

        from = "A8";
        to = "G8";
        playerBlackRook.play(from, to);
        assertNull("A8 must be empty", rookBoard.getPiece(0, 0));
        assertNotNull("Black Rook must be at G8", rookBoard.getPiece(0, 6));
        rookBoard.reset();

        from = "a8";
        to = "a5";
        playerBlackRook.play(from, to);
        assertNull("A8 must be empty", rookBoard.getPiece(0, 0));
        assertNotNull("Black Rook must be at A5", rookBoard.getPiece(3, 0));
        rookBoard.reset();
    }

    @Test
    public void kingSideBlackCastling() {

        from = "E8";
        to = "H8";
        playerBlackCastling.play(from, to);

        Pieces king = castlingBoard.getPiece(0,6);
        assertNotNull("King must be at G8 (0,6)", king);
        assertTrue("The piece at G8 should be the king", king.isKing());

        Pieces rook = castlingBoard.getPiece(0,5);
        assertNotNull("Rook must be at F8 (0,5)", rook);
        assertTrue("The piece at F8 should be the rook", rook.isRook());

        assertNull("E8 should be empty after castling", castlingBoard.getPiece(0,4));
        assertNull("H8 should be empty after castling", castlingBoard.getPiece(0,7));
    }

    @Test
    public void queenSideBlackCastling(){

        from = "E8";
        to = "A8";
        playerBlackCastling.play(from, to);

        Pieces king = castlingBoard.getPiece(0,2);
        assertNotNull("King must be at C8 (0,2)", king);
        assertTrue("The piece at C8 should be the king", king.isKing());

        Pieces rook = castlingBoard.getPiece(0,3);
        assertNotNull("Rook must be at D8 (0,3)", rook);
        assertTrue("The piece at D8 should be the rook", rook.isRook());

        assertNull("C8 should be empty after castling", castlingBoard.getPiece(0,4));
        assertNull("D8 should be empty after castling", castlingBoard.getPiece(0,0));

    }

    @Test
    public void kingSideWhiteCastling(){

        from = "E1";
        to = "H1";
        playerWhiteCastling.play(from, to);

        Pieces king = castlingBoard.getPiece(7,6);
        assertNotNull("King must be at G1 (7,6)", king);
        assertTrue("The piece at G1 should be the king", king.isKing());

        Pieces rook = castlingBoard.getPiece(7,5);
        assertNotNull("Rook must be at F1 (7,5)", rook);
        assertTrue("The piece at F8 should be the rook", rook.isRook());

        assertNull("E8 should be empty after castling", castlingBoard.getPiece(7,4));
        assertNull("H8 should be empty after castling", castlingBoard.getPiece(7,7));

    }

    @Test
    public void queenSideWhiteCastling(){

        from = "E1";
        to = "A1";
        playerWhiteCastling.play(from, to);

        Pieces king = castlingBoard.getPiece(7,2);
        assertNotNull("King must be at C8 (7,2)", king);
        assertTrue("The piece at C8 should be the king", king.isKing());

        Pieces rook = castlingBoard.getPiece(7,3);
        assertNotNull("Rook must be at D8 (7,3)", rook);
        assertTrue("The piece at D8 should be the rook", rook.isRook());

        assertNull("C8 should be empty after castling", castlingBoard.getPiece(7,4));
        assertNull("D8 should be empty after castling", castlingBoard.getPiece(7,0));

    }

    @Test
    public void enPassantTest(){

        from = "A7";
        to = "A5";
        playerBlack.play(from,to);

        from = "H2";
        to = "H3";
        playerWhite.play(from,to);

        from = "A5";
        to = "A4";
        playerBlack.play(from,to);

        from = "B2";
        to = "B4";
        playerWhite.play(from,to);

        from = "A4";
        to = "B3";
        playerBlack.play(from,to);

        //assertNull("The pawn have to be null", board.getPiece(4,1));

    }

}
