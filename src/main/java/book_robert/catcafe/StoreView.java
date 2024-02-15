package book_robert.catcafe;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class StoreView extends GridPane implements PropertyChangeListener{
    //the pane that holds the sim area
    public GridPane grid;
    public CafeSim sim;

    public StoreView(CafeSim subj){
        this.grid = new GridPane();
        this.sim = subj;
        this.grid.setId("StoreView");
        this.sim.pcs.addPropertyChangeListener(this); //GRADING: 1.A SUBJECT-GRID
    }
    public void removeListeners(){
        for(int i =0; i < this.sim.storeSize; i++) {
            for (int j = 0; j < this.sim.storeSize; j++) {
                int index = (i * this.sim.storeSize) + j;
                TileView tempView = (TileView) this.grid.getChildren().get(index);
                this.sim.tiles.get(index).pcs.removePropertyChangeListener(tempView);
            }
        }
    }
    void setModel(){
        this.grid.getChildren().clear();
        this.grid.getRowConstraints().clear();
        this.grid.getColumnConstraints().clear();
        this.grid.setAlignment(Pos.CENTER);
        for(int i =0; i < this.sim.storeSize; i++){
            for(int j = 0; j < this.sim.storeSize; j++){
                int index = (i * this.sim.storeSize) + j;
                TileView tempTile = new TileView(this.sim.tiles.get(index),index);
                tempTile.setAlignment(Pos.CENTER);
                tempTile.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                this.grid.add(tempTile, i, j);
            }
        }
        for(int i = 0; i < this.sim.storeSize; i++){
            float percentSize = (float) 100 / this.sim.storeSize;
            RowConstraints tempRow = new RowConstraints();
            ColumnConstraints tempCol = new ColumnConstraints();

            tempRow.setPercentHeight(percentSize);
            tempCol.setPercentWidth(percentSize);

            tempRow.setValignment(VPos.CENTER);
            tempCol.setHalignment(HPos.CENTER);

            this.grid.getRowConstraints().add(tempRow);
            this.grid.getColumnConstraints().add(tempCol);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) { //GRADING: OBSERVE
        for(Node node : grid.getChildren()){
            ((TileView)node).propertyChange(evt);
        }
    }

}
