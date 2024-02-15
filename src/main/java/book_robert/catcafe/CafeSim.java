package book_robert.catcafe;

import java.beans.PropertyChangeSupport;
import java.util.Vector;

public class CafeSim{
    //The manager class for the cafe
    public int timeSinceReset;
    public int funds;
    public int week;
    public int t_filled;
    public int adopted;
    public int f_change;
    public int f_age;
    public int t_cost;
    public int t_revenue;
    public Vector<Tile> tiles;
    public int storeSize;
    public PropertyChangeSupport pcs;
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
        this.tiles = new Vector<>();
        this.pcs = new PropertyChangeSupport(this);
        this.resizeTiles(this.storeSize);
    }

    public void resizeTiles(int size){
        if(!this.tiles.isEmpty()){this.tiles.clear();}
        this.storeSize = size;
        this.f_age = 0;
        this.timeSinceReset = 0;
        for(int i =0; i < (size * size); i++){
            this.tiles.add(new Tile("empty"));
            if(this.week != 0){
                this.funds -= this.tiles.get(i).type.totalCost;}
        }
        for(Tile t: tiles){
            t.t_t_cost = t.type.totalCost;
        }
    }

    public void setTile(int idx, String type){
        Tile oldTile = this.tiles.get(idx);
        if(this.tiles.get(idx).type instanceof Empty && !type.equals("empty")){
            this.t_filled++;

        }
        else if(!(this.tiles.get(idx).type instanceof Empty) && type.equals("empty")){
            this.t_filled--;
        }
        this.tiles.get(idx).change(type);
        this.tiles.get(idx).t_t_cost += this.tiles.get(idx).type.totalCost;
        this.funds -= this.tiles.get(idx).type.totalCost;
        this.pcs.firePropertyChange("setTile", oldTile, this.tiles.get(idx));
    }
    void nextWeek(){
        int oldWeek = this.week;
        this.f_age++;
        this.week++;
        for(Tile t : this.tiles){
            t.t_t_cost += t.type.weeklyCost;
            this.funds -= t.type.weeklyCost;
            if(t.type instanceof Table){
                this.t_revenue += ((Table) t.type).revenue;
                this.funds += ((Table) t.type).revenue;
            }
            if(t.type instanceof Cat || t.type instanceof Kitten){
                boolean adopted = false;
                t.type.age++;
                if(t.type instanceof Cat){
                    ((Cat) t.type).countdown--;
                    if(((Cat) t.type).countdown == 0){
                        adopted = true;
                    }
                }
                if(t.type instanceof Kitten) {
                    ((Kitten) t.type).countdown--;
                    if(((Kitten) t.type).countdown == 0){
                        adopted = true;
                    }
                }
                if(adopted){
                    this.adopted++;
                }

            }
            t.nextWeek();
        }
        this.pcs.firePropertyChange("weekChange", oldWeek, this.week);
        for(Tile t : this.tiles){
            t.pcs.firePropertyChange("week update", oldWeek, this.week);
        }
    }

}
