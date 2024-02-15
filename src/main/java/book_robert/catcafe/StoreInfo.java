package book_robert.catcafe;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StoreInfo extends Label implements PropertyChangeListener {
    public CafeSim simSubj;
    public Label data;
    public PropertyChangeSupport pcs;

    public StoreInfo(CafeSim sim){
        this.data = new Label();
        this.simSubj = sim;
        this.pcs = new PropertyChangeSupport(this);
        this.simSubj.pcs.addPropertyChangeListener(this);
        this.setData();
    }

    public void setData(){
        this.data.setText("Week: %s\nFilled: %s\nFunds: $%s\nAdopted: %s".formatted(
                this.simSubj.week,
                this.simSubj.t_filled,
                this.simSubj.funds,
                this.simSubj.adopted
        ));
        this.data.setTextAlignment(TextAlignment.CENTER);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StoreInfo oldSelf = this;
        setData();
        this.pcs.firePropertyChange("updated Store Info", oldSelf, this);
    }
}
