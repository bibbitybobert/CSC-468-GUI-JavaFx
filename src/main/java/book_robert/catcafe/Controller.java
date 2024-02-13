package book_robert.catcafe;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.EventListener;


public class Controller{
    //this should be where your event handler should go
    public CafeSim sim;
    public Layout view;

    public PropertyChangeSupport pcs;

    public Controller(CafeSim simToControl, Layout view){
        this.sim = simToControl;
        this.view = view;
        this.pcs = new PropertyChangeSupport(this);
    }

    public BorderPane makeACBox(ToggleGroup RBGroup){
        BorderPane tempBP = new BorderPane();

        //Next Week Button
        Button nextWeek = new Button("Next Week");
        tempBP.setLeft(nextWeek);
        nextWeek.setAlignment(Pos.BOTTOM_LEFT);
        nextWeek.setId("NextWeek");
        nextWeek.addEventFilter(ActionEvent.ACTION, new ActionFilter());


        //radio buttons
        HBox radioButtons = new HBox();
        RadioButton table = new RadioButton("Table");
        table.setId("TableRB");
        RadioButton cat = new RadioButton("Cat");
        cat.setId("CatRB");
        RadioButton kitten = new RadioButton("Kitten");
        kitten.setId("KittenRB");
        RadioButton empty = new RadioButton("Empty");
        empty.setId("EmptyRB");
        RadioButton view = new RadioButton("View");
        view.setId("ViewRB");

        table.setToggleGroup(RBGroup);
        cat.setToggleGroup(RBGroup);
        kitten.setToggleGroup(RBGroup);
        empty.setToggleGroup(RBGroup);
        view.setToggleGroup(RBGroup);

        table.setSelected(true);

        ObservableList<Node> buttonChildren = radioButtons.getChildren();
        buttonChildren.addAll(table, cat, kitten, empty, view);

        tempBP.setTop(radioButtons);
        radioButtons.setAlignment(Pos.CENTER);

        //Resize
        HBox resize = new HBox();
        javafx.scene.control.Label resizeLabel = new Label("Resize:");
        Button threeByThree = new Button("3x3");
        threeByThree.setId("3x3");
        threeByThree.addEventFilter(ActionEvent.ACTION, new ActionFilter());

        Button fiveByFive = new Button("5x5");
        fiveByFive.setId("5x5");
        fiveByFive.addEventFilter(ActionEvent.ACTION, new ActionFilter());

        Button nineByNine = new Button("9x9");
        nineByNine.setId("9x9");
        nineByNine.addEventFilter(ActionEvent.ACTION, new ActionFilter());

        ObservableList<Node> resizeChildren = resize.getChildren();
        resizeChildren.addAll(resizeLabel, threeByThree, fiveByFive, nineByNine);

        tempBP.setRight(resize);
        resize.setAlignment(Pos.CENTER_RIGHT);
        return tempBP;
    }

    public Label makeInfoBar(){
        Label infoBar = new Label();
        infoBar.setText("Week: %s\nFilled: %s\nFunds: $%s\nAdopted: %s".formatted(
                this.sim.week,
                this.sim.t_filled,
                this.sim.funds,
                this.sim.adopted
        ));
        infoBar.setTextAlignment(TextAlignment.CENTER);
        return infoBar;
    }

    public TileInfo makeTileInfo(int tileIdx){
        TileInfo tempTileInfo = new TileInfo(this.sim.tiles.get(tileIdx), this.sim);
        pcs.addPropertyChangeListener(tempTileInfo);
        return tempTileInfo;
    }

    public class ActionFilter implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(event.getSource() instanceof TileView) {
                switch (((RadioButton) view.RBGroup.getSelectedToggle()).getText()){
                    case "Table" -> {
                        sim.setTile(((TileView) event.getSource()).idx, "table");
                    }
                    case "Cat" -> sim.setTile(((TileView) event.getSource()).idx,
                            "cat");

                    case "Kitten" -> sim.setTile(((TileView) event.getSource()).idx,
                            "kitten");

                    case "Empty" -> sim.setTile(((TileView) event.getSource()).idx,
                            "empty");

                    case "View" -> {
                        System.out.println("View of idx: " + Integer.toString(((TileView) event.getSource()).idx));
                        view.tileInfo = makeTileInfo(((TileView) event.getSource()).idx);
                        view.setTileInfo();
                        pcs.firePropertyChange("tileInfoChange",
                                view.tileInfo,
                                view.tileInfo);
                    }

                    default -> {
                        System.out.println("ERROR");
                        System.exit(0);
                    }
                }
            }
            else if(event.getSource() instanceof Button) {
                if (((Node) event.getSource()).getId().compareTo("3x3") == 0) {
                    sim.resizeTiles(3, "empty");
                    view.setSimArea();
                }
                if (((Node) event.getSource()).getId().compareTo("5x5") == 0) {
                    sim.resizeTiles(5, "empty");
                    view.setSimArea();
                }
                if (((Node) event.getSource()).getId().compareTo("9x9") == 0) {
                    sim.resizeTiles(9, "empty");
                    view.setSimArea();
                }
            }
        }
    }

}

