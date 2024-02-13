package book_robert.catcafe;

public class Empty extends FloorArea{
    //unused area
    public int revenue;
    public Empty(){
        this.name = "Empty";
        this.letter = 'E';
        this.totalCost = 200;
        this.weeklyCost = 10;
        this.revenue = 0;
    }
}
