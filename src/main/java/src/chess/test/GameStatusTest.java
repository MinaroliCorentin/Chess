package src.chess.test;

import org.junit.Test;
import src.chess.factory.*;
import src.chess.gamemanagement.GameManagementTerminal;
import src.chess.gamestatus.GameStatus;
import src.chess.model.handler.CastlingHandler;
import src.chess.model.pieces.*;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;
import src.chess.gamestatus.GameStatusTerminal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GameStatusTest {

    private List<Localisation> threateningPieces(Board board, PiecesColor kingColor) {
        List<Localisation> result = new ArrayList<>();

        Localisation kingLoc = null;
        for (Map.Entry<Localisation, Pieces> e : board.getPiecesMap().entrySet()) {
            Pieces p = e.getValue();
            if (p.isKing() && p.getColor() == kingColor) {
                kingLoc = e.getKey();
                break;
            }
        }
        if (kingLoc == null) return result;

        for (Map.Entry<Localisation, Pieces> e : board.getPiecesMap().entrySet()) {
            Pieces p = e.getValue();
            if (p.getColor() == kingColor) continue;

            List<Localisation> moves = p.movements(e.getKey().getX(), e.getKey().getY(), board);
            for (Localisation m : moves) {
                if (m.equals(kingLoc) && !result.contains(e.getKey())) {
                    result.add(e.getKey());
                    break;
                }
            }
        }
        return result;
    }

    @Test
    public void promotionAsBlackRookTest() {

        String testInput = "1";
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player BlackPawnPlayer = new HumanPlayer(board, PiecesColor.BLACK, "Black", new GameStatusTerminal(board));

            BlackPawnPlayer.play("H7", "H5");
            BlackPawnPlayer.play("H5", "H4");
            BlackPawnPlayer.play("H4", "H3");
            BlackPawnPlayer.play("H3", "H2");
            BlackPawnPlayer.play("H2", "H1");

            GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
            gameStatusTerminal.promoting();
            assert (board.getPiece(7, 7).isRook()) : " The Piece at H1 (7,7) must be a rook";

        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void promotionAsBlackKnigthTest() {

        String testInput = "2";
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player BlackPawnPlayer = new HumanPlayer(board, PiecesColor.BLACK, "Black", new GameStatusTerminal(board));

            BlackPawnPlayer.play("H7", "H5");
            BlackPawnPlayer.play("H5", "H4");
            BlackPawnPlayer.play("H4", "H3");
            BlackPawnPlayer.play("H3", "H2");
            BlackPawnPlayer.play("H2", "H1");

            GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
            gameStatusTerminal.promoting();
            assert (board.getPiece(7, 7).isKnight()) : " The Piece at H1 (7,7) must be a Knight";

        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void promotionAsBlackBishopTest() {

        String testInput = "3";
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player BlackPawnPlayer = new HumanPlayer(board, PiecesColor.BLACK, "Black", new GameStatusTerminal(board));

            BlackPawnPlayer.play("H7", "H5");
            BlackPawnPlayer.play("H5", "H4");
            BlackPawnPlayer.play("H4", "H3");
            BlackPawnPlayer.play("H3", "H2");
            BlackPawnPlayer.play("H2", "H1");

            GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
            gameStatusTerminal.promoting();
            assert (board.getPiece(7, 7).isBishop()) : " The Piece at H1 (7,7) must be a Bishop";

        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void promotionAsBlackQueenTest() {

        String testInput = "4";
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player BlackPawnPlayer = new HumanPlayer(board, PiecesColor.BLACK, "Black", new GameStatusTerminal(board));

            BlackPawnPlayer.play("H7", "H5");
            BlackPawnPlayer.play("H5", "H4");
            BlackPawnPlayer.play("H4", "H3");
            BlackPawnPlayer.play("H3", "H2");
            BlackPawnPlayer.play("H2", "H1");

            GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
            gameStatusTerminal.promoting();
            assert (board.getPiece(7, 7).isQueen()) : " The Piece at H1 (7,7) must be a queen";

        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void promotionAsWhiteRookTest() {

        String testInput = "1";
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player WhitePawnPlayer = new HumanPlayer(board, PiecesColor.WHITE, "White", new GameStatusTerminal(board));

            WhitePawnPlayer.play("A2", "A4");
            WhitePawnPlayer.play("A4", "A5");
            WhitePawnPlayer.play("A5", "A6");
            WhitePawnPlayer.play("A6", "A7");
            WhitePawnPlayer.play("A7", "A8");

            GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
            gameStatusTerminal.promoting();
            assert (board.getPiece(0, 0).isRook()) : " The Piece at A8 (0,0) must be a rook";

        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void promotionAsWhiteKnigthTest() {

        String testInput = "2";
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player WhitePawnPlayer = new HumanPlayer(board, PiecesColor.WHITE, "White", new GameStatusTerminal(board));

            WhitePawnPlayer.play("A2", "A4");
            WhitePawnPlayer.play("A4", "A5");
            WhitePawnPlayer.play("A5", "A6");
            WhitePawnPlayer.play("A6", "A7");
            WhitePawnPlayer.play("A7", "A8");

            GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
            gameStatusTerminal.promoting();
            assert (board.getPiece(0, 0).isKnight()) : " The Piece at A8 (0,0) must be a knight";

        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void promotionAsWhiteBishopTest() {

        String testInput = "3";
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player WhitePawnPlayer = new HumanPlayer(board, PiecesColor.WHITE, "White", new GameStatusTerminal(board));

            WhitePawnPlayer.play("A2", "A4");
            WhitePawnPlayer.play("A4", "A5");
            WhitePawnPlayer.play("A5", "A6");
            WhitePawnPlayer.play("A6", "A7");
            WhitePawnPlayer.play("A7", "A8");

            GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
            gameStatusTerminal.promoting();
            assert (board.getPiece(0, 0).isBishop()) : " The Piece at A8 (0,0) must be a Bishop";

        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void promotionAsWhiteQueenTest() {

        String testInput = "4";
        InputStream originalIn = System.in;

        try {
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player WhitePawnPlayer = new HumanPlayer(board, PiecesColor.WHITE, "White", new GameStatusTerminal(board));

            WhitePawnPlayer.play("A2", "A4");
            WhitePawnPlayer.play("A4", "A5");
            WhitePawnPlayer.play("A5", "A6");
            WhitePawnPlayer.play("A6", "A7");
            WhitePawnPlayer.play("A7", "A8");

            GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
            gameStatusTerminal.promoting();
            assert (board.getPiece(0, 0).isQueen()) : " The Piece at A8 (0,0) must be a Queen";

        } finally {
            System.setIn(originalIn);
        }
    }

    @Test
    public void checkMateWhiteTest() {

        EmptyBoard board = new EmptyBoard();
        board.setPiece(0, 2, new King(PiecesColor.WHITE));
        board.setPiece(0, 1, new Pawn(PiecesColor.BLACK));
        board.setPiece(0, 3, new Pawn(PiecesColor.BLACK));
        board.setPiece(1, 3, new Pawn(PiecesColor.BLACK));
        board.setPiece(1, 1, new Pawn(PiecesColor.BLACK));
        board.setPiece(1, 2, new Pawn(PiecesColor.BLACK));

        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
        boolean checkmate1 = gameStatusTerminal.isCheckmate(PiecesColor.WHITE);
        assert (!checkmate1) : " The king is surrounded by pawn but can move ";
        board.reset();

        board.setPiece(0, 2, new King(PiecesColor.WHITE));
        board.setPiece(0, 1, new Queen(PiecesColor.BLACK));
        board.setPiece(0, 3, new Queen(PiecesColor.BLACK));
        board.setPiece(1, 3, new Queen(PiecesColor.BLACK));
        board.setPiece(1, 1, new Queen(PiecesColor.BLACK));
        board.setPiece(1, 2, new Queen(PiecesColor.BLACK));
        boolean checkmate2 = gameStatusTerminal.isCheckmate(PiecesColor.WHITE);
        assert (checkmate2) : " The king is surrounded by Queen and cannot move";

    }

    @Test
    public void checkMateBlackTest() {

        EmptyBoard board = new EmptyBoard();
        board.setPiece(0, 2, new King(PiecesColor.BLACK));
        board.setPiece(0, 1, new Pawn(PiecesColor.WHITE));
        board.setPiece(0, 3, new Pawn(PiecesColor.WHITE));
        board.setPiece(1, 3, new Pawn(PiecesColor.WHITE));
        board.setPiece(1, 1, new Pawn(PiecesColor.WHITE));
        board.setPiece(1, 2, new Pawn(PiecesColor.WHITE));

        board.display();

        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
        boolean checkmate1 = gameStatusTerminal.isCheckmate(PiecesColor.BLACK);
        assertFalse(" The king is surrounded by pawn but can escape ", checkmate1);
        board.reset();

        board.setPiece(0, 2, new King(PiecesColor.BLACK));
        board.setPiece(0, 1, new Queen(PiecesColor.WHITE));
        board.setPiece(0, 3, new Queen(PiecesColor.WHITE));
        board.setPiece(1, 3, new Queen(PiecesColor.WHITE));
        board.setPiece(1, 1, new Queen(PiecesColor.WHITE));
        board.setPiece(1, 2, new Queen(PiecesColor.WHITE));
        boolean checkmate2 = gameStatusTerminal.isCheckmate(PiecesColor.BLACK);
        assertTrue(" The king is surrounded by Queen and cannot move", checkmate2);
    }


    @Test
    public void drawnCounterGetterAndSetterTest() {

        Board board = new EmptyBoard();
        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
        assert (gameStatusTerminal.getDrawCounter() == 0) : "Have to be 0";
        gameStatusTerminal.setDrawCounter(10);
        assert (gameStatusTerminal.getDrawCounter() == 10) : "Have to be 10";

    }

    @Test
    public void drawnCounterTest() {

        Board board = new EmptyBoard();
        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
        assert (gameStatusTerminal.getDrawCounter() == 0) : "Have to be 0";
        assertFalse("Have to be false", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(10);
        assertFalse("Have to be false", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(20);
        assertFalse("Have to be false", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(30);
        assertFalse("Have to be false", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(40);
        assertFalse("Have to be false", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(50);
        assertFalse("Have to be false", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(99);
        assertFalse("Have to be false", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(100);
        assertTrue("Have to be true", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(101);
        assertTrue("Have to be true", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(-10);
        assertFalse("Have to be false", gameStatusTerminal.isDraw());

    }

    @Test
    public void resetDrawCounterTest() {

        Board board = new EmptyBoard();
        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
        gameStatusTerminal.setDrawCounter(10);
        assert (gameStatusTerminal.getDrawCounter() == 10) : "Have to be 10";

        gameStatusTerminal.resetDrawCounter();
        assert (gameStatusTerminal.getDrawCounter() == 0) : "Have to be 0";

    }

    @Test
    public void PiecesThreateningKingBlackTest() {

        EmptyBoard board = new EmptyBoard();
        List<Localisation> testList;

        board.setPiece(2, 2, new King(PiecesColor.BLACK));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.isEmpty()) : " 0 pieces are threatening the king ";

        board.setPiece(2, 1, new Queen(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";

        board.setPiece(2, 3, new Queen(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.size() == 2) : " 2 pieces are threatening the king ";

        board.setPiece(3, 2, new Queen(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.size() == 3) : " 3 pieces are threatening the king ";

        board.setPiece(1, 2, new Queen(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.size() == 4) : " 4 pieces are threatening the king ";

    }

    @Test
    public void PiecesThreateningKingWhiteTest() {

        EmptyBoard board = new EmptyBoard();
        List<Localisation> testList;

        board.setPiece(2, 2, new King(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.WHITE);
        assert (testList.isEmpty()) : " 0 pieces are threatening the king ";

        board.setPiece(2, 1, new Queen(PiecesColor.BLACK));
        testList = threateningPieces(board, PiecesColor.WHITE);
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";

        board.setPiece(2, 3, new Queen(PiecesColor.BLACK));
        testList = threateningPieces(board, PiecesColor.WHITE);
        assert (testList.size() == 2) : " 2 pieces are threatening the king ";

        board.setPiece(3, 2, new Queen(PiecesColor.BLACK));
        testList = threateningPieces(board, PiecesColor.WHITE);
        assert (testList.size() == 3) : " 3 pieces are threatening the king ";

        board.setPiece(1, 2, new Queen(PiecesColor.BLACK));
        testList = threateningPieces(board, PiecesColor.WHITE);
        assert (testList.size() == 4) : " 4 pieces are threatening the king ";

    }

    @Test
    public void PiecesThreateningKingBlackTest2() {

        EmptyBoard board = new EmptyBoard();
        List<Localisation> testList;

        board.setPiece(2, 2, new King(PiecesColor.BLACK));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.isEmpty()) : " 0 pieces are threatening the king ";

        board.setPiece(2, 6, new Queen(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";

        board.setPiece(3,5,new Pawn(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        System.out.println(testList.size());
        assert (testList.size() == 1) : " The pawn isn't threatening the king ";

    }

    @Test
    public void PiecesThreateningKingWhiteTest2() {

        EmptyBoard board = new EmptyBoard();
        List<Localisation> testList;

        board.setPiece(2, 2, new King(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.WHITE);
        assert (testList.isEmpty()) : " 0 pieces are threatening the king ";

        board.setPiece(2, 1, new Queen(PiecesColor.BLACK));
        testList = threateningPieces(board, PiecesColor.WHITE);
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";

        board.setPiece(2,5,new Pawn(PiecesColor.BLACK));
        testList = threateningPieces(board, PiecesColor.WHITE);
        assert (testList.size() == 1) : " The pawn isn't threatening the king ";

    }

    @Test
    public void PiecesThreateningKingTest(){

        Board board = new EmptyBoard();
        List<Localisation> testList;
        board.setPiece(0,4, new King(PiecesColor.BLACK));

        board.setPiece(0,7, new Queen(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";
        assert (testList.contains(new Localisation(0,7)));
        testList.clear();

        board.setPiece(0,3,new Rook(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.size() == 2) : " 2 pieces are threatening the king ";
        assert (testList.contains(new Localisation(0,3)));
        testList.clear();

        board.setPiece(1,3,new Pawn(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        assert (testList.size() == 3) : " 3 pieces are threatening the king ";
        assert (testList.contains(new Localisation(1,3)));
        testList.clear();

        board.setPiece(1,5,new Bishop(PiecesColor.WHITE));
        testList = threateningPieces(board, PiecesColor.BLACK);
        board.displayWithIndices();
        assert (testList.size() == 4) : " 4 pieces are threatening the king ";
        assert (testList.contains(new Localisation(1,5)));
        testList.clear();

    }

    @Test
    public void CanParryTest() {

        Board board = new EmptyBoard();
        board.setPiece(0, 4, new King(PiecesColor.BLACK));
        board.setPiece(0, 3, new Queen(PiecesColor.BLACK));
        board.setPiece(1, 4, new Queen(PiecesColor.WHITE));

        GameStatus gameStatus = new GameStatusTerminal(board);
        List<Localisation> threateningPieces = threateningPieces(board, PiecesColor.BLACK);
        assertEquals("Only one piece threaten the king", 1, threateningPieces.size());
        assertFalse("Queen can protect so no checkmate",
                gameStatus.isCheckmate(PiecesColor.BLACK));

        board.setPiece(0, 5, new Rook(PiecesColor.WHITE));
        threateningPieces = threateningPieces(board, PiecesColor.BLACK);
        assertEquals("Two pieces threaten the king ", 2, threateningPieces.size());
        assertFalse("King can capture Queen in (1,4)",
                gameStatus.isCheckmate(PiecesColor.BLACK));
    }

    @Test
    public void playerBaseOnRoundTest() {

        Board board = new EmptyBoard();
        GameStatus status = new GameStatusTerminal(board);
        Player white = new HumanPlayer(board, PiecesColor.WHITE, "White", status);
        Player black = new HumanPlayer(board, PiecesColor.BLACK, "Black", status);
        GameManagementTerminal game = new GameManagementTerminal(board, white, black, status);

        game.setRounds(0);
        assertTrue("Round 0 doit être White",
                game.getPlayerBaseOnRound() == white);
        assertEquals("Round 0 : White", "White", game.getPlayerNameBasedOnRound());

        game.setRounds(1);
        assertTrue("Round 1 must be Black turn",
                game.getPlayerBaseOnRound() == black);
        assertEquals("Round 1 : Black", "Black", game.getPlayerNameBasedOnRound());

        game.setRounds(2);
        assertTrue("Round 2 must be White turn",
                game.getPlayerBaseOnRound() == white);
    }

    @Test
    public void castlingRightSideBlockedByRightRookTest() {

        CastlingBoard board = new CastlingBoard();
        Rook rightRook = (Rook) board.getPiece(7, 7);
        rightRook.setRightRookMoved(true);

        PiecesStatus status = new PiecesStatus(board);
        assertFalse("King castling have to be blocked because the rook moved",
                status.canCastleWhiteRightSide());
    }

    @Test
    public void castlingRightSideNotBlockedByLeftRookTest() {

        CastlingBoard board = new CastlingBoard();
        Rook leftRook = (Rook) board.getPiece(7, 0);
        leftRook.setLeftRookMoved(true);

        PiecesStatus status = new PiecesStatus(board);
        assertTrue("Queen Castling have to be possible because only the left rook moved",
                status.canCastleWhiteRightSide());
    }

    @Test
    public void castlingLeftSideBlockedByLeftRookTest() {

        CastlingBoard board = new CastlingBoard();
        Rook leftRook = (Rook) board.getPiece(7, 0);
        leftRook.setLeftRookMoved(true);

        PiecesStatus status = new PiecesStatus(board);
        assertFalse("Le castling shouldn't be possible because the rook moved",
                status.canCastleWhiteLeftSide());
    }

    @Test
    public void castlingHandlerDoesNotMoveIfRightRookMovedTest() {

        CastlingBoard board = new CastlingBoard();
        Rook rightRook = (Rook) board.getPiece(7, 7);
        rightRook.setRightRookMoved(true);

        CastlingHandler handler = new CastlingHandler(board);
        handler.handleWhiteKingsideCastling(7, 4, 7, 7);

        assertTrue("Rook have to be in (7,4)", board.getPiece(7, 4).isKing());
        assertTrue("Rook have to be in (7,7)", board.getPiece(7, 7).isRook());
        assertNull("(7,5) Must be empty", board.getPiece(7, 5));
        assertNull("(7,6) Must be empty", board.getPiece(7, 6));
    }

    @Test
    public void enPassantCannotCaptureOwnPawnTest() {

        EmptyBoard board = new EmptyBoard();
        board.setPiece(3, 3, new Pawn(PiecesColor.WHITE));
        board.setPiece(3, 4, new Pawn(PiecesColor.WHITE));
        board.setEnPassantPawn(3, 4);

        Pawn whitePawn = (Pawn) board.getPiece(3, 3);
        List<Localisation> moves = whitePawn.movements(3, 3, board);

        assertFalse("No EnPassant against a pawn with the same color",
                moves.contains(new Localisation(2, 4)));
    }

    @Test
    public void enPassantNoEnemyTargetTest() {

        EmptyBoard board = new EmptyBoard();
        board.setPiece(3, 3, new Pawn(PiecesColor.WHITE));
        board.setEnPassantPawn(3, 4);

        Pawn whitePawn = (Pawn) board.getPiece(3, 3);
        List<Localisation> moves = whitePawn.movements(3, 3, board);

        assertFalse("No EnPassant because there is not ennemy pawn",
                moves.contains(new Localisation(2, 4)));
    }

    @Test
    public void enPassantValidCaptureTest() {

        EmptyBoard board = new EmptyBoard();
        board.setPiece(3, 3, new Pawn(PiecesColor.WHITE));
        board.setPiece(3, 4, new Pawn(PiecesColor.BLACK));
        board.setEnPassantPawn(3, 4);

        Pawn whitePawn = (Pawn) board.getPiece(3, 3);
        List<Localisation> moves = whitePawn.movements(3, 3, board);

        assertTrue("White pawn can escape in (2,4)",
                moves.contains(new Localisation(2, 4)));
    }

    @Test
    public void enPassantValidCaptureBlackTest() {

        EmptyBoard board = new EmptyBoard();
        board.setPiece(4, 3, new Pawn(PiecesColor.BLACK));
        board.setPiece(4, 4, new Pawn(PiecesColor.WHITE));
        board.setEnPassantPawn(4, 4);

        Pawn blackPawn = (Pawn) board.getPiece(4, 3);
        List<Localisation> moves = blackPawn.movements(4, 3, board);

        assertTrue("Black pawn can escape in (5,4)",
                moves.contains(new Localisation(5, 4)));
    }

    @Test
    public void doubleCheckIsCheckmateWhenKingCannotEscapeTest() {

        EmptyBoard board = new EmptyBoard();
        board.setPiece(7, 4, new King(PiecesColor.WHITE));
        board.setPiece(6, 3, new Pawn(PiecesColor.WHITE));
        board.setPiece(6, 4, new Pawn(PiecesColor.WHITE));
        board.setPiece(6, 5, new Pawn(PiecesColor.WHITE));
        board.setPiece(0, 4, new Rook(PiecesColor.BLACK));
        board.setPiece(7, 0, new Rook(PiecesColor.BLACK));

        GameStatus status = new GameStatusTerminal(board);
        assertTrue("Double check with no escape",
                status.isCheckmate(PiecesColor.WHITE));
    }

    @Test
    public void doubleCheckIsNotCheckmateWhenKingCanEscapeTest() {

        EmptyBoard board = new EmptyBoard();
        board.setPiece(7, 4, new King(PiecesColor.WHITE));
        board.setPiece(6, 4, new Pawn(PiecesColor.WHITE));
        board.setPiece(6, 5, new Pawn(PiecesColor.WHITE));
        board.setPiece(0, 4, new Rook(PiecesColor.BLACK));
        board.setPiece(7, 0, new Rook(PiecesColor.BLACK));

        GameStatus status = new GameStatusTerminal(board);
        assertFalse("Double check with no escape",
                status.isCheckmate(PiecesColor.WHITE));
    }

    @Test
    public void shepherdMateTest() {
        EmptyBoard board = new EmptyBoard();
        board.setPiece(0, 4, new King(PiecesColor.BLACK));
        board.setPiece(0, 3, new Queen(PiecesColor.BLACK));
        board.setPiece(1, 5, new Queen(PiecesColor.WHITE));
        board.setPiece(4, 2, new Bishop(PiecesColor.WHITE));
        board.setPiece(7, 4, new King(PiecesColor.WHITE));

        GameStatusTerminal status = new GameStatusTerminal(board);
        assertTrue("Mat du berger", status.isCheckmate(PiecesColor.BLACK));
    }
}