package book_robert.catcafe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
Complete the following checklist. If you partially completed an item, put a note how it can be checked for what is
working for partial credit.


_yes_ Followed the class OOP diagram
_yes_ Observer pattern (ignores tiers)


_yes_ 1.	Tier: Views and tile area
_yes_ a. All objects (ignoring the sim area) (-1 for each missing)
_yes_ b. Have a starting number of tiles in sim area
_yes_ c. Able to add/remove a tile area properly (-33% for each error)
_yes_ d. Info bar listed correctly with all the required elements (-25% for each error)
_yes_ f. Tile Text correct in tile area (-25% per error)
_yes_ g. Radio buttons update properly
_yes_ h. Selecting a rectangle with “view” updates the tile area info (-50% per error)


_yes_ 2a Tier: Advanced functionality
_yes_ a. Next week button has some noticeable effect*
_yes_ b. Tile areas updated properly on “next” (-33% per error)*
_yes_ c. Sim info bar updated properly (-25% per error)
_yes_ d. Selecting a tile after an update shows the new information


_yes_ 2b: Layout
_yes_ a. Location of all items in correct spot (-20% per error)
_yes_ b. Layout still correct on window resize (-20%  for minor error)
_yes_ c. Resize grid at minimum resets the grid and info (-50% if minor error)
_yes_ d. Everything still working that is listed above with resize (-50% if minor error)



Final Tier: Extensions 30
Extension 1: 2.d 10 add colors: It looks pretty :)
Extension 2: 2.e 5 edit appearance of tiles: tiles look pretty too :)
Extension 3: 1.a 15 store info observer pattern: it should auto update the store info
    when week changes or any selection made
Extension 4: 1.b 10 tile view observer pattern: same as above
Extension 5: 2.a 5 mark area being shown in Tile Info: top left tile starts and will change whenever you select a new
    tile with the "View" radio button active.

Etc.


The grade you compute is the starting point for course staff, who reserve the right to change the grade if they disagree
with your assessment and to deduct points for other issues they may encounter, such as errors in the submission process,
naming issues, etc.
 */

public class Main extends Application {
    //Startup the JavaFX app here
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;

    @Override
    public void start(Stage stage){
        Layout layout = new Layout();
        stage.setTitle("Cat Cafe Sim Simulation");
        Scene scene = new Scene(layout.root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}