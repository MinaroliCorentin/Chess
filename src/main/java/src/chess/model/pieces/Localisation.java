package src.chess.model.pieces;

import java.util.Objects;

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

    /**
     * Checks if this localisation is equal to another object.
     * @param o Object to compare with this localisation.
     * @return true if the object is also a localisation and has the same x and y, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Localisation)) {
            return false;
        }
        Localisation that = (Localisation) o;
        return x == that.x && y == that.y;
    }


    /**
     * Generates a hash code for this localisation.
     * @return the hash code based on the x and y coordinates.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}