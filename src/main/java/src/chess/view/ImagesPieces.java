package src.chess.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.chess.model.pieces.Pieces;
import src.chess.model.pieces.PiecesColor;

import java.util.Objects;

public class ImagesPieces {

    private static final String PATH = "/picture/";

    public static ImageView getImage(Pieces piece) {

        if (piece == null) return null;

        String color = piece.getColor().toString().toLowerCase();
        String name = piece.getClass().getSimpleName().toLowerCase();
        String fileName = color + "_" + name + ".png";

        Image img = new Image(Objects.requireNonNull(ImagesPieces.class.getResourceAsStream(PATH + fileName)));
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        return imageView;
    }
}
