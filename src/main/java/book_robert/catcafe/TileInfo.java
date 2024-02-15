package book_robert.catcafe;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class TileInfo extends Label implements PropertyChangeListener {
    public Label data;
    public Tile dataLookup;
    public CafeSim sim;
    public PropertyChangeSupport pcs;
    public TileInfo(Tile observe, CafeSim sim){
        this.dataLookup = observe;
        this.sim = sim;
        this.pcs = new PropertyChangeSupport(this);
        this.dataLookup.pcs.addPropertyChangeListener(this);
        this.refactor(this.dataLookup);
    }

    public void refactor(Tile observe){
        this.data = new Label();
        if (observe.type instanceof Empty){
            this.data.setText("%s\nFloor Changed: %s\nFloor Age: %s\nTotal Cost: $%s".formatted(
                    observe.type.name,
                    this.sim.f_change,
                    this.sim.f_age,
                    observe.t_t_cost
            ));
        }
        else if(observe.type instanceof Table){
            this.data.setText("%s\nFloor Changed: %s\nFloor Age: %s\nTotal Cost: $%s\nTotal Revenue: $%s".formatted(
                    observe.type.name,
                    this.sim.f_change,
                    this.sim.f_age,
                    observe.t_t_cost,
                    this.sim.t_revenue
            ));

        }
        else if(observe.type instanceof Cat){
            this.data.setText(("%s\n" +
                    "Floor Changed: %s\n" +
                    "Floor Age: %s\n" +
                    "Total Cost: $%s\n" +
                    "Cat age: %s\n" +
                    "Weeks until adoption: %s").formatted(
                    observe.type.name,
                    this.sim.f_change,
                    this.sim.f_age,
                    observe.t_t_cost,
                    observe.type.age,
                    ((Cat) observe.type).countdown
            ));

        }
        else if(observe.type instanceof Kitten){
            this.data.setText(("%s\n" +
                    "Floor Changed: %s\n" +
                    "Floor Age: %s\n" +
                    "Total Cost: $%s\n" +
                    "Cat age: %s\n" +
                    "Weeks until adoption: %s").formatted(
                    observe.type.name,
                    this.sim.f_change,
                    this.sim.f_age,
                    observe.t_t_cost,
                    observe.type.age,
                    ((Kitten) observe.type).countdown
            ));

        }
        this.data.setTextAlignment(TextAlignment.LEFT);
        this.dataLookup.observed = true;
        this.pcs.firePropertyChange("refactored TI", null, this.data);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(!Objects.equals(evt.getPropertyName(), "being observed")) {
            this.refactor(this.dataLookup);
        }
    }
}
