package src.chess.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import src.chess.factory.Board;
import src.chess.factory.StandartBoard;
import src.chess.gamemanagement.GameManagement;
import src.chess.gamemanagement.GameManagementFx;
import src.chess.gameplay.Gameplay;
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
    private Label turnLabel;

    @FXML
    private GridPane chessGrid;

    private GameManagementFx gameFX;
    private Board board;

    private StackPane[][] cells ;

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
        this.cells = new StackPane[8][8];

        initializeBoard();
        board.addObserver(this);
    }




    /**
     * Initialize the board, add pieces, color for the board, and make it tactile
     */
    public void initializeBoard() {

        chessGrid.getChildren().clear();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                StackPane cell = new StackPane();

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
        addChessNotation();
        addPieces();
        updatePlayerTurn();
    }

    /**
     * add indices at the bottom right of each cells to help the user
     */
    public void addChessNotation() {

        for ( int i = 0 ; i < 8 ; i ++ ){
            for ( int j = 0 ; j < 8 ; j++){

                Label overlay = new Label(toChessNotation(i,j));
                overlay.setStyle("-fx-text-fill: blue; -fx-font-size: 12px;");
                overlay.setMouseTransparent(true);

                StackPane.setAlignment(overlay, Pos.BOTTOM_RIGHT);
                StackPane.setMargin(overlay, new Insets(2));

                cells[i][j].getChildren().add(overlay);

            }
        }
    }

    /**
     * Add all the pieces to the board
     */
    public void addPieces() {
        Map<Localisation, Pieces> allPieces = board.getPiecesMap();

        for (Map.Entry<Localisation, Pieces> entry : allPieces.entrySet()) {
            Pieces piece = entry.getValue();
            ImageView imageView = ImagesPieces.getImage(piece);
            cells[entry.getKey().getX()][entry.getKey().getY()].getChildren().add(imageView);
        }
    }

    /**
     * Revome the Piece and reset the color of the board
     */
    public void removePiecesAndColor() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                cells[row][col].getChildren().clear();
                Color baseColor = ((row + col) % 2 == 0) ? Color.BURLYWOOD : Color.SADDLEBROWN;
                cells[row][col].setBackground(new Background(new BackgroundFill(baseColor, null, null)));

            }
        }
    }

    /**
     * Handler the leftClick
     * Highlight a pieces in yellow when selected
     * Show all the possible movements in red
     * if the user click on the red box, move the piece
     * @param row row of the piece
     * @param col col of the piece
     */
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

    /**
     * Transform the int board to a standard chess board
     * @param row
     * @param col
     * @return
     */
    private String toChessNotation(int row, int col) {
        char file = (char) ('a' + col);
        char rank = (char) ('0' + (8 - row));
        return "" + file + rank;
    }

    /**
     * Reset from and to variables
     */
    private void resetFromTo() {
        fromRow = -1;
        fromCol = -1;
        toRow = -1;
        toCol = -1;
    }

    /**
     * Highlight the selected piece in yellow
     * @param row row of the piece
     * @param col col of the piece
     */
    private void highlightCell(int row, int col) {
        StackPane cell = cells[row][col];
        cell.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
    }

    /**
     * Reset the default color of the board based on a Piece pos
     * @param row Piece row
     * @param col Piece col
     */
    private void applyBoardDefaultColor(int row, int col) {
        Pieces piece = board.getPiece(row, col);
        if (piece == null) return;

        List<Localisation> cleaningCells = piece.movements(row, col, board);
        cleaningCells.add(new Localisation(row, col));

        for (Localisation loc : cleaningCells) {
            StackPane cell = cells[loc.getX()][loc.getY()];
            Color baseColor = ((loc.getX() + loc.getY()) % 2 == 0) ? Color.BURLYWOOD : Color.SADDLEBROWN;
            cell.setBackground(new Background(new BackgroundFill(baseColor, null, null)));
        }
    }

    /**
     * Highlight the possible movement in the grid
     * @param x Piece x position
     * @param y Piece y position
     */
    private void pieceMovement(int x, int y) {
        Pieces piece = board.getPiece(x, y);
        if (piece == null) return;

        List<Localisation> moves = piece.movements(x, y, board);
        for (Localisation loc : moves) {
            StackPane cell = cells[loc.getX()][loc.getY()];
            cell.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
    }

    /**
     * Update the label that indicates who is playing
     */
    private void updatePlayerTurn(){

        turnLabel.setText(gameFX.getPlayerNameBasedOnRound());

    }

    /**
     * Remove the pieces, apply the default color then add the pieces
     */
    @Override
    public void react() {
        removePiecesAndColor();
        applyBoardDefaultColor(fromRow, fromCol);
        addChessNotation();
        addPieces();
        updatePlayerTurn();
    }
}
