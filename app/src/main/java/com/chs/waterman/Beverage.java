package com.chs.waterman;

import com.orm.SugarRecord;

/**
 * Created by SESA312452 on 5/26/2017.
 */

public class Beverage extends SugarRecord {
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    // master list of beverages to pick from
int active;

    String bevName;

    public String getBevIcon() {
        return bevIcon;
    }

    public void setBevIcon(String bevIcon) {
        this.bevIcon = bevIcon;
    }

    String bevIcon;

    public String getBevName() {
        return bevName;
    }

    public void setBevName(String bevName) {
        this.bevName = bevName;
    }

    public int getBevAmount() {
        return bevAmount;
    }

    public void setBevAmount(int bevAmount) {
        this.bevAmount = bevAmount;
    }

    public String getBevUnit() {
        return bevUnit;
    }

    public void setBevUnit(String bevUnit) {
        this.bevUnit = bevUnit;
    }


    int bevAmount;
    String bevUnit;





public Beverage(){

}

    public Beverage(  String bevName, int bevAmount, String bevUnit , String bevIcon, int Active){

        this.bevName = bevName;
        this.bevAmount = bevAmount;
        this.bevUnit = bevUnit;
        this.bevIcon = bevIcon;
        this.active = active;
    }
}
