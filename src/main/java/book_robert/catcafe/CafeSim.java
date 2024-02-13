package book_robert.catcafe;

import java.beans.PropertyChangeListener;
import java.util.Vector;

public class CafeSim {
    //The manager class for the cafe
    int timeSinceReset;
    int funds;
    int week;
    int t_filled;
    int adopted;
    int f_change;
    int f_age;
    int t_cost;
    int t_revenue;
    Vector<Tile> tiles;
    int storeSize;
    public CafeSim(){
        this.timeSinceReset = 0;
        this.funds = 0;
        this.week = 0;
        this.t_filled = 0;
        this.adopted = 0;
        this.f_change = 0;
        this.f_age = 0;
        this.t_cost = 0;
        this.t_revenue = 0;
        this.storeSize = 3;
        this.tiles = new Vector<Tile>();
        this.resizeTiles(this.storeSize, "empty");
    }

    public void resizeTiles(int size, String type){
        if(!this.tiles.isEmpty()){this.tiles.clear();}
        this.storeSize = size;
        for(int i =0; i < (size * size); i++){
            this.tiles.add(new Tile(type));
        }
    }

    public void setTile(int idx, String type){
        this.tiles.get(idx).change(type);
    }
    void nextWeek(){}
}
