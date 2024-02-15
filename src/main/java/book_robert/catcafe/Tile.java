package book_robert.catcafe;

import java.beans.PropertyChangeSupport;

public class Tile{
    //Constant holder for a floor area (potentially more)
    //the subject of the holder
    public FloorArea type;
    public PropertyChangeSupport pcs;
    public int t_t_cost;
    public boolean observed;

    public Tile(){
        new Tile("empty");
    }

    public Tile(String newType){
        this.pcs = new PropertyChangeSupport(this);
        this.observed = false;
        this.t_t_cost = 0;
        switch (newType) {
            case "cat" -> this.type = new Cat();
            case "kitten" -> this.type = new Kitten();
            case "table" -> this.type = new Table();
            default -> this.type = new Empty();
        }
    }
    public void change(String newType){
        FloorArea oldType = this.type;
        switch (newType) {
            case "cat" ->
                this.type = new Cat();

            case "kitten" ->
                this.type = new Kitten();

            case "table" ->
                this.type = new Table();

            default ->
                this.type = new Empty();
        }
        this.pcs.firePropertyChange("type", oldType, this.type); //GRADING: TRIGGER
    }

    void nextWeek(){
        if(this.type instanceof Cat && ((Cat) this.type).countdown == 0){
            this.change("cat");
        }
        else if(this.type instanceof Kitten && ((Kitten) this.type).countdown == 0){
            this.change("kitten");
        }
    }
}
