package book_robert.catcafe;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import org.w3c.dom.events.Event;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TileView extends Button implements PropertyChangeListener {
    //View node that displays the tile's info
    //it is the obsersver in the observer pattern
    public Tile model;

    public TileView() {
        this.model = new Tile();
        this.setText("-%s-\n-$%s".formatted(this.model.type.letter, this.model.type.weeklyCost));
        this.setTextAlignment(TextAlignment.CENTER);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) { //GRADING: OBSERVE
        if(model.type.getClass() == Empty.class) {
            this.setText("-%s-\n-$%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost));
            this.setTextAlignment(TextAlignment.CENTER);
        }
        else if(model.type.getClass() == Table.class){
            this.setText("-%s-\n-$%s\n$%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost,
                    ((Table)this.model.type).revenue));
            this.setTextAlignment(TextAlignment.CENTER);

        }
        else if(model.type.getClass() == Cat.class){
            this.setText("-%s-\n-$%s\n%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost,
                    ((Cat)this.model.type).countdown));
            this.setTextAlignment(TextAlignment.CENTER);
        }
        else if(model.type.getClass() == Kitten.class){
            this.setText("-%s-\n-$%s\n%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost,
                    ((Kitten)this.model.type).countdown));
            this.setTextAlignment(TextAlignment.CENTER);
        }
    }



}
