package com.chs.waterman;

import com.orm.SugarRecord;

/**
 * Created by SESA312452 on 5/26/2017.
 */

public class Drink extends SugarRecord {

    String dateTime;

    public long getBevID() {
        return bevID;
    }

    public void setBevID(long bevID) {
        this.bevID = bevID;
    }

    long bevID;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    double amount;
    String unit;

public Drink(){




}
    public void doSomething(){

    }

    public Drink(String dateTime, double amount, String unit, long bevID){
        this.dateTime = dateTime;
        this.amount = amount;
        this.unit = unit;
        this.bevID = bevID;
    }
}
