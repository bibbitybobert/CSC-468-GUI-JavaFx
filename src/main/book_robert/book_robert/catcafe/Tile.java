package book_robert.catcafe;

import java.beans.PropertyChangeSupport;

public class Tile{
    //Constant holder for a floor area (potentially more)
    //the subject of the holder
    public FloorArea type;

    public Tile(){
        this.type = new Empty();
    }
    void nextMonth(){}
}
