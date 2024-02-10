package book_robert.catcafe;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Layout extends BorderPane implements PropertyChangeListener{
    //Build layout here
    private CafeSim data;
    public BorderPane root;
    public Label infoBar;
    public TileInfo tileInfo;
    public StoreView simArea;
    public BorderPane actionCommand;
    private Controller controller;
    public ToggleGroup RBGroup;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;

    public Layout(){
        this.root = new BorderPane();
        this.data = new CafeSim();
        this.controller = new Controller(data,this);

        //set table area
        this.simArea = new StoreView(this.controller);
        this.simArea.setModel();

        //set infoBar
        this.infoBar = this.controller.makeInfoBar();

        //set tileInfo
        this.tileInfo = this.controller.makeTileInfo();
        this.controller.pcs.addPropertyChangeListener(this.tileInfo);

        //set actionCommand
        this.RBGroup = new ToggleGroup();
        this.actionCommand = this.controller.makeACBox(RBGroup);

        //set all nodes to correct spot on root
        this.root.setTop(this.infoBar);
        this.root.setLeft(this.tileInfo.data);
        this.root.setCenter(this.simArea.grid);
        this.root.setBottom(this.actionCommand);

        //set alignments
        BorderPane.setAlignment(this.infoBar, Pos.CENTER);
        BorderPane.setAlignment(this.tileInfo.data, Pos.CENTER);
        BorderPane.setAlignment(this.simArea, Pos.CENTER_LEFT);
        BorderPane.setAlignment(this.actionCommand, Pos.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.root.setLeft(this.tileInfo);
    }
}
