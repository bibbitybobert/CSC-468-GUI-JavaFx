package book_robert.catcafe;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

import java.awt.desktop.SystemEventListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TileInfo extends Label implements PropertyChangeListener {
    Label data;
    FloorArea dataLookup;
    CafeSim sim;
    public TileInfo(Tile observe, CafeSim sim){
        System.out.println("TileInfo type: " + observe.type);
        this.dataLookup = observe.type;
        this.sim = sim;
        this.refactor(this.dataLookup);
    }

    public void refactor(FloorArea tileType){
        System.out.println("refactoring");
        data = new Label();
        if (tileType.getClass() == Empty.class){
            data.setText("%s\nFloor Changed: %s\nFloor Age: %s\nTotal Cost: $%s".formatted(
                    tileType.name,
                    this.sim.f_change,
                    this.sim.f_age,
                    this.sim.t_cost
            ));
        }
        else if(tileType.getClass() == Table.class){
            data.setText("%s\nFloor Changed: %s\nFloor Age: %s\nTotal Cost: $%s\nTotal Revenue: $%s".formatted(
                    tileType.name,
                    this.sim.f_change,
                    this.sim.f_age,
                    this.sim.t_cost,
                    this.sim.t_revenue
            ));

        }
        else if(tileType.getClass() == Cat.class){
            data.setText(("%s\n" +
                    "Floor Changed: %s\n" +
                    "Floor Age: %s\n" +
                    "Total Cost: $%s\n" +
                    "Cat age: %s\n" +
                    "Weeks until adoption: %s").formatted(
                    tileType.name,
                    this.sim.f_change,
                    this.sim.f_age,
                    this.sim.t_cost,
                    tileType.age,
                    ((Cat) tileType).countdown
            ));

        }
        else if(tileType.getClass() == Kitten.class){
            data.setText(("%s\n" +
                    "Floor Changed: %s\n" +
                    "Floor Age: %s\n" +
                    "Total Cost: $%s\n" +
                    "Cat age: %s\n" +
                    "Weeks until adoption: %s").formatted(
                    tileType.name,
                    this.sim.f_change,
                    this.sim.f_age,
                    this.sim.t_cost,
                    tileType.age,
                    ((Kitten) tileType).countdown
            ));

        }
        data.setTextAlignment(TextAlignment.LEFT);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("TileInfo PC");
        this.refactor(dataLookup);
    }
}
