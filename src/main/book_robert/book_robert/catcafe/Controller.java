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

    /*
    private ArrayList<Node> getInfoBarData(){
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new Label("Floor Changed: %s".formatted(this.sim.f_change)));
        list.add(new Label("Floor Age: %s".formatted(this.sim.f_age)));
        list.add(new Label("Total Cost: $%s".formatted(this.sim.t_cost)));
        return list;
    }

    private ArrayList<Node> getTileInfo(){
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new Label(this.sim.view));
        list.add(new Label("Week: %s".formatted(this.sim.week)));
        list.add(new Label("Filled: %s".formatted(this.sim.t_filled)));
        list.add(new Label("Funds: $%s".formatted(this.sim.funds)));
        list.add(new Label("Adopted: %s".formatted(this.sim.adopted)));
        return list;
    }


    private VBox makeVBox(ArrayList<Node> boxData){
        VBox newVBox = new VBox();
        newVBox.setFillWidth(true);
        newVBox.setAlignment(Pos.CENTER);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        ObservableList<Node> children = newVBox.getChildren();

        children.addAll(boxData);
        return newVBox;
    }
    */

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

    public TileInfo makeTileInfo(){
        return new TileInfo(this.sim);
    }

    public class ActionFilter implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(((Node)event.getSource()).getId().compareTo("3x3") == 0){
                view.simArea.resize(3);
            }
            if(((Node)event.getSource()).getId().compareTo("5x5") == 0){
                view.simArea.resize(5);
            }
            if(((Node)event.getSource()).getId().compareTo("9x9") == 0){
                view.simArea.resize(9);
            }
            if(((Node)event.getSource()).getId().compareTo("TileView") == 0){
                if(((RadioButton)view.RBGroup.getSelectedToggle()).getText() == "Table"){
                    ((TileView)event.getSource()).model.type = new Table();
                    //GRADING: TRIGGER
                    pcs.firePropertyChange("model.type",
                            ((Node)event.getSource()).getClass(), new Table());
                }
                else if(((RadioButton)view.RBGroup.getSelectedToggle()).getText() == "Cat"){
                    ((TileView)event.getSource()).model.type = new Cat();
                    //GRADING: TRIGGER
                    pcs.firePropertyChange("model.type",
                            ((Node)event.getSource()).getClass(), new Cat());
                }
                else if(((RadioButton)view.RBGroup.getSelectedToggle()).getText() == "Kitten"){
                    ((TileView)event.getSource()).model.type = new Kitten();
                    //GRADING: TRIGGER
                    pcs.firePropertyChange("model.type",
                            ((Node)event.getSource()).getClass(), new Kitten());
                }
                else if(((RadioButton)view.RBGroup.getSelectedToggle()).getText() == "Empty"){
                    ((TileView)event.getSource()).model.type = new Empty();
                    //GRADING: TRIGGER
                    pcs.firePropertyChange("model.type",
                            ((Node)event.getSource()).getClass(), new Empty());
                }
                else if(((RadioButton)view.RBGroup.getSelectedToggle()).getText() == "View"){
                    view.tileInfo.refactor(((TileView)event.getSource()).model.type);
                    pcs.firePropertyChange("tileInfoBar", view.infoBar, view.infoBar);
                }
                else {
                    System.out.println("Error");
                }

            }
        }
    }

}

