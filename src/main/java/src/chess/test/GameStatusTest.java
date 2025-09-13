package src.chess.test;

import org.junit.Test;
import src.chess.factory.Board;
import src.chess.factory.EmptyBoard;
import src.chess.factory.PawnBoard;
import src.chess.factory.StandartBoard;
import src.chess.gamestatus.GameStatus;
import src.chess.model.pieces.*;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;
import src.chess.gamestatus.GameStatusTerminal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameStatusTest {

    @Test
    public void promotionAsBlackRookTest() {

        String testInput = "1";
        InputStream originalIn = System.in;

        try {

            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            PawnBoard board = new PawnBoard();
            Player BlackPawnPlayer = new HumanPlayer(board, PiecesColor.BLACK, "Black");

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
            Player BlackPawnPlayer = new HumanPlayer(board, PiecesColor.BLACK, "Black");

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
            Player BlackPawnPlayer = new HumanPlayer(board, PiecesColor.BLACK, "Black");

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
            Player BlackPawnPlayer = new HumanPlayer(board, PiecesColor.BLACK, "Black");

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
            Player WhitePawnPlayer = new HumanPlayer(board, PiecesColor.WHITE, "White");

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
            Player WhitePawnPlayer = new HumanPlayer(board, PiecesColor.WHITE, "White");

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
            Player WhitePawnPlayer = new HumanPlayer(board, PiecesColor.WHITE, "White");

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
            Player WhitePawnPlayer = new HumanPlayer(board, PiecesColor.WHITE, "White");

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
        assert (checkmate1) : " The king is surrounded by pawn but can escape ";
        board.reset();

        board.setPiece(0, 2, new King(PiecesColor.BLACK));
        board.setPiece(0, 1, new Queen(PiecesColor.WHITE));
        board.setPiece(0, 3, new Queen(PiecesColor.WHITE));
        board.setPiece(1, 3, new Queen(PiecesColor.WHITE));
        board.setPiece(1, 1, new Queen(PiecesColor.WHITE));
        board.setPiece(1, 2, new Queen(PiecesColor.WHITE));
        boolean checkmate2 = gameStatusTerminal.isCheckmate(PiecesColor.BLACK);
        assert (checkmate2) : " The king is surrounded by Queen and cannot move";

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
        assertTrue("Have to be true", gameStatusTerminal.isDraw());

        gameStatusTerminal.setDrawCounter(60);
        assertFalse("Have to be false", gameStatusTerminal.isDraw());

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
        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
        List<Localisation> testList;

        board.setPiece(2, 2, new King(PiecesColor.BLACK));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.BLACK);
        assert (testList.isEmpty()) : " 0 pieces are threatening the king ";

        board.setPiece(2, 1, new Queen(PiecesColor.WHITE));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.BLACK);
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";

        board.setPiece(2, 3, new Queen(PiecesColor.WHITE));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.BLACK);
        assert (testList.size() == 2) : " 2 pieces are threatening the king ";

        board.setPiece(3, 2, new Queen(PiecesColor.WHITE));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.BLACK);
        assert (testList.size() == 3) : " 3 pieces are threatening the king ";

        board.setPiece(1, 2, new Queen(PiecesColor.WHITE));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.BLACK);
        assert (testList.size() == 4) : " 4 pieces are threatening the king ";

    }

    @Test
    public void PiecesThreateningKingWhiteTest() {

        EmptyBoard board = new EmptyBoard();
        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
        List<Localisation> testList;

        board.setPiece(2, 2, new King(PiecesColor.WHITE));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.WHITE);
        assert (testList.isEmpty()) : " 0 pieces are threatening the king ";

        board.setPiece(2, 1, new Queen(PiecesColor.BLACK));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.WHITE);
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";

        board.setPiece(2, 3, new Queen(PiecesColor.BLACK));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.WHITE);
        assert (testList.size() == 2) : " 2 pieces are threatening the king ";

        board.setPiece(3, 2, new Queen(PiecesColor.BLACK));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.WHITE);
        assert (testList.size() == 3) : " 3 pieces are threatening the king ";

        board.setPiece(1, 2, new Queen(PiecesColor.BLACK));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.WHITE);
        assert (testList.size() == 4) : " 4 pieces are threatening the king ";

    }

    @Test
    public void PiecesThreateningKingBlackTest2() {

        EmptyBoard board = new EmptyBoard();
        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
        List<Localisation> testList;

        board.setPiece(2, 2, new King(PiecesColor.BLACK));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.BLACK);
        assert (testList.isEmpty()) : " 0 pieces are threatening the king ";

        board.setPiece(2, 6, new Queen(PiecesColor.WHITE));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.BLACK);
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";

        board.setPiece(3,5,new Pawn(PiecesColor.WHITE));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.BLACK);
        System.out.println(testList.size());
        assert (testList.size() == 1) : " The pawn isn't threatening the king ";

    }

    @Test
    public void PiecesThreateningKingWhiteTest2() {

        EmptyBoard board = new EmptyBoard();
        GameStatusTerminal gameStatusTerminal = new GameStatusTerminal(board);
        List<Localisation> testList;

        board.setPiece(2, 2, new King(PiecesColor.WHITE));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.WHITE);
        assert (testList.isEmpty()) : " 0 pieces are threatening the king ";

        board.setPiece(2, 1, new Queen(PiecesColor.BLACK));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.WHITE);
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";

        board.setPiece(2,5,new Pawn(PiecesColor.BLACK));
        testList = gameStatusTerminal.PiecesThreateningKing(PiecesColor.WHITE);
        assert (testList.size() == 1) : " The pawn isn't threatening the king ";

    }

    @Test
    public void PiecesThreateningKingTest(){

        Board board = new EmptyBoard();
        List<Localisation>testList;
        GameStatus gameStatus = new GameStatusTerminal(board);
        board.setPiece(0,4, new King(PiecesColor.BLACK));

        board.setPiece(0,7, new Queen(PiecesColor.WHITE));
        testList = (gameStatus.PiecesThreateningKing(PiecesColor.BLACK));
        assert (testList.size() == 1) : " 1 pieces are threatening the king ";
        assert (testList.contains(new Localisation(0,7)));
        testList.clear();

        board.setPiece(0,3,new Rook(PiecesColor.WHITE));
        testList = (gameStatus.PiecesThreateningKing(PiecesColor.BLACK));
        assert (testList.size() == 2) : " 2 pieces are threatening the king ";
        assert (testList.contains(new Localisation(0,3)));
        testList.clear();

        board.setPiece(1,3,new Pawn(PiecesColor.WHITE));
        testList = (gameStatus.PiecesThreateningKing(PiecesColor.BLACK));
        assert (testList.size() == 3) : " 3 pieces are threatening the king ";
        assert (testList.contains(new Localisation(1,3)));
        testList.clear();

        board.setPiece(1,5,new Bishop(PiecesColor.WHITE));
        testList = (gameStatus.PiecesThreateningKing(PiecesColor.BLACK));
        board.displayWithIndices();
        assert (testList.size() == 4) : " 4 pieces are threatening the king ";
        assert (testList.contains(new Localisation(1,5)));
        testList.clear();

    }

    @Test
    public void CanParryTest(){

        Board board = new EmptyBoard();
        board.setPiece(0,4, new King(PiecesColor.BLACK));
        board.setPiece(0,3, new Queen(PiecesColor.BLACK));
        board.setPiece(1,4, new Queen(PiecesColor.WHITE));

        GameStatus gameStatus = new GameStatusTerminal(board);
        List<Localisation> threateningPieces = gameStatus.PiecesThreateningKing(PiecesColor.BLACK);
        assertTrue ( gameStatus.canParryKingThreat(PiecesColor.BLACK, threateningPieces));

        board.setPiece(0,5, new Rook(PiecesColor.WHITE));
        threateningPieces.clear();
        threateningPieces = gameStatus.PiecesThreateningKing(PiecesColor.BLACK);

        List<Localisation> finalThreateningPieces = threateningPieces;
        assertThrows(AssertionError.class, () -> {
            gameStatus.canParryKingThreat(PiecesColor.BLACK, finalThreateningPieces);
        });

    }
}