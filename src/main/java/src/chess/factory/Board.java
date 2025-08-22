package src.chess.factory;

import src.chess.controller.ObserverSubject;
import src.chess.model.pieces.*;
import src.chess.model.pieces.PiecesColor;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Board extends ObserverSubject {

    private final Map<Localisation, Pieces> board = new ConcurrentHashMap<>();
    private Point enPassantPawn ;

    public Board() {
        this.BoardInitialize();
        this.enPassantPawn = new Point(-2,-2);
    }

    public int getEnPassantPawnX() {
        return enPassantPawn.getLocation().x;
    }

    public int getEnPassantPawnY() {
        return enPassantPawn.getLocation().y;
    }

    public void setEnPassantPawn(int X, int Y) {
        this.enPassantPawn.setLocation(X,Y);
    }

    public void setEnPassantPawnDefaultValue(){
        this.enPassantPawn.setLocation(-1,-1);
    }

    /**
     * Create the board. Override by the several class that build the board
     */
    public abstract void BoardInitialize();

    /**
     * Clear the board and call initialize
     */
    public void reset() {
        board.clear();
        this.BoardInitialize();
    }

    /**
     * Clear the board then fill it with the @param board
     * @param newBoard the new board
     */
    public void setBoard(Pieces[][] newBoard) {
        board.clear();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Pieces p = newBoard[x][y];
                if (p != null) {
                    board.put(new Localisation(x, y), p);
                }
            }
        }
    }

    /**
     * @return a array of Pieces[][] that contain every Pieces
     */
    public Pieces[][] getBoard() {
        Pieces[][] array = new Pieces[8][8];
        for (Map.Entry<Localisation, Pieces> entry : board.entrySet()) {
            Localisation loc = entry.getKey();
            array[loc.getX()][loc.getY()] = entry.getValue();
        }
        return array;
    }

    /**
     * @param x row
     * @param y col
     * @return the Pieces at x,y, position
     */
    public Pieces getPiece(int x, int y) {
        return board.get(new Localisation(x, y));
    }

    /**
     * Set a new pieces if the pieces isn't null
     * @param x row
     * @param y col
     * @param piece the new pieces
     */
    public void setPiece(int x, int y, Pieces piece) {
        Localisation loc = new Localisation(x, y);
        if (piece != null) {
            board.put(loc, piece);
        } else {
            board.remove(loc);
        }
    }

    /**
     *
     * @param x row
     * @param y col
     * @return true if the pos dont contain a piece and is in bound
     */
    public boolean isEmpty(int x, int y) {
        return isBound(x, y) && !board.containsKey(new Localisation(x, y));
    }

    /**
     *
     * @param x row
     * @param y col
     * @return true if x and y are in bound ( >= 0 && < 8 )
     */
    public boolean isBound(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    /**
     *
     * @param x row
     * @param y col
     * @param piecesColor enemy piecesColor
     * @return true if the piece at x,y is null or != piecesColor
     */
    public boolean hasOpponentPieceOrNull(int x, int y, PiecesColor piecesColor) {
        Pieces p = board.get(new Localisation(x, y));
        return isBound(x, y) && p != null && p.getColor() != piecesColor;
    }

    /**
     * @return a map with every Pieces on the board
     */
    public Map<Localisation, Pieces> getPiecesMap() {
        Map<Localisation, Pieces> piecesMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieces p = this.getPiece(i, j);
                if (p != null) {
                    piecesMap.put(new Localisation(i, j), p);
                }
            }
        }
        return piecesMap;
    }


    /**
     * Used to display the board if the standard coordonates
     */
    public void display() {
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + "    ");
            for (int j = 0; j < 8; j++) {
                Pieces piece = getPiece(i,j);
                System.out.print(piece != null ? piece.getSymbol() + " " : ". ");
            }
            System.out.println();
        }
        System.out.println("     A B C D E F G H\n");
    }

    /**
     * Used to display the board with the IT indices
     */
    public void displayWithIndices() {

        for (int row = 0; row < 8; row++) {
            System.out.print(row + " | ");
            for (int col = 0; col < 8; col++) {
                Pieces piece = getPiece(row, col);
                System.out.print(piece != null ? piece.getSymbol() + " " : ". ");
            }
            System.out.println();
        }
        System.out.print("    ");
        for (int col = 0; col < 8; col++) {
            System.out.print(col + " ");
        }
        System.out.println("\n");
    }


}
