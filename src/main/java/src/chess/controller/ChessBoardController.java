package src.chess.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import src.chess.factory.Board;
import src.chess.factory.StandartBoard;
import src.chess.GameManagement.GameManagementFx;
import src.chess.model.pieces.Localisation;
import src.chess.model.pieces.Pieces;
import src.chess.model.pieces.PiecesColor;
import src.chess.model.players.HumanPlayer;
import src.chess.model.players.Player;
import src.chess.view.ImagesPieces;

import java.util.List;
import java.util.Map;

public class ChessBoardController implements Observer {

    @FXML
    private GridPane chessGrid;

    private GameManagementFx gameFX;
    private Board board;

    private TilePane[][] cells ;

    private int fromRow = -1;
    private int fromCol = -1;
    private int toRow = -1;
    private int toCol = -1;

    private Player whitePlayer;
    private Player blackPlayer;

    @FXML
    public void initialize() {

        board = new StandartBoard();
        whitePlayer = new HumanPlayer(board, PiecesColor.WHITE, "White");
        blackPlayer = new HumanPlayer(board, PiecesColor.BLACK, "Black");
        gameFX = new GameManagementFx(board, whitePlayer, blackPlayer);
        this.cells = new TilePane[8][8];

        initializeBoard();
        board.addObserver(this);
    }

    public void initializeBoard() {

        chessGrid.getChildren().clear();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                TilePane cell = new TilePane();
                cell.setPrefSize(100, 100);
                Color baseColor = ((row + col) % 2 == 0) ? Color.BURLYWOOD : Color.SADDLEBROWN;
                cell.setBackground(new Background(new BackgroundFill(baseColor, null, null)));

                final int r = row;
                final int c = col;
                cell.setOnMouseClicked(event -> handleLeftClick(r, c));

                cells[row][col] = cell;
                chessGrid.add(cell, col, row);
            }
        }
        addPieces();
    }

    public void addPieces() {
        Map<Localisation, Pieces> allPieces = board.getPiecesMap();

        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            ImageView imageView = ImagesPieces.getImage(piece);
            cells[entry.getKey().getX()][entry.getKey().getY()].getChildren().add(imageView);
        }
    }
    public void removePiecesAndColor() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                cells[row][col].getChildren().clear();
                Color baseColor = ((row + col) % 2 == 0) ? Color.BURLYWOOD : Color.SADDLEBROWN;
                cells[row][col].setBackground(new Background(new BackgroundFill(baseColor, null, null)));

            }
        }
    }
    public void handleLeftClick(int row, int col) {

        Pieces clickedPiece = board.getPiece(row, col);

        if (fromRow == -1) {
            if (clickedPiece != null) {
                fromRow = row;
                fromCol = col;
                highlightCell(row, col);
                pieceMovement(row, col);
            }
            return;
        }

        if (row == fromRow && col == fromCol) {
            applyBoardDefaultColor(fromRow, fromCol);
            resetFromTo();
            return;
        }

        if (clickedPiece != null && clickedPiece.getColor() == board.getPiece(fromRow, fromCol).getColor()) {
            applyBoardDefaultColor(fromRow, fromCol);
            resetFromTo();
            fromRow = row;
            fromCol = col;
            highlightCell(row, col);
            pieceMovement(row, col);
            return;
        }

        if (toRow == -1) {
            toRow = row;
            toCol = col;

            String from = toChessNotation(fromRow, fromCol);
            String to = toChessNotation(toRow, toCol);

            gameFX.playMove(from, to);

            resetFromTo();
            board.notifyObservers();
        }
    }

    private String toChessNotation(int row, int col) {
        char file = (char) ('a' + col);
        char rank = (char) ('0' + (8 - row));
        return "" + file + rank;
    }

    private void resetFromTo() {
        fromRow = -1;
        fromCol = -1;
        toRow = -1;
        toCol = -1;
    }

    private void highlightCell(int row, int col) {
        TilePane cell = cells[row][col];
        cell.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
    }

    private void applyBoardDefaultColor(int row, int col) {
        Pieces piece = board.getPiece(row, col);
        if (piece == null) return;

        List<Localisation> cleaningCells = piece.movements(row, col, board);
        cleaningCells.add(new Localisation(row, col));

        for (Localisation loc : cleaningCells) {
            TilePane cell = cells[loc.getX()][loc.getY()];
            Color baseColor = ((loc.getX() + loc.getY()) % 2 == 0) ? Color.BURLYWOOD : Color.SADDLEBROWN;
            cell.setBackground(new Background(new BackgroundFill(baseColor, null, null)));
        }
    }

    private void pieceMovement(int x, int y) {
        Pieces piece = board.getPiece(x, y);
        if (piece == null) return;

        List<Localisation> moves = piece.movements(x, y, board);
        for (Localisation loc : moves) {
            TilePane cell = cells[loc.getX()][loc.getY()];
            cell.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
    }

    @Override
    public void react() {
        removePiecesAndColor();
        applyBoardDefaultColor(fromRow, fromCol);
        addPieces();
    }
}
