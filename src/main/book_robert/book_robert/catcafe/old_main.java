/*
package book_robert.catcafe;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

///*
Complete the following checklist. If you partially completed an item, put a note how it can be checked for what is
working for partial credit.


____ Followed the class OOP diagram
____ Observer pattern (ignores tiers)


____ 1.	Tier: Views and tile area
____ a. All objects (ignoring the sim area) (-1 for each missing)
____ b. Have a starting number of tiles in sim area
____ c. Able to add/remove a tile area properly (-33% for each error)
____ d. Info bar listed correctly with all the required elements (-25% for each error)
____ f. Tile Text correct in tile area (-25% per error)
____ g. Radio buttons update properly
____ h. Selecting a rectangle with “view” updates the tile area info (-50% per error)


____ 2a Tier: Advanced functionality
____ a. Next week button has some noticeable effect*
____ b. Tile areas updated properly on “next” (-33% per error)*
____ c. Sim info bar updated properly (-25% per error)
____ d. Selecting a tile after an update shows the new information


____ 2b: Layout
____ a. Location of all items in correct spot (-20% per error)
____ b. Layout still correct on window resize (-20%  for minor error)
____ c. Resize grid at minimum resets the grid and info (-50% if minor error)
____ d. Everything still working that is listed above with resize (-50% if minor error)



Final Tier: Extensions 30
Extension 1: <number> <points> <name>: <how to test/find if applicable>
Extension 2: <number> <points> <name>: <how to test/find if applicable>
Etc.


The grade you compute is the starting point for course staff, who reserve the right to change the grade if they disagree
with your assessment and to deduct points for other issues they may encounter, such as errors in the submission process,
naming issues, etc.

public class old_main extends Application {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;


    private ArrayList<Node> getTopBoxList(int week, int filled, int funds, int adopted){
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new Label("Week: %s".formatted(week)));
        list.add(new Label("Filled: %s".formatted(filled)));
        list.add(new Label("Funds: $%s".formatted(funds)));
        list.add(new Label("Adopted: %s".formatted(adopted)));
        return list;
    }

    private ArrayList<Node> getLeftBoxList(int fChanged, int fAge, int cost){
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(new Label("Floor Changed: %s".formatted(fChanged)));
        list.add(new Label("Floor Age: %s".formatted(fAge)));
        list.add(new Label("Total Cost: $%s".formatted(cost)));
        return list;
    }
    private VBox createVBox(ArrayList<Node> inChildren){
        VBox newBox = new VBox();
        newBox.setFillWidth(true);
        newBox.setAlignment(Pos.CENTER);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);


        ObservableList<Node> children = newBox.getChildren();

        children.addAll(inChildren);

        return newBox;
    }

    private GridPane makeGrid(int size, ArrayList<old_tableData> tData){
        GridPane newGrid = new GridPane();
        newGrid.setGridLinesVisible(true);
        newGrid.setAlignment(Pos.CENTER);

        for(int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                Button tempButton = new Button(" -%s-\n-$%s\n%s".formatted(
                        tData.get((i*j) - 1).letter,
                        tData.get((i*j) - 1).cost,
                        tData.get((i*j) - 1).extra));
                tempButton.setAlignment(Pos.CENTER);
                tempButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                //newGrid.addRow(i-1, tempButton);
                newGrid.add(tempButton, i-1, j-1);
            }
        }

        for(int i = 0; i < size; i++){
            float percentSize = (float) 100 /size;
            RowConstraints tempRow = new RowConstraints();
            tempRow.setPercentHeight(percentSize);
            ColumnConstraints tempCol = new ColumnConstraints();
            tempCol.setPercentWidth(percentSize);
            tempCol.setHalignment(HPos.CENTER);

            newGrid.getRowConstraints().add(tempRow);
            newGrid.getColumnConstraints().add(tempCol);

        }

        return newGrid;
    }

    private BorderPane makeBorder(){
        BorderPane newBorder = new BorderPane();

        //Next Week Button
        Button nextWeek = new Button("Next Week");
        newBorder.setLeft(nextWeek);
        nextWeek.setAlignment(Pos.BOTTOM_LEFT);

        //radio buttons
        HBox radioButtons = new HBox();
        RadioButton table = new RadioButton("Table");
        RadioButton cat = new RadioButton("Cat");
        RadioButton kitten = new RadioButton("Kitten");
        RadioButton empty = new RadioButton("Empty");
        RadioButton view = new RadioButton("View");
        ObservableList<Node> buttonChildren = radioButtons.getChildren();
        buttonChildren.addAll(table, cat, kitten, empty, view);

        newBorder.setTop(radioButtons);
        radioButtons.setAlignment(Pos.CENTER);

        //Resize
        HBox resize = new HBox();
        Label resizeLabel = new Label("Resize:");
        Button threeByThree = new Button("3x3");
        Button fiveByFive = new Button("5x5");
        Button nineByNine = new Button("9x9");
        ObservableList<Node> resizeChildren = resize.getChildren();
        resizeChildren.addAll(resizeLabel, threeByThree, fiveByFive, nineByNine);

        newBorder.setRight(resize);
        resize.setAlignment(Pos.CENTER_RIGHT);
        return newBorder;
    }

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        old_CatCafeModel data = new old_CatCafeModel();
        //set title
        stage.setTitle("Cat Cafe Sim Simulation");

        //set size
        root.setPrefSize(WIDTH, HEIGHT);

        //add stuff
        VBox infoBar = createVBox(getTopBoxList(data.Week, data.TablesFilled,data.Funds, data.Adopted));
        //infoBar.setStyle("-fx-border-color: Blue");

        VBox tileInfo = createVBox(getLeftBoxList(data.FloorChanged, data.FloorAge, data.TotalCost));
        //tileInfo.setStyle("-fx-border-color: Red");

        GridPane simArea = makeGrid(data.FloorSize, data.tData);
        //simArea.setStyle("-fx-border-color: Green");

        BorderPane actionCommand = makeBorder();
        //actionCommand.setStyle("-fx-border-color: Black");

        root.setTop(infoBar);
        BorderPane.setAlignment(infoBar, Pos.CENTER);
        BorderPane.setMargin(infoBar, new Insets(10, 10, 10, 10));

        root.setLeft(tileInfo);
        BorderPane.setAlignment(tileInfo, Pos.CENTER);
        BorderPane.setMargin(tileInfo, new Insets(10, 10, 10, 10));

        root.setCenter(simArea);
        BorderPane.setAlignment(simArea, Pos.CENTER_LEFT);

        root.setBottom(actionCommand);
        BorderPane.setAlignment(actionCommand, Pos.CENTER);
        BorderPane.setMargin(actionCommand, new Insets(10, 10, 10, 10));

        //make visible
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /*public static void main(String[] args) {
        launch();
    }
}
*/