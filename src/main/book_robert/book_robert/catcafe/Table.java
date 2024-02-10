package book_robert.catcafe;

public class Table extends FloorArea{
    //table\food area
    public int revenue;
    public Table(){
        this.name = "Table";

        //letter of table tile type
        this.letter = 'T';

        //initial cost  = $300
        this.totalCost = 300;

        //weekly cost = $50
        this.weeklyCost = 50;

        //weekly revenue = $150
        this.revenue = 150;
    }
}
