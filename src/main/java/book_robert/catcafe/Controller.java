package book_robert.catcafe;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

public class Controller{
    //this should be where your event handler should go
    public CafeSim sim;
    public Layout view;

    public Controller(CafeSim simToControl, Layout view){
        this.sim = simToControl;
        this.view = view;
    }

    public BorderPane makeACBox(ToggleGroup RBGroup){
        BorderPane tempBP = new BorderPane();

        //Next Week Button
        Button nextWeek = new Button("Next Week");
        tempBP.setLeft(nextWeek);
        nextWeek.setAlignment(Pos.BOTTOM_LEFT);
        nextWeek.setId("NextWeek");
        nextWeek.addEventFilter(ActionEvent.ACTION, new ActionFilter());
        nextWeek.setStyle("-fx-background-color: #dda6b5; -fx-border-color: #ffd3da");


        //radio buttons
        HBox radioButtons = new HBox();
        RadioButton table = new RadioButton("Table");
        table.setId("TableRB");
        table.setStyle("-fx-background-color: #9075d8; -fx-border-color: #dda6b5");

        RadioButton cat = new RadioButton("Cat");
        cat.setId("CatRB");
        cat.setStyle("-fx-background-color: #eb9d9d; -fx-border-color: #ffd3da");

        RadioButton kitten = new RadioButton("Kitten");
        kitten.setId("KittenRB");
        kitten.setStyle("-fx-background-color: #cea2d7; -fx-border-color: #ffd3da");

        RadioButton empty = new RadioButton("Empty");
        empty.setId("EmptyRB");
        empty.setStyle("-fx-background-color: #ffdbde; -fx-border-color: #dda6b5");

        RadioButton view = new RadioButton("View");
        view.setId("ViewRB");
        view.setStyle("-fx-background-color: #ffdbde; -fx-border-color: #dda6b5");

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
        threeByThree.setStyle("-fx-background-color: #dda6b5; -fx-border-color: #ffd3da");

        Button fiveByFive = new Button("5x5");
        fiveByFive.setId("5x5");
        fiveByFive.addEventFilter(ActionEvent.ACTION, new ActionFilter());
        fiveByFive.setStyle("-fx-background-color: #dda6b5; -fx-border-color: #ffd3da");

        Button nineByNine = new Button("9x9");
        nineByNine.setId("9x9");
        nineByNine.addEventFilter(ActionEvent.ACTION, new ActionFilter());

        ObservableList<Node> resizeChildren = resize.getChildren();
        resizeChildren.addAll(resizeLabel, threeByThree, fiveByFive, nineByNine);
        nineByNine.setStyle("-fx-background-color: #dda6b5; -fx-border-color: #ffd3da");

        tempBP.setRight(resize);
        resize.setAlignment(Pos.CENTER_RIGHT);
        return tempBP;
    }

    public class ActionFilter implements EventHandler<ActionEvent>{

        private void clearTileInfo(){
            for(Tile tile : sim.tiles){
                if(tile == view.tileInfo.dataLookup){
                    tile.pcs.removePropertyChangeListener(view.tileInfo);
                }
            }
        }

        @Override
        public void handle(ActionEvent event){
            if(event.getSource() instanceof TileView) {
                switch (((RadioButton) view.RBGroup.getSelectedToggle()).getText()){
                    case "Table" -> sim.setTile(((TileView) event.getSource()).idx,
                            "table");
                    case "Cat" -> sim.setTile(((TileView) event.getSource()).idx,
                            "cat");

                    case "Kitten" -> sim.setTile(((TileView) event.getSource()).idx,
                            "kitten");

                    case "Empty" -> sim.setTile(((TileView) event.getSource()).idx,
                                "empty");

                    case "View" -> {
                        TileView lookat = ((TileView) event.getSource());
                        for(Node t: view.simArea.grid.getChildren()){
                            if(((TileView)t).model == view.tileInfo.dataLookup){
                                ((TileView)t).color();
                                ((TileView)t).model.observed = false;
                            }
                        }
                        view.tileInfo.dataLookup.pcs.removePropertyChangeListener(view.tileInfo);
                        view.tileInfo.dataLookup = lookat.model;
                        lookat.model.observed = true;
                        lookat.highlight();
                        view.tileInfo.refactor(view.tileInfo.dataLookup);
                        view.tileInfo.dataLookup.pcs.addPropertyChangeListener(view.tileInfo); //GRADING: 1.B SUBJECT-TILE AREA
                    }

                    default -> {
                        System.out.println("ERROR");
                        System.exit(0);
                    }
                }
                sim.pcs.firePropertyChange("Tile Changed", null, sim); //GRADING: TRIGGER
            }
            else if(event.getSource() instanceof Button) {
                if (((Node) event.getSource()).getId().compareTo("NextWeek") == 0){
                    sim.nextWeek();
                }
                else {
                    this.clearTileInfo();
                    view.simArea.removeListeners();
                    if (((Node) event.getSource()).getId().compareTo("3x3") == 0) {
                        sim.resizeTiles(3);
                    }
                    if (((Node) event.getSource()).getId().compareTo("5x5") == 0) {
                        sim.resizeTiles(5);
                    }
                    if (((Node) event.getSource()).getId().compareTo("9x9") == 0) {
                        sim.resizeTiles(9);

                    }
                    view.simArea.setModel();
                    view.setSimArea();
                    view.tileInfo.dataLookup = sim.tiles.get(0);
                    ((TileView) view.simArea.grid.getChildren().get(0)).highlight();
                    sim.tiles.get(0).pcs.addPropertyChangeListener(view.tileInfo); //GRADING: 1.B SUBJECT-TILE AREA
                    //GRADING: 1.B TRIGGER-TILE AREA
                    sim.tiles.get(0).pcs.firePropertyChange("newTile", null, sim.tiles.get(0));
                }
            }
        }
    }

}

