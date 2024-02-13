package book_robert.catcafe;

import javafx.beans.value.ObservableValue;
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
    public int idx;

    public TileView(Tile observes, int index) {
        this.model = observes;
        observes.pcs.addPropertyChangeListener(this);
        this.idx = index;
        this.setText("-%s-\n-$%s".formatted(this.model.type.letter, this.model.type.weeklyCost));
        this.setTextAlignment(TextAlignment.CENTER);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) { //GRADING: OBSERVE
        if(model.type instanceof Empty) {
            this.setText("-%s-\n-$%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost));
            this.setTextAlignment(TextAlignment.CENTER);
        }
        else if(model.type instanceof Table){
            this.setText("-%s-\n-$%s\n$%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost,
                    ((Table)this.model.type).revenue));
            this.setTextAlignment(TextAlignment.CENTER);

        }
        else if(model.type instanceof Cat){
            this.setText("-%s-\n-$%s\n%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost,
                    ((Cat)this.model.type).countdown));
            this.setTextAlignment(TextAlignment.CENTER);
        }
        else if(model.type instanceof Kitten){
            this.setText("-%s-\n-$%s\n%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost,
                    ((Kitten)this.model.type).countdown));
            this.setTextAlignment(TextAlignment.CENTER);
        }
    }



}
