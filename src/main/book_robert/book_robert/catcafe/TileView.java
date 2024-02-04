package book_robert.catcafe;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TileView extends Button {
    //View node that displays the tile's info
    //it is the obsersver in the observer pattern
    private Tile model;

    public TileView() {
        this.model = new Tile();
        this.setText("-%s-\n-$%s".formatted(this.model.letter, this.model.cost));
    }


}
