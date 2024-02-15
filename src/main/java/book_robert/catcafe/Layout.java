package book_robert.catcafe;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Layout extends BorderPane implements PropertyChangeListener{
    //Build layout here
    private CafeSim data;
    public BorderPane root;
    public StoreInfo infoBar;
    public TileInfo tileInfo;
    public StoreView simArea;
    public BorderPane actionCommand;
    private Controller controller;
    public ToggleGroup RBGroup;
    public Layout(){
        this.root = new BorderPane();
        this.data = new CafeSim();
        this.controller = new Controller(data,this);

        //set table area
        this.simArea = new StoreView(this.data);
        this.simArea.setModel();
        this.setSimArea();

        //set infoBar
        this.infoBar = new StoreInfo(this.data);
        this.infoBar.pcs.addPropertyChangeListener(this); //GRADING: 1.A SUBJECT-GRID

        //set tileInfo
        this.tileInfo = new TileInfo(this.data.tiles.get(0), this.data);
        ((TileView)this.simArea.grid.getChildren().get(0)).highlight();
        this.tileInfo.pcs.addPropertyChangeListener(this); //GRADING: 1.B SUBJECT-TILE AREA

        //set actionCommand
        this.RBGroup = new ToggleGroup();
        this.actionCommand = this.controller.makeACBox(RBGroup);

        //set all nodes to correct spot on root
        this.root.setTop(this.infoBar.data);
        this.root.setLeft(this.tileInfo.data);
        this.root.setCenter(this.simArea.grid);
        this.root.setBottom(this.actionCommand);

        //set alignments
        BorderPane.setAlignment(this.infoBar.data, Pos.CENTER);
        BorderPane.setAlignment(this.tileInfo.data, Pos.CENTER);
        BorderPane.setAlignment(this.simArea, Pos.CENTER_LEFT);
        BorderPane.setAlignment(this.actionCommand, Pos.CENTER);

        //COLORS!!!
        this.root.setStyle("-fx-background-color: #ffdbde");
    }

    public void setSimArea(){
        for(Node btn : this.simArea.grid.getChildren()){
            btn.addEventFilter(ActionEvent.ACTION, this.controller.new ActionFilter());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) { //GRADING: 1.A/1.B OBSERVE-GRID -TILE AREA
        this.root.setLeft(this.tileInfo.data);
        BorderPane.setAlignment(this.tileInfo.data, Pos.CENTER);

        this.root.setTop(this.infoBar.data);
        BorderPane.setAlignment(this.infoBar.data, Pos.CENTER);
    }
}
