package book_robert.catcafe;

public class CafeSim {
    //The manager class for the cafe
    int timeSinceReset;
    int funds;
    int week;
    int t_filled;
    int adopted;
    int f_change;
    int f_age;
    int t_cost;
    int t_revenue;

    public CafeSim(){
        this.timeSinceReset = 0;
        this.funds = 0;
        this.week = 0;
        this.t_filled = 0;
        this.adopted = 0;
        this.f_change = 0;
        this.f_age = 0;
        this.t_cost = 0;
        this.t_revenue = 0;
    }
    void nextWeek(){}
}
