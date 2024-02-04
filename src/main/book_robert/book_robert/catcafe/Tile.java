package book_robert.catcafe;

public class Tile{
    //Constant holder for a floor area (potentially more)
    //the subject of the holder
    public char letter;
    public int cost;
    public int extra_type; //0: empty, 1: weekly revenue 2: time until adoption
    public int weekly_revenue;
    public char t_t_adoption;

    public Tile(){
        this.letter = 'E';
        this.cost = 10;
        this.extra_type = 0;
    }
    void nextMonth(){}
}
