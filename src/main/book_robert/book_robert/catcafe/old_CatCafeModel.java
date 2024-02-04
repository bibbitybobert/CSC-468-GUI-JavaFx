/*
package book_robert.catcafe;

import java.util.ArrayList;

public class old_CatCafeModel {

    //visible data
    public ArrayList<old_tableData> tData;
    public Integer Week;
    public Integer TablesFilled;
    public Integer Funds;
    public Integer Adopted;
    public Integer FloorChanged;
    public Integer FloorAge;
    public Integer TotalCost;

    //layout options
    public Integer FloorSize;


    //Main Constructor
    public old_CatCafeModel(Integer size){
        this.Week = 0;
        this.TablesFilled = 0;
        this.Funds = 0;
        this.Adopted = 0;
        this.FloorChanged = 0;
        this.FloorAge = 0;
        this.TotalCost = 0;
        this.FloorSize = size;
        this.tData = new ArrayList<old_tableData>();
        for(int i = 0; i < size * size; i++){
            old_tableData temp = new old_tableData();
            this.tData.add(temp);
        }

    }
    public old_CatCafeModel(){
        this(3);
    }
}

 */