package book_robert.catcafe;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TileView extends Button implements PropertyChangeListener {
    //View node that displays the tile's info
    //it is the observer in the observer pattern
    public Tile model;
    public int idx;

    public TileView(Tile observes, int index) {
        this.model = observes;
        observes.pcs.addPropertyChangeListener(this);
        this.idx = index;
        this.setText("-%s-\n-$%s".formatted(this.model.type.letter, this.model.type.weeklyCost));
        this.setTextAlignment(TextAlignment.CENTER);
        this.color();
    }
    public void color(){
        if(this.model.type instanceof Empty){
            this.setStyle("-fx-background-color: #ffdbde; -fx-border-color: #dda6b5; -fx-border-radius: 10; -fx-background-radius: 10");
        }
        else if(this.model.type instanceof Table){
            this.setStyle("-fx-background-color: #9075d8; -fx-border-color: #dda6b5; -fx-border-radius: 10; -fx-background-radius: 10");
        }
        else if(this.model.type instanceof Cat){
            this.setStyle("-fx-background-color: #eb9d9d; -fx-border-color: #ffd3da; -fx-border-radius: 10; -fx-background-radius: 10");
        }
        else if(this.model.type instanceof Kitten){
            this.setStyle("-fx-background-color: #cea2d7; -fx-border-color: #ffd3da; -fx-border-radius: 10; -fx-background-radius: 10");
        }
    }
    public void highlight(){
        TileView oldSelf = this;
        this.setStyle("-fx-border-color: #007f87;" +
                " -fx-border-width: 5;" + this.getStyle());
        this.model.observed = true;
        this.model.pcs.firePropertyChange("highlight set", oldSelf, this);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) { //GRADING: OBSERVE

        if (this.model.type instanceof Empty) {
            this.setText("-%s-\n-$%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost));
            this.setTextAlignment(TextAlignment.CENTER);
        } else if (this.model.type instanceof Table) {
            this.setText("-%s-\n-$%s\n$%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost,
                    ((Table) this.model.type).revenue));
            this.setTextAlignment(TextAlignment.CENTER);

        } else if (this.model.type instanceof Cat) {
            this.setText("-%s-\n-$%s\n%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost,
                    ((Cat) this.model.type).countdown));
            this.setTextAlignment(TextAlignment.CENTER);
        } else if (this.model.type instanceof Kitten) {
            this.setText("-%s-\n-$%s\n%s".formatted(this.model.type.letter,
                    this.model.type.weeklyCost,
                    ((Kitten) this.model.type).countdown));
            this.setTextAlignment(TextAlignment.CENTER);
        }
        color();
        if(this.model.observed){
            highlight();
        }
    }



}
