package com.example.catcafe;

import java.security.PublicKey;
import java.util.ArrayList;

public class CatCafeModel {

    //visible data
    public ArrayList<tableData> tData;
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
    public CatCafeModel(Integer size){
        this.Week = 0;
        this.TablesFilled = 0;
        this.Funds = 0;
        this.Adopted = 0;
        this.FloorChanged = 0;
        this.FloorAge = 0;
        this.TotalCost = 0;
        this.FloorSize = size;
        this.tData = new ArrayList<tableData>();
        for(int i = 0; i < size * size; i++){
            tableData temp = new tableData();
            this.tData.add(temp);
        }

    }
    public CatCafeModel(){
        this(3);
    }
}
