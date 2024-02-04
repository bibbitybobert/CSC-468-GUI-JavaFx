package book_robert.catcafe;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class Layout extends BorderPane{
    //Build layout here
    private CafeSim data;
    public BorderPane root;
    public VBox infoBar;
    public VBox tileInfo;
    public StoreView simArea;
    public BorderPane actionCommand;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;

    private ArrayList<Node> getInfoBarData(){
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new Label("Floor Changed: %s".formatted(this.data.f_change)));
        list.add(new Label("Floor Age: %s".formatted(this.data.f_age)));
        list.add(new Label("Total Cost: $%s".formatted(this.data.t_cost)));
        return list;
    }

    private ArrayList<Node> getTileInfo(){
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new Label(this.data.view));
        list.add(new Label("Week: %s".formatted(this.data.week)));
        list.add(new Label("Filled: %s".formatted(this.data.t_filled)));
        list.add(new Label("Funds: $%s".formatted(this.data.funds)));
        list.add(new Label("Adopted: %s".formatted(this.data.adopted)));
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

    private BorderPane makeACBox(){
        BorderPane tempBP = new BorderPane();

        //Next Week Button
        Button nextWeek = new Button("Next Week");
        tempBP.setLeft(nextWeek);
        nextWeek.setAlignment(Pos.BOTTOM_LEFT);

        //radio buttons
        HBox radioButtons = new HBox();
        RadioButton table = new RadioButton("Table");
        RadioButton cat = new RadioButton("Cat");
        RadioButton kitten = new RadioButton("Kitten");
        RadioButton empty = new RadioButton("Empty");
        RadioButton view = new RadioButton("View");

        ToggleGroup RBGroup = new ToggleGroup();
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
        Label resizeLabel = new Label("Resize:");
        Button threeByThree = new Button("3x3");
        Button fiveByFive = new Button("5x5");
        Button nineByNine = new Button("9x9");
        ObservableList<Node> resizeChildren = resize.getChildren();
        resizeChildren.addAll(resizeLabel, threeByThree, fiveByFive, nineByNine);

        tempBP.setRight(resize);
        resize.setAlignment(Pos.CENTER_RIGHT);
        return tempBP;
    }
    public Layout(){
        this.root = new BorderPane();
        this.data = new CafeSim();

        //set table area
        this.simArea = new StoreView();
        this.simArea.setModel();

        //set infoBar
        this.infoBar = makeVBox(getInfoBarData());

        //set tileInfo
        this.tileInfo = makeVBox(getTileInfo());

        //set actionCommand
        this.actionCommand = makeACBox();

        //set all nodes to correct spot on root
        this.root.setTop(this.infoBar);
        this.root.setLeft(this.tileInfo);
        this.root.setCenter(this.simArea.grid);
        this.root.setBottom(this.actionCommand);

        //set alignments
        BorderPane.setAlignment(this.infoBar, Pos.CENTER);
        BorderPane.setAlignment(this.tileInfo, Pos.CENTER);
        BorderPane.setAlignment(this.simArea, Pos.CENTER_LEFT);
        BorderPane.setAlignment(this.actionCommand, Pos.CENTER);
    }
}
