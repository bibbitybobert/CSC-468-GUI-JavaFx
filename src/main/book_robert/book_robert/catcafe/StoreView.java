package book_robert.catcafe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.controlsfx.control.GridView;
import org.controlsfx.control.spreadsheet.Grid;

import java.beans.PropertyChangeSupport;

public class StoreView extends GridPane{
    //the pane that holds the sim area
    public GridPane grid;
    public Controller controller;


    public StoreView(Controller controller){
        this.grid = new GridPane();
        this.grid.setId("StoreView");
        this.controller = controller;
    }
    void setModel(){
        resize(3);
    }
    void resize(int size){
        this.grid.getChildren().clear();
        this.grid.getRowConstraints().clear();
        this.grid.getColumnConstraints().clear();
        this.grid.setAlignment(Pos.CENTER);

        for(int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                TileView tempTile = new TileView();
                tempTile.setAlignment(Pos.CENTER);
                tempTile.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                tempTile.setId("TileView");
                tempTile.addEventFilter(ActionEvent.ACTION, this.controller.new ActionFilter());
                this.controller.pcs.addPropertyChangeListener(tempTile); //GRADING: SUBJECT
                this.grid.addRow(i-1, tempTile);
            }
        }

        for(int i = 0; i < size; i++){
            float percentSize = (float) 100 / size;
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

}
