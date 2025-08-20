package src.chess.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import src.chess.factory.Board;
import src.chess.factory.StandartBoard;
import src.chess.model.pieces.Pieces;

public class ChessBoardController implements Observer {

    @FXML
    private GridPane chessGrid;

    private Board board;

    @FXML
    public void initialize() {

        board = new StandartBoard();
        board.addObserver(this);
        board.notifyObservers();

    }

    public void react(){

        chessGrid.getChildren().clear();
        Pieces[][] piecesArray = board.getBoard();
        int c = 0 ;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                StackPane cell = new StackPane();
                cell.setPrefSize(200,200);

                Color cellColor = (c % 2 == 0) ? Color.BURLYWOOD : Color.SADDLEBROWN;
                cell.setBackground(new Background(new BackgroundFill(cellColor, null, null)));

                Pieces pieces = piecesArray[row][col];
                if ( pieces != null){
                    Label label = new Label(pieces.getSymbol());
                    label.setFont(new Font(30));
                    cell.getChildren().add(label);
                }
                chessGrid.add(cell, col, row);
                c ++ ;
            }
            c ++ ;
        }
    }
}