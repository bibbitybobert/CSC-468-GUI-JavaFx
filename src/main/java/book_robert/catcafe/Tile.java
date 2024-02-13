package book_robert.catcafe;

import java.beans.PropertyChangeSupport;

public class Tile{
    //Constant holder for a floor area (potentially more)
    //the subject of the holder
    public FloorArea type;
    public PropertyChangeSupport pcs;

    public Tile(){
        new Tile("empty");
    }

    public Tile(String newType){
        pcs = new PropertyChangeSupport(this);
        switch (newType) {
            case "cat" -> this.type = new Cat();
            case "kitten" -> this.type = new Kitten();
            case "table" -> this.type = new Table();
            default -> this.type = new Empty();
        }
    }
    public void change(String newType){
        switch (newType) {
            case "cat" -> {
                FloorArea oldType = this.type;
                this.type = new Cat();
                this.pcs.firePropertyChange("type", oldType, this.type);
            }
            case "kitten" -> {
                FloorArea oldType = this.type;
                this.type = new Kitten();
                this.pcs.firePropertyChange("type", oldType, this.type);
            }
            case "table" -> {
                FloorArea oldType = this.type;
                this.type = new Table();
                this.pcs.firePropertyChange("type", oldType, this.type);
            }
            default -> {
                FloorArea oldType = this.type;
                this.type = new Empty();
                this.pcs.firePropertyChange("type", oldType, this.type);
            }
        }
    }
    void nextMonth(){}
}
