package src.chess.model.pieces;

public class Localisation {
    public int x;
    public int y;

    public Localisation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter
     * @return return X, the row pos
     */
    public int getX() {
        return x;
    }

    /**
     * Getter
     * @return return Y, the col pos
     */
    public int getY() {
        return y;
    }

}
