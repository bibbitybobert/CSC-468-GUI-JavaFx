package book_robert.catcafe;

public class Kitten extends FloorArea{
    //Kitten area
    public int countdown;
    public Kitten(){
        this.name = "Kitten";
        this.letter = 'K';
        this.totalCost = 200;
        this.weeklyCost = 20;
        this.age = 10;
        this.countdown = 4;
    }
}
